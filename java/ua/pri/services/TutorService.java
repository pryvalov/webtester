package ua.pri.services;

import java.util.List;

import ua.pri.ent.Account;
import ua.pri.ent.Test;

public interface TutorService {

	public abstract List<Test> getUserTests(Account account);

	public abstract List<Test> getAllTests();

	public abstract List<Test> getAllTests(int offset, int limit);

	public abstract Test loadTest(int id_test);

	public abstract Test createTest();

	public abstract Test createTest(String name, String subject, Account author);

	public abstract void saveTest(Test test);

	public abstract void updateTest(Test test);

	public abstract void persistTest(Test test);

	public abstract void mergeTest(Test test);

}