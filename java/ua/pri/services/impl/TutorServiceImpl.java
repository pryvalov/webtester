package ua.pri.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pri.dao.TestDao;
import ua.pri.ent.Account;
import ua.pri.ent.Question;
import ua.pri.ent.Test;
import ua.pri.services.TutorService;

@Service("tutorService")
public class TutorServiceImpl implements TutorService {
	
	private static final Logger LOGGER = LogManager.getLogger(TutorServiceImpl.class);
	
	@Autowired
	private TestDao testDao;
	
	@Override
	public List<Test> getUserTests(Account account){
		return testDao.getList(account);
	};
	
	@Override
	public List<Test> getAllTests(){
		return testDao.getList();
	};
	
	@Override
	public List<Test> getAllTests(int offset, int limit){
		return testDao.getList(offset, limit);
	};
	
	@Override
	public Test loadTest(int id_test){
		return testDao.loadTest(id_test);
	}
	
	@Override
	public Test createTest(){
		Test t = new Test();
		t.setActive(true);
		t.setCreated(new Date());
		
		return t;
	}
	
	@Override
	public Test createTest(String name, String subject, Account author){
		Test t = createTest();
		t.setAuthor(author);
		t.setSubject(subject);
		t.setName(name);
		return t;
	}
	
	@Override
	public void saveTest(Test test){
		testDao.save(test);
	}
	@Override
	public void updateTest(Test test){
		testDao.update(test);
	}
	@Override
	public void persistTest(Test test){
		testDao.persist(test);
	}
	@Override
	public void mergeTest(Test test){
		testDao.merge(test);
	}
	
	public void updateTest(Map<String, String> params, Account author){
		Test test = createTest(params.get("name"), params.get("subj"), author);
		List<Question> questions = new ArrayList<>();
		for(Map.Entry<String, String> entry : params.entrySet())
		{
			if(entry.getKey().startsWith("ques")){
				Question question = new Question();
				question.setQuestionText(entry.getValue());
				question.setTest(test);
				questions.add(question);
								
			}
				
		}
		test.setQuestions(questions);
		persistTest(test);	
	}
	
}
