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

import com.stuff.stuffapp.domain.User;

@Repository
public class UserDaoImpl implements UserDao {

	Logger log = Logger.getLogger(getClass());
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void saveUser(User user) {
		try {
		Session session = sessionFactory.openSession();
Transaction t = session.beginTransaction();
		session.saveOrUpdate(user);
		session.flush();
t.commit();
		session.close();
		} catch (HibernateException exc) {
			log.error("error save user", exc);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> userList() {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from User");
		List<User> list = q.list();
		return list;
	}

	@Override
	public void removeUser(Long id) {
		User user = retriveUser(id);
		deleteUser(user);
	}
	
	@Transactional(readOnly = false)
	private void deleteUser(User user) {
		try {
		Session session = sessionFactory.openSession();
Transaction t = session.beginTransaction();
		session.delete(user);
		session.flush();
t.commit();
		session.close();
		} catch (HibernateException e) {
			log.error("error delete user", e);
		}
	}

	@Override
	public User retriveUser(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (User) session.get(User.class, id);
	}

	@Override
	public List<User> findUsersByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		List<User> list = null;
		try {
		Query q = session.createQuery("from User where name = :name").setString("name", name);
		list = q.list();
		} catch (HibernateException ex) {
			log.error("error find user", ex);
		}
		return list;
	}

}
