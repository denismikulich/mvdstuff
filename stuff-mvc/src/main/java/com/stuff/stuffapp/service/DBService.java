package com.stuff.stuffapp.service;

import java.security.KeyStore.Builder;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.stuff.stuffapp.dao.CurrentFlowDao;
import com.stuff.stuffapp.dao.StuffDao;
import com.stuff.stuffapp.dao.StuffFlowDao;
import com.stuff.stuffapp.data.FlowBO;
import com.stuff.stuffapp.data.StuffBO;
import com.stuff.stuffapp.data.UserBO;
import com.stuff.stuffapp.domain.CurrentFlow;
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
	@Autowired
	CurrentFlowDao curFlowDao;
	

	private static Logger log = Logger.getLogger(DBService.class.getName());

	public StuffBO saveStuff(final StuffBO stuff) {
		Stuff entity = DataBuilder.buildStuffEntity(stuff);
		stuffDao.saveStuff(entity);
		StuffBO result = DataBuilder.buildStuffBo(entity);
		return result;
	}
	
	public StuffBO getStuff(StuffSearchCriteria criteria) {
		Stuff entity = getStuff(criteria.getStuffNumber(), criteria.getType(), criteria.getStuffsYear());
		return DataBuilder.buildStuffBo(entity);
	}

	private Stuff getStuff(String regNo, int type, int year) {
		return stuffDao.findStuff(regNo, type, year);
	}
	
	private Stuff getStuff(StuffBO bo) {
		return getStuff(bo.getRegNumber(), bo.getType().getIntValue(), bo.getYear());
	}

	public Boolean isStuffExist(StuffBO stuff) {
		Stuff result = getStuff(stuff);
		return result != null;
	}
	
	public List<FlowBO> getStuffHistory(StuffBO stuff) {
		Stuff stuffEntity = getStuff(stuff);
		if (stuffEntity == null) {
			return null;
		}
		List<StuffFlow> history = flowDao.getStuffHistory(stuffEntity.getId());
		List<FlowBO> historyBo = new ArrayList<FlowBO>();
		for (StuffFlow entity : history) {
			historyBo.add( DataBuilder.buildFlowBo(entity));
		}
		return historyBo;
	}

	public List<FlowBO> getStuffHistory(StuffSearchCriteria criteria) {
		StuffBO stuff = getStuff(criteria);
		if (stuff != null) {
			return getStuffHistory(stuff);
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
		User userEntity = this.getAuthorizedUser();
		// 2. save stuff
		Stuff stuffEntity = getStuff(flow.getStuff().getRegNumber(), flow.getStuff().getType()
				.getIntValue(), flow.getStuff().getYear());
		if (stuffEntity == null) {
			stuffEntity = stuffDao.saveStuff(DataBuilder.buildStuffEntity(flow.getStuff()));
		}
		// 3. save Flow
		StuffFlow flowEntity = DataBuilder.buildFlowEntity(flow);
		flowEntity.setStuff(stuffEntity);
		flowEntity.setUser(userEntity);
		flowDao.saveFlow(flowEntity);
		
		// 4. save currFlow
		CurrentFlow curFlow = new CurrentFlow();
		curFlow.setFlow(flowEntity);
		curFlow.setStuff(stuffEntity);
		curFlowDao.save(curFlow);
	}

	public FlowBO getLastFlow(StuffBO stuff) {
		Stuff stuffEntity = getStuff(stuff);
		StuffFlow flowEnitiy = curFlowDao.getFlowByStuff(stuffEntity);
		return DataBuilder.buildFlowBo(flowEnitiy);
	}

}
