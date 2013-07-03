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

import com.stuff.stuffapp.domain.CurrentFlow;
import com.stuff.stuffapp.domain.Stuff;
import com.stuff.stuffapp.domain.StuffFlow;

@Repository
public class CurrentFlowDaoImpl implements CurrentFlowDao {

	private static Logger log = Logger.getLogger(CurrentFlowDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(CurrentFlow curFlow) {
		// 1. find old, compare, update.
		CurrentFlow find = getCurFlowByStuff(curFlow.getStuff());
		if (find == null) {
			saveNewEntity(curFlow);
		} else {
			if (find.getFlow().getSendDate().before(curFlow.getFlow().getSendDate())) {
				delete(find);
				saveNewEntity(curFlow);
			}
		}

	}

	private void saveNewEntity(CurrentFlow curFlow) {
		try {
			Session session = sessionFactory.openSession();
			Transaction t = session.beginTransaction();
			session.saveOrUpdate(curFlow);
			session.flush();
			t.commit();
			session.close();
		} catch (HibernateException exc) {
			log.error("error save current_flow", exc);
		}
	}
	
	private void delete(CurrentFlow flow) {
		try {
			Session session = sessionFactory.openSession();
			Transaction t = session.beginTransaction();
			session.delete(flow);
			session.flush();
			t.commit();
			session.close();
		} catch (HibernateException exc) {
			log.error("error delete current_flow", exc);
		}
	}

	@Override
	public CurrentFlow retrive(Long id) {
		throw new RuntimeException("Not implemented yet.");
	}

	@Override
	public CurrentFlow getCurFlowByStuff(Stuff stuff) {
		if (stuff == null) {
			return null;
		}

		Session session = sessionFactory.getCurrentSession();
		List<CurrentFlow> listCurFlows = null;
		try {
			Query q = session.createQuery("from CurrentFlow where stuff.regNumber = :stuffN and "
					+ "stuff.year = :stuffsYear and " + "stuff.type = :stuffsType");
			q.setString("stuffN", stuff.getRegNumber());
			q.setInteger("stuffsType", stuff.getType());
			q.setInteger("stuffsYear", stuff.getYear());
			listCurFlows = q.list();
			session.flush();
		} catch (HibernateException e) {
			log.error("findFlowsByStuff error", e);
		}
		if (listCurFlows == null || listCurFlows.size() != 1) {
			log.error("Error current flow table data for Stuff ID=" + stuff.getId());
			return null;
		}
		return listCurFlows.get(0);
	}

	@Override
	public StuffFlow getFlowByStuff(Stuff stuff) {
		if (stuff == null) {
			return null;
		}

		Session session = sessionFactory.getCurrentSession();
		List<CurrentFlow> listCurFlows = null;
		try {
			Query q = session.createQuery("from CurrentFlow where stuff.regNumber = :stuffN and "
					+ "stuff.year = :stuffsYear and " + "stuff.type = :stuffsType");
			q.setString("stuffN", stuff.getRegNumber());
			q.setInteger("stuffsType", stuff.getType());
			q.setInteger("stuffsYear", stuff.getYear());
			listCurFlows = q.list();
			session.flush();
		} catch (HibernateException e) {
			log.error("findFlowsByStuff error", e);
		}
		if (listCurFlows == null || listCurFlows.size() != 1) {
			log.error("Error current flow table data for Stuff ID=" + stuff.getId());
			return null;
		}
		return listCurFlows.get(0).getFlow();
	}

}
