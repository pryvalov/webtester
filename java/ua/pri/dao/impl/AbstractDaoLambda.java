package ua.pri.dao.impl;

import java.io.Serializable;

import java.util.List;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ua.pri.utils.SFactory;

public abstract class AbstractDaoLambda<T> {
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

	protected final Object select(Consumer<Session> cons) {
		Session s = SF.openSession();
		try {
			cons.accept(s);
			return null;
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



	public void save(T e) {

		transaction(s -> s.save(e));
	}



	public void update(T e) {
		transaction(s -> s.update(e));

	}


	public void delete(T e) {
		transaction(s -> s.delete(e));

	}


	
	protected Object obj ;


	@SuppressWarnings("unchecked")
	public T findById(Serializable id) {
		
		
		select(s -> obj=s.createCriteria(entityClass()).add(Restrictions.idEq(id)).uniqueResult());
	
		return (T)obj; 

	}


	@SuppressWarnings("unchecked")
	public List<T> getList() {

		select(s -> obj=s.createQuery("from " + entityClass().getName()).list());
		return (List<T>) obj;


	}

}
