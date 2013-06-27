package com.stuff.stuffapp.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.stuff.stuffapp.dao.StuffDao;
import com.stuff.stuffapp.dao.StuffFlowDao;
import com.stuff.stuffapp.data.FlowData;
import com.stuff.stuffapp.data.StuffType;
import com.stuff.stuffapp.domain.Stuff;
import com.stuff.stuffapp.domain.StuffFlow;
import com.stuff.stuffapp.domain.User;
import com.stuff.stuffapp.exception.StuffBusinessException;
import com.stuff.stuffapp.formbean.StuffSearchCriteria;
import com.stuff.stuffapp.loginservice.UserDetailsImpl;

@Service
public class DBService {

	@Autowired
	StuffDao stuffDao;
	@Autowired
	StuffFlowDao flowDao;
	
	private static Logger log = Logger.getLogger(DBService.class.getName());

	public Stuff getStuff(StuffSearchCriteria criteria) {
		return stuffDao.findStuff(criteria.getStuffNumber(), StuffType.valueOf(criteria.getType()),
				criteria.getStuffsYear());
	}

	public List<StuffFlow> getStuffHistory(Long stuffID) {
		return flowDao.getStuffHistory(stuffID);
	}

	public List<StuffFlow> getStuffHistory(StuffSearchCriteria criteria) {
		Stuff stuff = getStuff(criteria);
		if (stuff != null) {
			return getStuffHistory(stuff.getId());
		}
		return null;
	}
	
	public User getCurrentUser() throws StuffBusinessException {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetailsImpl userDet = (UserDetailsImpl) auth.getPrincipal();
		User user = userDet.getUser();
		if (user == null) {
			log.error("current user is null");
			throw new StuffBusinessException();
		}
		return user;
	}
	
	public void saveFlow(FlowData flowData) {
		// 1. check user
		// 2. save stuff
		// 3. save Flow
		// 4. save currFlow
	}

}
