package com.stuff.stuffapp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.stuff.stuffapp.formbean.SearchCriteriaBean;

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

	@Override
	public List<StuffFlow> advancedSearch(SearchCriteriaBean criteria) {
		if (criteria == null) {
			return null;
		}

		List<PartQuery> parts = parseCriteria(criteria);
		StringBuffer strQuery = new StringBuffer();
		for (int i=0; i<parts.size(); i++) {
			PartQuery part = parts.get(i);
			if (i!=0) {
				strQuery.append(" and ");
			}
			strQuery.append(part.query);
		}
		
		Session session = sessionFactory.getCurrentSession();
		List<CurrentFlow> listCurFlows = null;
		try {
			Query q = session.createQuery("from CurrentFlow where "+strQuery.toString());
			for (PartQuery part : parts) {
				for (String key : part.parameters.keySet()) {
					q.setParameter(key, part.parameters.get(key));
				}
			}
			listCurFlows = q.list();
			session.flush();
		} catch (HibernateException e) {
			log.error("findFlowsByStuff error", e);
		}

		List<StuffFlow> result = new ArrayList<StuffFlow>();
		for (CurrentFlow currentFlow : listCurFlows) {
			result.add(currentFlow.getFlow());
		}
		return result;
	}
	
	private List<PartQuery> parseCriteria(SearchCriteriaBean criteria) {
		List<PartQuery> result = new ArrayList<CurrentFlowDaoImpl.PartQuery>();
		// Receiver.
		if (criteria.getReciever() != null && !criteria.getReciever().isEmpty()) {
			PartQuery part = new PartQuery();
			part.query = "(flow.reciever like :reciever)";
			part.parameters.put("reciever", "%" + criteria.getReciever() + "%");
			result.add(part);
		}
		// Sender.
		if (criteria.getSender() != null && !criteria.getSender().isEmpty()) {
			PartQuery part = new PartQuery();
			part.query = "(flow.sender like :sender)";
			part.parameters.put("sender", "%" + criteria.getSender() + "%");
			result.add(part);
		}
		// Send Number.
		if (criteria.getSendNumber() != null && !criteria.getSendNumber().isEmpty()) {
			PartQuery part = new PartQuery();
			part.query = "(flow.sendNumber like :sendNumber)";
			part.parameters.put("sendNumber", "%" + criteria.getSendNumber() + "%");
			result.add(part);
		}		
		return result;
	}

	class PartQuery {
		public String query;
		public Map<String, Object> parameters;

		public PartQuery() {
			parameters = new HashMap<String, Object>();
		}

	}

}
