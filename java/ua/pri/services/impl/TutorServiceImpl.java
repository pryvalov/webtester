package ua.pri.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.pri.dao.AnswerDao;
import ua.pri.dao.QuestionDao;
import ua.pri.dao.TestDao;
import ua.pri.ent.Account;
import ua.pri.ent.Answer;
import ua.pri.ent.Question;
import ua.pri.ent.Test;
import ua.pri.services.TutorService;

@Service("tutorService")
public class TutorServiceImpl implements TutorService {

	private static final Logger LOGGER = LogManager
			.getLogger(TutorServiceImpl.class);

	@Autowired
	private TestDao testDao;

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private AnswerDao answerDao;

	@Override
	@Transactional(readOnly = true)
	public List<Test> getUserTests(Account account) {
		return testDao.getList(account);
	};

	@Override
	@Transactional(readOnly = true)
	public List<Test> getAllTests() {
		return testDao.getList();
	};

	@Transactional(readOnly = false)
	public void delete(int id) {
		testDao.delete(testDao.loadTest(id));
	}

	@Transactional(readOnly = false)
	public void activate(int id) {
		Test toActivate = testDao.findById(id);
		toActivate.setActive(true);
		testDao.update(toActivate);
	}

	@Transactional(readOnly = false)
	public void deActivate(int id) {
		Test toDeActivate = testDao.loadTest(id);
		toDeActivate.setActive(false);
		testDao.merge(toDeActivate);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Test> getAllTests(int offset, int limit) {
		return testDao.getList(offset, limit);
	};

	@Override
	@Transactional(readOnly = true)
	public Test loadTest(int id_test) {
		return testDao.loadTest(id_test);
	}

	@Override
	public Test createTest(Account account) {
		Test t = new Test();
		t.setActive(true);
		t.setCreated(new Date());
		t.setAuthor(account);
		saveTest(t);

		return t;
	}

	@Override
	public Test createTest(String name, String subject, Account author) {
		Test t = createTest(author);
		t.setSubject(subject);
		t.setName(name);
		return t;
	}

	@Override
	public Test createTest(String name, String subject, String time,
			Account author) {
		Test t = createTest(author);
		t.setSubject(subject);
		t.setName(name);
		t.setTime(time);
		return t;
	}

	@Override
	public Test createTest(Test test, String name, String subject, String time,
			Account author) {
		LOGGER.debug(author.getFirstName());
		if (test != null) {
			Test t = test;
			t.setAuthor(author);
			t.setSubject(subject);
			t.setName(name);
			t.setTime(time);
			return t;
		}
		LOGGER.error("error creating test, session do not contain one");
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void saveTest(Test test) {
	/*	LOGGER.info("save service");
		test.setQuestionsSize(""+(test.getQuestions().size()+1));
		LOGGER.debug("testing size: "+test.getQuestions().size());
		int i=1;
		for(Question q: test.getQuestions())
			LOGGER.debug(" == "+(++i));*/
		testDao.save(test);
		LOGGER.debug(test.getAuthor().getFirstName() + " test author");
		for (Question question : test.getQuestions()) {
			questionDao.save(question);
			for (Answer answer : question.getAnswers())
				answerDao.save(answer);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void updateTest(Test test) {
		
		
		
		Test oldTest = testDao.loadTest(test.getIdTest());

		List<Question> questions = test.getQuestions();

		List<Question> oldQuestions = oldTest.getQuestions();

		List<Question> toUpdate = (List<Question>) CollectionUtils.retainAll(
				questions, oldQuestions);

		List<Question> toDelete = (List<Question>) CollectionUtils.removeAll(
				oldQuestions, toUpdate);

		List<Question> toSave = (List<Question>) CollectionUtils.removeAll(
				questions, toUpdate);

		List<Question> beforeUpdate = (List<Question>) CollectionUtils
				.retainAll(oldQuestions, questions);

		for (Question questionToUpdate : toUpdate) {

			List<Answer> oldAnswers = beforeUpdate.get(
					beforeUpdate.indexOf(questionToUpdate)).getAnswers();

			List<Answer> answersToUpdate = (List<Answer>) CollectionUtils
					.retainAll(questionToUpdate.getAnswers(), oldAnswers);

			List<Answer> answersToDelete = (List<Answer>) CollectionUtils
					.removeAll(oldAnswers, answersToUpdate);

			List<Answer> answersToSave = (List<Answer>) CollectionUtils
					.removeAll(questionToUpdate.getAnswers(), answersToUpdate);
			for (Answer answer : answersToDelete)
				answerDao.delete(answer);
			for (Answer answer : answersToSave)
				answerDao.save(answer);

		}

		for (Question questionToDelete : toDelete) {

			for (Answer answer : questionToDelete.getAnswers())
				answerDao.delete(answer);

			questionDao.delete(questionToDelete);

		}

		for (Question questionToSave : toSave) {
			questionDao.save(questionToSave);
			for (Answer answer : questionToSave.getAnswers())
				answerDao.save(answer);

		}
		try {

			oldTest.setName(test.getName());
			oldTest.setSubject(test.getSubject());
			oldTest.setTime(test.getTime());
			/*oldTest.setQuestionsSize(""+oldTest.getQuestions().size());
			LOGGER.debug("testing size upd: "+(oldTest.getQuestions().size()+1));
			int i=1;
			for(Question q: test.getQuestions()) ////////////////////////////////////////////////////
				LOGGER.debug(" == "+(++i));*/
			testDao.update(oldTest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " ID TEST: " + test.getIdTest());
		}

	}

	@Override
	@Transactional(readOnly = false)
	public void persistTest(Test test) {
		testDao.persist(test);
	}

	@Override
	@Transactional(readOnly = false)
	public void mergeTest(Test test) {
		testDao.merge(test);
	}

	public Test updateTest(Map<String, String> params, Test test) {
		Question question = new Question();
		question.setQuestionText(params.get("question"));

		question.setTest(test);
		List<Answer> answers = new ArrayList<>();
		Set<Integer> correctAnswers = new HashSet<Integer>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if (entry.getKey().startsWith("cbox")) {
				correctAnswers.add(Integer.valueOf(entry.getValue()));
			}

		}

		int iterator = 0;
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if (entry.getKey().startsWith("answer")) {
				iterator++;
				Answer answer = new Answer();
				answer.setAnswerText(entry.getValue());
				answer.setQuestion(question);
				if (correctAnswers.contains(iterator))
					answer.setCorrect(true);
				else
					answer.setCorrect(false);
				answers.add(answer);
			}

		}
		question.setAnswers(answers);
		//if (test == null)
			//test = createTest(null);//TODO: ???? wat?

		test.setName(params.get("name"));
		test.setSubject(params.get("subj"));
		test.setTime(params.get("time"));

		test.getQuestions().add(question);
		if (test.getIdTest() != 0) {
			updateTest(test);// //////////---------------------------------------------------------HERE
								// NEW
			LOGGER.info("updated test " + test.getIdTest());
		} else {
			saveTest(test);
			LOGGER.info("saved test " + test.getIdTest());
		}
		return test;

	}

	

	public Test updateQuestion(Test test, Question question,
			Map<String, String> params) {
		if (!StringUtils.isBlank(params.get("question"))) {
			Question updatedQuestion = new Question();
			updatedQuestion.setTest(test);
			updatedQuestion.setQuestionText(params.get("question"));

			List<String> correct = new ArrayList<>();
			Map<String, Boolean> ansmap = new HashMap<>();
			List<Answer> answers = new ArrayList<>();

			for (Map.Entry<String, String> entry : params.entrySet())
				if (entry.getKey().startsWith("cbox"))
					correct.add(entry.getValue().replaceAll("[^0-9]", ""));

			for (Map.Entry<String, String> entry : params.entrySet())
				if (entry.getKey().startsWith("answer"))
					ansmap.put(
							entry.getValue(),
							correct.contains(entry.getKey().replaceAll(
									"[^0-9]", "")));

			for (Map.Entry<String, Boolean> entry : ansmap.entrySet()) {
				Answer answer = new Answer();
				answer.setAnswerText(entry.getKey());
				answer.setCorrect(entry.getValue());
				answer.setQuestion(updatedQuestion);
				answers.add(answer);
			}
			updatedQuestion.setAnswers(answers);

			test.getQuestions().remove(question);
			test.getQuestions().add(updatedQuestion);
		}
		test.setName(params.get("name"));
		test.setSubject(params.get("subj"));
		test.setTime(params.get("time"));
		updateTest(test);
		return test;

	}

	@Transactional(readOnly = true)
	public Question findQuestion(Integer id_question) {
		return questionDao.findById(Integer.valueOf(id_question));
	}



	public void deleteQuestion(int idQuestion, Test test) {
		//
		test.getQuestions().remove(questionDao.findById(idQuestion));
		questionDao.delete(questionDao.findById(idQuestion));
	}

}
