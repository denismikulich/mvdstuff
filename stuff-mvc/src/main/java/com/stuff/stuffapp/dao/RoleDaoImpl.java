package com.stuff.stuffapp.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stuff.stuffapp.domain.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	Logger log = Logger.getLogger(getClass());
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public void saveRole(Role role) {
		try {
		Session session = sessionFactory.openSession();
Transaction t = session.beginTransaction();
		session.save(role);
		session.flush();
t.commit();
		session.close();
		log.debug("save role"+role.getName());
		} catch (HibernateException exc) {
			log.error("save role error", exc);
		}
	}

	@Transactional
	@Override
	public List<Role> roleList() {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Role");
		List<Role> list = null;
		try {
			list = q.list();
		} catch (Exception e) {
			log.error("role list error", e);
		}
		return list;
	}

	@Override
	public void removeRole(Long id) {
		throw new RuntimeException("Not implemented yet.");
	}

	@Transactional
	@Override
	public Role retriveRole(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Role) session.get(Role.class, id);
	}

}
