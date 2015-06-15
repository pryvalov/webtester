package ua.pri.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ua.pri.dao.AbstractDao;
import ua.pri.utils.SFactory;

public abstract class AbstractDaoImpl<T> implements AbstractDao<T> {
	protected static final Logger logger = LogManager.getLogger(AbstractDaoImpl.class);
	protected final SessionFactory sessionFactory = SFactory.getSessionFactory();

	protected abstract Class<T> entityClass();

	protected interface Callback {
		Object invoke(Session s);
	}

	protected final Object transaction(Callback c) {
		Session s = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			Object res = c.invoke(s);
			tx.commit();
			return res;
		} catch (RuntimeException e) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (Exception e1) {
					logger.error("rollback fail : " + e1.getMessage());
				}
			}
			throw e;
		} finally {
			if (s.isOpen()) {
				try {
					s.flush();
					s.close();
				} catch (HibernateException e) {
					logger.error("error closing session "+e.getMessage());
				}
			}
		}
	}

	protected final Object select(Callback c) {
		Session s = sessionFactory.openSession();
		try {
			return c.invoke(s);
		} finally {
			if (s != null) {
				try {
					s.flush();
					s.close();
				} catch (HibernateException e) {
					logger.error("close session failed: "
							+ e.getMessage());
				}
			}
		}
	}


	@Override
	public void save(T e) {

		transaction(new Callback() {

			@Override
			public Object invoke(Session s) {
				s.save(e);
				return null;
			}
		});
	}


	@Override
	public void update(T e) {
		transaction(new Callback() {

			@Override
			public Object invoke(Session s) {
				s.update(e);
				return null;
			}
		});

	}


	@Override
	public void delete(T e) {
		transaction(new Callback() {

			@Override
			public Object invoke(Session s) {
				s.delete(e);
				return null;
			}
		});

	}


	@Override
	@SuppressWarnings("unchecked")
	public T findById(Serializable id) {

		return (T) select(new Callback() {

			@Override
			public Object invoke(Session s) {
				Criteria c = s.createCriteria(entityClass());
				c.add(Restrictions.idEq(id));
				Object o = c.uniqueResult();
				return o;
			}
		});
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> getList() {

		return (List<T>) select(new Callback() {

			@Override
			public Object invoke(Session s) {
				List<T> list = new ArrayList<>();
				Query q = s.createQuery("from " + entityClass().getName());
				list = q.list();
				return list;

			}
		});

	}

}
