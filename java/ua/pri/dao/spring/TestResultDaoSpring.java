package ua.pri.dao.spring;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;







import ua.pri.dao.TestResultDao;
import ua.pri.ent.Account;
import ua.pri.ent.TestResult;

@Repository("testResultDao")
public class TestResultDaoSpring extends AbstractDaoSpring<TestResult> implements
		TestResultDao {


	protected Class<TestResult> entityClass() {
		// TODO Auto-generated method stub
		return TestResult.class;
	}

		@SuppressWarnings("unchecked")
		public List<TestResult> getUserReults(Account account){
			Criteria c = getSession().createCriteria(entityClass()).add(Restrictions.eq("account", account));
			c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return c.list();
		}


}
