package ua.pri.dao;

import java.util.List;

import ua.pri.ent.Account;
import ua.pri.ent.TestResult;

public interface TestResultDao extends AbstractDao<TestResult> {
	
	
	public abstract List<TestResult> getUserReults(Account account);
}
