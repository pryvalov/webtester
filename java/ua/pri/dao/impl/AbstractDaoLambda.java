package ua.pri.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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

public abstract class AbstractDaoLambda<T> implements AbstractDao<T> {
	protected static final Logger logger = LogManager.getLogger(AbstractDaoLambda.class);
	protected final SessionFactory SF = SFactory.getSessionFactory();

	protected abstract Class<T> entityClass();

	protected interface Callback {
		Object invoke(Session s);
	}

	protected final Object transaction(Consumer<Session> cons) {
		Session s = SF.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			cons.accept(s);;
			tx.commit();
			return null;
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
		Session s = SF.openSession();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see sourceit.dao.impl.AbstractDaoInterface#save(T)
	 */
	@Override
	public void save(T e) {

		transaction(s -> s.save(e));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sourceit.dao.impl.AbstractDaoInterface#update(T)
	 */
	@Override
	public void update(T e) {
		transaction(s -> s.update(e));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sourceit.dao.impl.AbstractDaoInterface#delete(T)
	 */
	@Override
	public void delete(T e) {
		transaction(s -> s.delete(e));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sourceit.dao.impl.AbstractDaoInterface#findById(java.io.Serializable)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T findById(Serializable id) {
		// Session s = SF.openSession();
		// s.beginTransaction();
		// Criteria c = s.createCriteria(entityClass());
		// c.add(Restrictions.idEq(id));
		// Object o = c.uniqueResult();
		// s.flush();
		// s.close();
		// return (T) o;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see sourceit.dao.impl.AbstractDaoInterface#getList()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> getList() {
		// List<T> list = new ArrayList<>();
		// Session s = SF.openSession();
		//
		// s.beginTransaction();
		// Query q = s.createQuery("from " + entityClass().getName());
		// list = (List<T>) q.list();
		// s.getTransaction().commit();
		// s.close();
		// return list;
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
