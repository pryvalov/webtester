package ua.pri.dao.spring;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.pri.dao.AccountDao;
import ua.pri.ent.Account;

@Repository("accountDao")
@Transactional(readOnly=false)
public class AccountDaoSpring extends AbstractDaoSpring<Account> implements
		AccountDao {

	


	@Override
	public Account findByLogin(String _login) {
		return (Account) getSession().createCriteria(entityClass()).add(Restrictions.eq("login", _login)).uniqueResult();
	}

	@Override
	public Account findByEmail(String _email) {
		return (Account) getSession().createCriteria(entityClass()).add(Restrictions.eq("email", _email)).uniqueResult();
	}

	@Override
	public void setActive(int id) {
		((Account)getSession().get(Account.class, id)).setActive(true);
	}

	@Override
	public void setEmailVerified(int id) {
		((Account)getSession().get(Account.class, id)).setEmailVerified(true);
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Account> getList() {
		Criteria c = getSession().createCriteria(entityClass());
		c.addOrder(Order.asc("accountId"));
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Account>) c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getList(int offset, int limit) {
		// TODO Auto-generated method stub
		return (List<Account>)getSession().createCriteria("Account")
				.setFirstResult(offset)
				.setMaxResults(limit).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getList(int offset, int limit, String orderBy,
			boolean asc) {
		if(asc)
		return (List<Account>)getSession().createQuery("from Account order by " + orderBy + " asc")
				.setFirstResult(offset)
				.setMaxResults(limit).list();
		else
			return (List<Account>)getSession().createQuery("from Account order by " + orderBy + " desc")
					.setFirstResult(offset)
					.setMaxResults(limit).list();
	}





	@Override
	protected Class<Account> entityClass() {
		return Account.class;
	}

}
