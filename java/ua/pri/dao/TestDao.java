package ua.pri.dao;

import java.util.List;

import ua.pri.ent.Account;
import ua.pri.ent.Test;

public interface TestDao extends AbstractDao<Test>{

	public abstract Test loadTest(int id_test);
	
	public abstract List<Test> getList(Account author);

	public abstract void merge(Test t);
	
	public abstract void persist(Test t);
	
	public abstract List<Test> getList(int offset, int limit);

}