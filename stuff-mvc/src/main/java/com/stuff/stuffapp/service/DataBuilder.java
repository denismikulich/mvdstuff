package com.stuff.stuffapp.service;

import com.stuff.stuffapp.data.UserBO;
import com.stuff.stuffapp.domain.User;

public class DataBuilder {

	public static UserBO buildUserBo(User userEntity) {
		UserBO userBo = new UserBO();
		userBo.setFirstName(userEntity.getFirstname());
		userBo.setLastName(userEntity.getLastname());
		userBo.setLogin(userEntity.getName());
		userBo.setRoleDescription(userEntity.getRole().getName());
		return userBo;
	}
}
