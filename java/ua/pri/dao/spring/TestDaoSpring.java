package ua.pri.dao.spring;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ua.pri.dao.TestDao;
import ua.pri.ent.Account;
import ua.pri.ent.Test;

@Repository("testDao")
public class TestDaoSpring extends AbstractDaoSpring<Test> implements TestDao {
	
	private Logger LOGGER = LogManager.getLogger(TestDaoSpring.class);
	
	@Override
	public void save(Test e) {
		e.setQuestionsSize(e.getQuestions().size()+1);//TODO: find why size is 1 less than it is actually
		LOGGER.debug("String: "+e.getQuestionsSize()+" size(): "+e.getQuestions().size());
		getSession().save(e);
	}


	@Override
	public void update(Test e) {
		e.setQuestionsSize(e.getQuestions().size()+1);//TODO: same
		LOGGER.debug("String: "+e.getQuestionsSize()+" size(): "+e.getQuestions().size());
		getSession().update(e);
	}
	
	

	@Override
	protected Class<Test> entityClass() {
		return Test.class;
	}

	@Override
	public Test loadTest(int id_test) {
		Criteria c = getSession().createCriteria(entityClass());
		c.add(Restrictions.idEq(id_test)).setFetchMode("questions",
				FetchMode.SELECT);
		Test t = (Test) c.uniqueResult();
		//t.getQuestions().size();
		return t;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Test> getList(Account author) {
		Criteria c = getSession().createCriteria(entityClass());
		c.add(Restrictions.eq("author", author));
		c.addOrder(Order.asc("idTest"));
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Test>) c.list();
	}

	@SuppressWarnings("unchecked")
	public List<Test> getList(int offset, int limit) {

		return (List<Test>) getSession().createQuery(
				"from Test offset " + offset + " limit " + limit).list();
	}

	@Override
	public void merge(Test t) {
		getSession().merge(t);
	}

	@Override
	public void persist(Test t) {
		getSession().persist(t);
	}
}
