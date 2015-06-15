package ua.pri.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ua.pri.dao.AccountVerificationDao;
import ua.pri.ent.Account;
import ua.pri.ent.AccountVerification;

public class AccountVerificationDaoImpl extends AbstractDaoImpl<AccountVerification> implements AccountVerificationDao {

	@Override
	protected Class<AccountVerification> entityClass() {
		// TODO Auto-generated method stub
		return AccountVerification.class;
	}
	public AccountVerification findByAccount(Account acc){
		
		return (AccountVerification) select(new Callback() {
			
			@Override
			public Object invoke(Session s) {
				Criteria c = s.createCriteria(entityClass());
				c.add(Restrictions.eq("account", acc));
				Object o = c.uniqueResult();
				return  o;
			}
		});
	}

}
