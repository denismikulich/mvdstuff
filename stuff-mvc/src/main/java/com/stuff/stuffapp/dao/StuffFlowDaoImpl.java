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

import com.stuff.stuffapp.domain.StuffFlow;
import com.stuff.stuffapp.formbean.SearchCriteriaBean;

@Repository
public class StuffFlowDaoImpl implements StuffFlowDao {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveFlow(StuffFlow flow) {
		try {
			Session session = sessionFactory.openSession();
Transaction t = session.beginTransaction();
			session.saveOrUpdate(flow);
			session.flush();
t.commit();
			session.close();
			log.info("saved flow is: " + flow.getStuff().getRegNumber());
		} catch (HibernateException e) {
			log.info("save flow error", e);
		}
	}

	@Override
	public StuffFlow retriveFlow(Long id) {
		throw new RuntimeException("Not implemented yet.");
	}

	@Override
	@Transactional
	public List<StuffFlow> findFlowsByStuff(String name, Integer year) {
		Session session = sessionFactory.getCurrentSession();
		
		List<StuffFlow> result = null;
		try {
			Query q = session
				.createQuery("from StuffFlow where stuff.regNumber = :stuffN and stuff.year = :stuffsYear"+
						" ORDER BY sendDate DESC, flowId DESC");
			q.setString("stuffN", name);
			q.setInteger("stuffsYear", year);
			result = q.list();
			session.flush();
		} catch (HibernateException e) {
			log.error("findFlowsByStuff error", e);
		}
		return result;
	}
	
	@Transactional
	private List<StuffFlow> simpleSearch(SearchCriteriaBean criteria) {
		Session session = sessionFactory.getCurrentSession();
		
		List<StuffFlow> result = null;
		try {
			Query q = session
				.createQuery("from StuffFlow where stuff.regNumber = :stuffN and stuff.year = :stuffsYear");
		
			q.setString("stuffN", criteria.getStuffNumber());
			q.setInteger("stuffsYear", criteria.getStuffsYear());
			result = q.list();
			session.flush();
		} catch (HibernateException e) {
			log.error("simple search error", e);
		}
		return result;
	}
	
	@Transactional
	private List<StuffFlow> advancedSearch(SearchCriteriaBean criteria) {
		Session session = sessionFactory.getCurrentSession();
		List<StuffFlow> result = null;
		//Build search query.
		StringBuilder searchQuery = new StringBuilder();
		boolean bAnd = false;
		if (criteria.getReciever()!= null && !criteria.getReciever().isEmpty()) {
			searchQuery.append("reciever LIKE :receiver");
			bAnd = true;
		}
		if (criteria.getSender() != null && !criteria.getSender().isEmpty()) {
			if (bAnd) {
				searchQuery.append(" and");
			}
			searchQuery.append(" sender LIKE :sender");
			bAnd = true;
		}
		if (criteria.getSendNumber()!= null && !criteria.getSendNumber().isEmpty()) {
			if (bAnd) {
				searchQuery.append(" and");
			}
			searchQuery.append(" sendNumber LIKE :sendNumber");
			bAnd = true;
		}
		if (criteria.getSendDateStartRange()!=null) {
			if (criteria.getSendDateEndRange()!=null) { //between
				if (bAnd) {
					searchQuery.append(" and");
				}
				searchQuery.append(" ( sendDate BETWEEN :startD and :endD )");
				bAnd = true;
			} else { // >
				searchQuery.append(" sendDate >= :startD");
				bAnd = true;
			}
		}
		if (criteria.getSendDateEndRange()!=null){ // <
			if (bAnd) {
				searchQuery.append(" and");
			}
			searchQuery.append(" sendDate <= :endD");
			bAnd = true;
		}
		try {
			if (bAnd == false) return null;
			Query q = session
				.createQuery("from StuffFlow where "+searchQuery+" ORDER BY stuff.regNumber ASC");
		
			if (criteria.getReciever()!= null && !criteria.getReciever().isEmpty()) {
				q.setString("receiver", '%'+criteria.getReciever()+'%');
			}
			if (criteria.getSender() != null && !criteria.getSender().isEmpty()) {
				q.setString("sender", '%'+criteria.getSender()+'%');
			}
			if (criteria.getSendNumber()!= null && !criteria.getSendNumber().isEmpty()) {
				q.setString("sendNumber", '%'+criteria.getSendNumber()+'%');
			}
			if (criteria.getSendDateStartRange()!=null) {
				q.setDate("startD", criteria.getSendDateStartRange());
			}
			if (criteria.getSendDateEndRange()!=null) {
				q.setDate("endD", criteria.getSendDateEndRange());
			}
			
			result = q.list();
			session.flush();
		} catch (HibernateException e) {
			log.error("Advanced search error", e);
		}
		return result;
	}

	/**
	 * return result list. could be null.
	 */
	@Override
	public List<StuffFlow> searchFlows(SearchCriteriaBean criteria) {
		if (criteria.getStuffNumber()!= null && !criteria.getStuffNumber().isEmpty()) {
			return simpleSearch(criteria);
		} else {
			return advancedSearch(criteria);
		}
	}

	@Override
	@Transactional
	public List<StuffFlow> getStuffHistory(Long stuffID) {
		Session session = sessionFactory.getCurrentSession();
		List<StuffFlow> result = null;
		try {
			Query q = session
				.createQuery("from StuffFlow where stuff.id = :stuffID");
		
			q.setLong("stuffID", stuffID);
			result = q.list();
			session.flush();
		} catch (HibernateException e) {
			log.error("getStuffHistory error", e);
		}
		return result;
	}

}
