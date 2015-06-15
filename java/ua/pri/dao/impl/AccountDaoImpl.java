package ua.pri.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ua.pri.dao.AccountDao;
import ua.pri.ent.Account;

public class AccountDaoImpl extends AbstractDaoImpl<Account> implements
		AccountDao {

	@Override
	protected Class<Account> entityClass() {
		return Account.class;
	}

	@Override
	public void update(Account a) {
		transaction(new Callback() {

			@Override
			public Object invoke(Session s) {
				a.setUpdated(new Date());
				s.update(a);
				return null;
			}
		});

	}

	@Override
	public Account findById(Serializable id) {

		return (Account) select(new Callback() {

			@Override
			public Object invoke(Session s) {
				Criteria c = s.createCriteria(Account.class);
				c.add(Restrictions.idEq(id));
				c.setFetchMode("accountRoles", FetchMode.JOIN);
				return c.uniqueResult();
			}
		});

	}

	@Override
	public Account findByLogin(String _login) {

		return (Account) select(new Callback() {

			@Override
			public Object invoke(Session s) {
				Criteria c = s.createCriteria(Account.class);
				c.add(Restrictions.eq("login", _login));
				return c.uniqueResult();
			}
		});

	}

	@Override
	public Account findByEmail(String _email) {

		return (Account) select(new Callback() {

			@Override
			public Object invoke(Session s) {
				Criteria c = s.createCriteria(Account.class);
				c.add(Restrictions.eq("email", _email));
				return c.uniqueResult();
			}
		});

	}

	@Override
	public void setActive(int id) {

		transaction(new Callback() {
			@Override
			public Object invoke(Session s) {
				Criteria c = s.createCriteria(Account.class);
				c.add(Restrictions.idEq(id));
				Account a = (Account) c.uniqueResult();
				a.setActive(true);
				s.update(a);
				return null;
			}
		});

	}

	@Override
	public void setEmailVerified(int id) {

		transaction(new Callback() {
			@Override
			public Object invoke(Session s) {
				Criteria c = s.createCriteria(Account.class);
				c.add(Restrictions.idEq(id));
				Account a = (Account) c.uniqueResult();
				a.setEmailVerified(true);
				s.update(a);
				return null;
			}
		});

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Account> getList(int offset, int limit) {

		return (List<Account>) select(new Callback() {

			@Override
			public Object invoke(Session s) {
				Query q = s.createQuery("from Account");
				q.setFirstResult(offset);
				q.setMaxResults(limit);
				return q.list();
			}
		});
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Account> getList(int offset, int limit, String orderBy,
			boolean asc) {

		return (List<Account>) select(new Callback() {
			@Override
			public Object invoke(Session s) {
				Query q = null;
				if (asc)
					q = s.createQuery("from Account order by " + orderBy
							+ " asc");
				else
					q = s.createQuery("from Account order by " + orderBy
							+ " desc");

				q.setFirstResult(offset);
				q.setMaxResults(limit);
				return q.list();

			}
		});
	}

}
