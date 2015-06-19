package ua.pri.dao.spring;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import org.springframework.stereotype.Repository;

import ua.pri.dao.TestDao;
import ua.pri.ent.Account;
import ua.pri.ent.Test;

@Repository("testDao")
//@Transactional(readOnly=false)
public class TestDaoSpring extends AbstractDaoSpring<Test> implements TestDao {
	
/*	@Autowired
	AccountDao accountDao;*/
	
	@Override
	protected Class<Test> entityClass() {
		return Test.class;
	}

	@Override
	public Test loadTest(int id_test) {
		Criteria c = getSession().createCriteria(entityClass());
		c.add(Restrictions.idEq(id_test)).setFetchMode("questions", FetchMode.SELECT);
		Test t = (Test) c.uniqueResult();
		t.getQuestions().size();
		return t;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Test> getList(Account author){
		Criteria c = getSession().createCriteria(entityClass());
		c.add(Restrictions.eq("author", author));
		c.addOrder(Order.asc("idTest"));
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Test>) c.list();
 	}
	
	@SuppressWarnings("unchecked")
	public List<Test> getList(int offset, int limit){

		return (List<Test>) getSession().createQuery("from Test offset "+offset+" limit "+limit).list();
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
