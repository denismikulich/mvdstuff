package com.stuff.stuffapp.service;

import com.stuff.stuffapp.data.FlowBO;
import com.stuff.stuffapp.data.StuffBO;
import com.stuff.stuffapp.data.StuffState;
import com.stuff.stuffapp.data.StuffType;
import com.stuff.stuffapp.data.UserBO;
import com.stuff.stuffapp.domain.Stuff;
import com.stuff.stuffapp.domain.StuffFlow;
import com.stuff.stuffapp.domain.User;

public class DataBuilder {

	public static UserBO buildUserBo(final User userEntity) {
		UserBO userBo = new UserBO();
		userBo.setFirstName(userEntity.getFirstname());
		userBo.setLastName(userEntity.getLastname());
		userBo.setLogin(userEntity.getName());
		userBo.setRoleDescription(userEntity.getRole().getName());
		return userBo;
	}
	
	/**
	 * fill fields: login, firstname, lastname.
	 * @param bo
	 * @return filled object.
	 */
	public static User buildUserEntity(final UserBO bo) {
		User entity = new User();
		entity.setName(bo.getLogin());
		entity.setFirstname(bo.getFirstName());
		entity.setLastname(bo.getLastName());
		return entity;
	}

	public static StuffBO buildStuffBo(final Stuff entity) {
		if (entity == null) {
			return null;
		}
		StuffBO bo = new StuffBO();
		bo.setRegNumber(entity.getRegNumber());
		bo.setType(StuffType.valueOf(entity.getType()));
		bo.setYear(entity.getYear());
		return bo;
	}

	public static Stuff buildStuffEntity(final StuffBO bo) {
		if (bo == null) {
			return null;
		}
		Stuff entity = new Stuff();
		entity.setRegNumber(bo.getRegNumber());
		entity.setType(bo.getType().getIntValue());
		entity.setYear(bo.getYear());
		return entity;
	}

	/**
	 * build entity without fields user and stuff.
	 * @param bo
	 * @return
	 */
	public static StuffFlow buildFlowEntity(final FlowBO bo) {
		if (bo == null) {
			return null;
		}
		StuffFlow flowEntity = new StuffFlow();
		flowEntity.setSender(bo.getSender());
		flowEntity.setReciever(bo.getRecipient());
		//flowEntity.setStuff(buildStuffEntity(bo.getStuff()));
		flowEntity.setSendNumber(bo.getOutgoingNo());
		flowEntity.setSendDate(bo.getOutgoingDate());
		flowEntity.setSign(bo.getSignature());
		flowEntity.setDescription(bo.getDescription());
		//flowEntity.setUser( buildUserEntity( bo.getUser() ));
		
		return flowEntity;
	}
	
	public static FlowBO buildFlowBo(final StuffFlow entity) {
		if (entity == null) {
			return null;
		}
		FlowBO bo = new FlowBO();
		bo.setSender(entity.getSender());
		bo.setRecipient(entity.getReciever());
		bo.setStuff( buildStuffBo(entity.getStuff()));
		bo.setOutgoingNo(entity.getSendNumber());
		bo.setOutgoingDate(entity.getSendDate());
		bo.setSignature(entity.getSign());
		bo.setDescription(entity.getDescription());
		bo.setUser(buildUserBo(entity.getUser()));
		return bo;
	}
}
