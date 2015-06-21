package ua.pri.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ua.pri.dao.TestDao;
import ua.pri.ent.Account;
import ua.pri.ent.Test;
import ua.pri.utils.SFactory;

public class TestDaoImpl extends AbstractDaoImpl<Test> implements TestDao {

	@Override
	protected Class<Test> entityClass() {
		return Test.class;
	}

	@Override
	public Test loadTest(int id_test) {
		Session s = SFactory.getSessionFactory().openSession();
		Criteria c = s.createCriteria(Test.class);
		c.add(Restrictions.idEq(id_test));
		Test t = (Test) c.uniqueResult();
		c.setFetchMode("questions", FetchMode.SELECT);
		return t;
	}

	@Override
	public void merge(Test t) {
		transaction(new Callback() {

			@Override
			public Object invoke(Session s) {
				s.merge(t);
				return null;
			}
		});
	}
	
	@Override
	public void persist(Test t) {
		transaction(new Callback() {

			@Override
			public Object invoke(Session s) {
				s.persist(t);
				return null;
			}
		});
	}



	@Override
	public List<Test> getList(int offset, int limit) {
		return null;
	}

	@Override
	public List<Test> getList(Account author) {
		return null;
	}
}
