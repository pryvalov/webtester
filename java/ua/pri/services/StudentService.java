package ua.pri.services;

import java.util.List;
import java.util.Map;

import ua.pri.ent.Account;
import ua.pri.ent.Test;
import ua.pri.ent.TestResult;

public interface StudentService {

	public abstract List<Test> listTests();

	public abstract List<Test> listTests(int offset, int limit);

	public abstract Test loadTest(int id_test);
	
	public abstract TestResult makeTestResult(Account student, Test test);
	
	public abstract int checkAnswer(Map<String, String> params);
	
	public abstract void saveResult(TestResult result, int score);
	
	public abstract List<TestResult> getUesrResults(Account account);

}