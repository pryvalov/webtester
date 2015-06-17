package ua.pri.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void delete(int id) {
		testDao.delete(testDao.loadTest(id));
	}

	public void activate(int id) {
		Test toActivate = testDao.loadTest(id);
		toActivate.setActive(true);
		testDao.merge(toActivate);
	}

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
	public void saveTest(Test test) {
		LOGGER.info("save service");
		testDao.save(test);
		for (Question question : test.getQuestions()) {
			questionDao.save(question);
			for (Answer answer : question.getAnswers())
				answerDao.save(answer);
		}
	}

	
	@Override
	public void updateTest(Test test) {
		LOGGER.info("update service");
		Test oldTest = testDao.loadTest(test.getIdTest());
		List<Question> oldQuestionsList = oldTest.getQuestions();
		int index = 0;
		Question oldQuestion;
		for (Question question : test.getQuestions()) {
			if (oldQuestionsList.contains(question)) {
				questionDao.update(question);
				index = oldQuestionsList.indexOf(question);
				oldQuestion = oldQuestionsList.get(index);
				for (Answer answer : question.getAnswers()){
						if(oldQuestion.getAnswers().contains(answer))
							answerDao.update(answer);
						else
							answerDao.save(answer);
					}
				}
			
			
			else {
				questionDao.save(question);
					for(Answer answer : question.getAnswers())
						answerDao.save(answer);
			}
		}
		testDao.update(test);
	}

	@Override
	public void persistTest(Test test) {
		testDao.persist(test);
	}

	@Override
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
		test.getQuestions().add(question);
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

}
