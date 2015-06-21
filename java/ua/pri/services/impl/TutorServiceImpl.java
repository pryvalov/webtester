package ua.pri.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
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
	public List<Test> getUserTests(Account account) {
		return testDao.getList(account);
	};

	@Override
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
	public List<Test> getAllTests(int offset, int limit) {
		return testDao.getList(offset, limit);
	};

	@Override
	public Test loadTest(int id_test) {
		return testDao.loadTest(id_test);
	}

	@Override
	public Test createTest() {
		Test t = new Test();
		t.setActive(true);
		t.setCreated(new Date());

		return t;
	}

	@Override
	public Test createTest(String name, String subject, Account author) {
		Test t = createTest();
		t.setAuthor(author);
		t.setSubject(subject);
		t.setName(name);
		return t;
	}

	@Override
	public Test createTest(String name, String subject, String time,
			Account author) {
		Test t = createTest();
		t.setAuthor(author);
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
		LOGGER.info("save service");
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
		/*
		 * LOGGER.info("update service"); Test oldTest =
		 * testDao.loadTest(test.getIdTest()); List<Question> oldQuestionsList =
		 * oldTest.getQuestions(); int index = 0; Question oldQuestion; for
		 * (Question question : test.getQuestions()) { if
		 * (oldQuestionsList.contains(question)) { questionDao.update(question);
		 * index = oldQuestionsList.indexOf(question); oldQuestion =
		 * oldQuestionsList.get(index); for (Answer answer :
		 * question.getAnswers()){ if(oldQuestion.getAnswers().contains(answer))
		 * answerDao.update(answer); else answerDao.save(answer); } }
		 * 
		 * 
		 * else { questionDao.save(question); for(Answer answer :
		 * question.getAnswers()) answerDao.save(answer); } }
		 * testDao.update(test);
		 */

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
		if (test == null)
			test = createTest();
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

	public void updateTest(Map<String, String> params, Account author) {
		Test test = createTest(params.get("name"), params.get("subj"), author);
		List<Question> questions = new ArrayList<>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if (entry.getKey().startsWith("ques")) {
				Question question = new Question();
				question.setQuestionText(entry.getValue());
				question.setTest(test);
				questions.add(question);

			}

		}
		test.setQuestions(questions);
		/* persistTest(test); */
		saveTest(test);
	}

	public Question updateQuestion(Test test, Question question,
			Map<String, String> params) {
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
				ansmap.put(entry.getValue(), correct.contains(entry.getKey()
						.replaceAll("[^0-9]", "")));

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

		return updatedQuestion;

	}

	@Transactional
	public Question findQuestion(Integer id_question) {
		return questionDao.findById(Integer.valueOf(id_question));
	}

	public Test substituteQuestion(Test test, Question new_question)
			throws Exception {
		for (Question que : test.getQuestions()) {
			if (que.getIdQuestion() == new_question.getIdQuestion()) {
				test.getQuestions().remove(que);
				return test;
			}
		}
		throw new Exception("Test does not contain question");
	}

	public void deleteQuestion(int idQuestion, Test test) {
		//
		test.getQuestions().remove(questionDao.findById(idQuestion));
		questionDao.delete(questionDao.findById(idQuestion));
	}

}
