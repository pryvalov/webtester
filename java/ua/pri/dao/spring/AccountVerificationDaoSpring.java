package ua.pri.dao.spring;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ua.pri.dao.AccountVerificationDao;
import ua.pri.ent.Account;
import ua.pri.ent.AccountVerification;
@Repository("accountVerificationDao")
public class AccountVerificationDaoSpring extends AbstractDaoSpring<AccountVerification> implements AccountVerificationDao {

	@Override
	protected Class<AccountVerification> entityClass() {
		// TODO Auto-generated method stub
		return AccountVerification.class;
	}
	@Override
	public AccountVerification findByAccount(Account acc){
		Criteria c = getSession().createCriteria(entityClass());
		c.add(Restrictions.eq("account", acc));
		return (AccountVerification) c.uniqueResult();

	}

}
