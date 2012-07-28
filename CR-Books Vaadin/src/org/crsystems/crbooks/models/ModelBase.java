package org.crsystems.crbooks.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.crsystems.crbooks.application.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;


public abstract class ModelBase<T, PK extends Serializable> implements IDatabaseModel<T, PK> {

	public ModelBase() {
		
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean save() {
		T object = (T)this;
		Session session = HibernateUtil.getSessionFactory().openSession();
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
        } finally {
            session.close();
        }		
		return saved;
	}
	
	@SuppressWarnings("unchecked")
	public boolean update() {
		T object = (T)this;
		Session session = HibernateUtil.getSessionFactory().openSession();
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
        } finally {
            session.close();
        }				
		return saved;
	}
	
	@SuppressWarnings("unchecked")
	public boolean delete() {
		T object = (T)this;
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean saved = false;
        try {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
            saved = true;
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }				
		return saved;
	}
	
	@SuppressWarnings("unchecked")
	public boolean saveOrUpdate() {
		T object = (T)this;
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean saved = false;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
            saved = true;
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }				
		return saved;
	}
	
	@SuppressWarnings("unchecked")
	public T getByID(PK key) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Object o = null;
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			o = session.get(getClass(), key);
			transaction.commit();
		} catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();	
		} finally {
            session.close();
        }		
		return (T)o;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<T> list = null;
		try {
			transaction = session.beginTransaction();
			list = session.createQuery(String.format("from %s", getTableName())).list();
			transaction.commit();
		} catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();	
		} finally {
            session.close();
        }	
		return list;	
	}
	
	public abstract String getTableName();
	
	@SuppressWarnings("unchecked")
	public static <D>  List<? extends D> getByCriterion(Class<D> klass, Criterion ... crits) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(klass);
			for (Criterion c : crits) {
				criteria.add(c);
			}
			List<? extends D> list = (List<? extends D>) criteria.list();
			transaction.commit();
			return list;
		} catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();	
		} finally {
			session.close();
		}		
		return null;
	}
	
}
