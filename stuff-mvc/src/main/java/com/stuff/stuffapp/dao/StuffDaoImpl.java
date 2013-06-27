package com.stuff.stuffapp.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stuff.stuffapp.data.StuffType;
import com.stuff.stuffapp.domain.Stuff;
import com.stuff.stuffapp.formbean.StuffSearchCriteria;

@Repository
public class StuffDaoImpl implements StuffDao {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Stuff saveStuff(Stuff stuff) {
		try {
			Session session = sessionFactory.openSession();
			Transaction t = session.beginTransaction();
			stuff = (Stuff) session.merge(stuff);
			session.saveOrUpdate(stuff);
			t.commit();
			session.close();
			log.debug("save stuff" + stuff.getRegNumber());
			return stuff;
		} catch (HibernateException exc) {
			log.error("save stuff error", exc);
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Stuff> stuffList() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query q = session.createQuery("from Stuff");
			List<Stuff> list = q.list();
			session.flush();
			return list;
		} catch (HibernateException e) {
			log.error("stuff list error", e);
			return null;
		}
	}

	@Override
	public void removeStuff(Long id) {
		throw new RuntimeException("Not implemented yet.");
	}

	@Override
	@Transactional
	public Stuff retriveStuff(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		return (Stuff) session.get(Stuff.class, id);
	}

	@Override
	@Transactional
	@Deprecated
	public Stuff findStuff(String regNumber, int year) {
		Session session = sessionFactory.getCurrentSession();
		List<Stuff> stuffs = null;
		try {
			Query q = session
					.createQuery("from Stuff where regNumber = :regN and year = :regY");
			q.setString("regN", regNumber);
			q.setInteger("regY", year);
			stuffs = q.list();
			session.flush();
		} catch (HibernateException ex) {
			log.error("error find user", ex);
		}
		if (stuffs.size() == 0) {
			return null;
		} else {
			return stuffs.get(0);
		}
	}

	@Override
	@Transactional
	public Stuff findStuff(String regNumber, int type, int year) {
		Session session = sessionFactory.getCurrentSession();
		List<Stuff> stuffs = null;
		try {
			Query q = session
					.createQuery("from Stuff where regNumber = :regN and type = :type " +
							"and year = :regY");
			q.setString("regN", regNumber);
			q.setInteger("regY", year);
			q.setInteger("type", type);
			stuffs = q.list();
			session.flush();
		} catch (HibernateException ex) {
			log.error("error find user", ex);
		}
		if (stuffs.size() == 0) {
			return null;
		} else {
			return stuffs.get(0);
		}
	}

}
