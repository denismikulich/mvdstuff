package com.stuff.stuffapp.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.stuff.stuffapp.dao.StuffDao;
import com.stuff.stuffapp.dao.StuffFlowDao;
import com.stuff.stuffapp.data.FlowBO;
import com.stuff.stuffapp.data.StuffBO;
import com.stuff.stuffapp.data.StuffType;
import com.stuff.stuffapp.data.UserBO;
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
		return getStuff(criteria.getStuffNumber(), criteria.getType(), criteria.getStuffsYear());
	}

	private Stuff getStuff(String regNo, int type, int year) {
		return stuffDao.findStuff(regNo, type, year);
	}
	
	public Boolean isStuffExist(StuffBO stuff) {
		Stuff result = stuffDao.findStuff(stuff.getRegNumber(), stuff.getType().getIntValue(), stuff.getYear());
		return result!=null;
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

	/**
	 * return current authorized user.
	 * 
	 * @return
	 * @throws StuffBusinessException
	 */
	public UserBO getCurrentUser() throws StuffBusinessException {
		User userEntity = getAuthorizedUser();
		return DataBuilder.buildUserBo(userEntity);
	}

	private User getAuthorizedUser() throws StuffBusinessException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDet = (UserDetailsImpl) auth.getPrincipal();
		User userEntity = userDet.getUser();
		if (userEntity == null) {
			log.error("current user is null");
			throw new StuffBusinessException("current user is null");
		}
		return userEntity;
	}

	public void saveFlow(FlowBO flow) throws StuffBusinessException {
		// 1. check user
		// 2. save stuff

		// 3. save Flow
		// 4. save currFlow
	}

}
