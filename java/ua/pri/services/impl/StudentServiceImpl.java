package ua.pri.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;










import ua.pri.dao.AnswerDao;
import ua.pri.dao.TestDao;
import ua.pri.dao.TestResultDao;
import ua.pri.ent.Account;
import ua.pri.ent.Answer;
import ua.pri.ent.Test;
import ua.pri.ent.TestResult;
import ua.pri.services.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(StudentServiceImpl.class);
	@Autowired
	TestDao testDao;
	
	@Autowired
	AnswerDao answerDao;
	
	@Autowired
	TestResultDao testResultDao;
	
	@Override
	public List<Test> listTests(){
		
		return testDao.getList();
		
	}
	@Override
	public List<Test> listTests(int offset, int limit){
		
		return testDao.getList(offset, limit);
		
	}
	
	@Override
	public Test loadTest(int id_test){
		return testDao.loadTest(id_test);
		
	}
	
	public TestResult makeTestResult(Account student, Test test){
		TestResult testResult = new TestResult();
		testResult.setAccount(student);;
		testResult.setTest(test);
		return testResult;
	}
	
	public int checkAnswer(Map<String, String> params){
		int score = 0;
		for(Map.Entry<String, String> entry : params.entrySet())
		{
			if(!entry.getValue().equals("-1")){
			Answer answer = answerDao.findById(Integer.valueOf(entry.getValue()));
			if(answer.isCorrect())
				score++;
			else
				score--;
			}
			else {score--;}
			
		}
			
		
		
		return score;
		
	}
	public void saveResult(TestResult result, int score){
		result.setDate(new Date());
		result.setScore(" "+score);
		testResultDao.save(result);
	}
	
	
	public List<TestResult> getUesrResults(Account account){
		return testResultDao.getUserReults(account);
	}
	
	
}
