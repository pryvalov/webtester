package ua.pri.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.pri.dao.AbstractDao;

@Repository
@Transactional
public abstract class AbstractDaoSpring<T> implements AbstractDao<T> {
	protected static final Logger logger = LogManager.getLogger(AbstractDaoImpl.class);
	@Autowired
	protected SessionFactory sessionFactory;// = SFactory.getSessionFactory();
	
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	protected abstract Class<T> entityClass();


	

	@Override
	public void save(T e) {
		getSession().save(e);
	}


	@Override
	public void update(T e) {
		getSession().update(e);
	}


	@Override
	public void delete(T e) {
		getSession().delete(e);
	}


	@Override
	@SuppressWarnings("unchecked")
	public T findById(Serializable id) {

		return (T) getSession().createCriteria(entityClass()).add(Restrictions.idEq(id)).uniqueResult();
			
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> getList() {
		return (List<T>) getSession().createQuery("from "+entityClass().getName()).list();

	}

}
