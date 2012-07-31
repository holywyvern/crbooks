package org.crsystems.crbooks.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.crsystems.crbooks.application.CRBooks;
import org.crsystems.crbooks.application.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;


public abstract class ModelBase<T, PK extends Serializable> implements IDatabaseModel<T, PK>, Serializable {
	
	public ModelBase() {
		
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean save() {
		T object = (T)this;
		Session session = null;
		if (CRBooks.getCurrentSession() != null) {
			if (CRBooks.getCurrentSession().getSession() == null) {
				CRBooks.getCurrentSession().setSession(HibernateUtil.getSessionFactory().openSession());
			}
			session = CRBooks.getCurrentSession().getSession();
		}
		else {
			session = HibernateUtil.getSessionFactory().openSession();	
		}
        Transaction transaction = null;
        boolean saved = false;
        try {
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
            saved = true;
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }		
        session.close();
        if (CRBooks.getCurrentSession() != null) CRBooks.getCurrentSession().setSession(null);
		return saved;
	}
	
	@SuppressWarnings("unchecked")
	public boolean update() {
		T object = (T)this;
		Session session = null;
		if (CRBooks.getCurrentSession() != null) {
			if (CRBooks.getCurrentSession().getSession() == null) {
				CRBooks.getCurrentSession().setSession(HibernateUtil.getSessionFactory().openSession());
			}
			session = CRBooks.getCurrentSession().getSession();
		}
		else {
			session = HibernateUtil.getSessionFactory().openSession();	
		}
        Transaction transaction = null;
        boolean saved = false;
        try {
            transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
            saved = true;
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }	
        session.close();
        if (CRBooks.getCurrentSession() != null) CRBooks.getCurrentSession().setSession(null);        
		return saved;
	}
	
	@SuppressWarnings("unchecked")
	public boolean delete() {
		T object = (T)this;
		Session session = null;
		if (CRBooks.getCurrentSession() != null) {
			if (CRBooks.getCurrentSession().getSession() == null) {
				CRBooks.getCurrentSession().setSession(HibernateUtil.getSessionFactory().openSession());
			}
			session = CRBooks.getCurrentSession().getSession();
		}
		else {
			session = HibernateUtil.getSessionFactory().openSession();	
		}
        Transaction transaction = null;
        boolean saved = false;
        try {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
            saved = true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }		
        session.close();
        if (CRBooks.getCurrentSession() != null) CRBooks.getCurrentSession().setSession(null);        
		return saved;
	}
	
	@SuppressWarnings("unchecked")
	public boolean saveOrUpdate() {
		T object = (T)this;
		Session session = null;
		if (CRBooks.getCurrentSession() != null) {
			if (CRBooks.getCurrentSession().getSession() == null) {
				CRBooks.getCurrentSession().setSession(HibernateUtil.getSessionFactory().openSession());
			}
			session = CRBooks.getCurrentSession().getSession();
		}
		else {
			session = HibernateUtil.getSessionFactory().openSession();	
		}
        Transaction transaction = null;
        boolean saved = false;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
            saved = true;
        } catch (HibernateException e) {
        	if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }	
        session.close();
        if (CRBooks.getCurrentSession() != null) CRBooks.getCurrentSession().setSession(null);       
		return saved;
	}
	
	@SuppressWarnings("unchecked")
	public static <K, PK extends Serializable> K getByID(Class<K> tclass, Class<PK> pkclass, PK key) {
		Session session = null;
		if (CRBooks.getCurrentSession() != null) {
			if (CRBooks.getCurrentSession().getSession() == null) {
				CRBooks.getCurrentSession().setSession(HibernateUtil.getSessionFactory().openSession());
			}
			session = CRBooks.getCurrentSession().getSession();
		}
		else {
			session = HibernateUtil.getSessionFactory().openSession();	
		}
		Object o = null;
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			o = session.get(tclass, key);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) transaction.rollback();
            e.printStackTrace();	
		}		
		session.close();
        if (CRBooks.getCurrentSession() != null) CRBooks.getCurrentSession().setSession(null);		
		return (K)o;
	}
	
	@SuppressWarnings("unchecked")
	public static <T, PK> List<T> getAll(Class<T> klass, Class<PK> pkclass, String tableName) {
		Session session = null;
		if (CRBooks.getCurrentSession() != null) {
			if (CRBooks.getCurrentSession().getSession() == null) {
				CRBooks.getCurrentSession().setSession(HibernateUtil.getSessionFactory().openSession());
			}
			session = CRBooks.getCurrentSession().getSession();
		}
		else {
			session = HibernateUtil.getSessionFactory().openSession();	
		}
		Transaction transaction = null;
		List<T> list = null;
		try {
			transaction = session.beginTransaction();
			list = session.createQuery(String.format("from %s", tableName)).list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) transaction.rollback();
            e.printStackTrace();	
		}
		session.close();
        if (CRBooks.getCurrentSession() != null) CRBooks.getCurrentSession().setSession(null);		
		return list;	
	}

	public boolean saveOrUpdateNoTransaction() {
		T object = (T)this;
		Session session = null;
		if (CRBooks.getCurrentSession() != null) {
			if (CRBooks.getCurrentSession().getSession() == null) {
				CRBooks.getCurrentSession().setSession(HibernateUtil.getSessionFactory().openSession());
			}
			session = CRBooks.getCurrentSession().getSession();
		}
		else {
			session = HibernateUtil.getSessionFactory().openSession();	
		}		
		session.saveOrUpdate(object);	
		return true;
	}		
	
	
	protected abstract String getTableName();
	
	@SuppressWarnings("unchecked")
	public static <D>  List<D> getByCriterion(Class<D> klass, Criterion ... crits) {
		Session session = null;
		if (CRBooks.getCurrentSession() != null) {
			if (CRBooks.getCurrentSession().getSession() == null) {
				CRBooks.getCurrentSession().setSession(HibernateUtil.getSessionFactory().openSession());
			}
			session = CRBooks.getCurrentSession().getSession();
		}
		else {
			session = HibernateUtil.getSessionFactory().openSession();	
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(klass);
			for (Criterion c : crits) {
				criteria.add(c);
			}
			List<D> list = (List<D>) criteria.list();
			transaction.commit();
			return list;
		} catch (HibernateException e) {
			if (transaction != null) transaction.rollback();
            e.printStackTrace();	
		}	
		session.close();
        if (CRBooks.getCurrentSession() != null) CRBooks.getCurrentSession().setSession(null);			
		return null;
	}
	
	public abstract boolean isValid();
	public abstract Map<String, String> getErrorFields();
	
}
