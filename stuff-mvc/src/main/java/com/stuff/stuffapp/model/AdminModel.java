package com.stuff.stuffapp.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stuff.stuffapp.dao.RoleDao;
import com.stuff.stuffapp.dao.UserDao;
import com.stuff.stuffapp.domain.Role;
import com.stuff.stuffapp.domain.User;

@Component
public class AdminModel {

	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;
	/**
	 * User showed in Edit form.
	 */
	User editableUser;
	
	public AdminModel() {
		setEditableUserAsFakeNewUser();
	}

	public List<Role> getRoleList() {
		return roleDao.roleList();
	}
	
	public List<User> getUserList() {
		return userDao.userList();
	}

	public User getEditableUser() {
		return editableUser;
	}
	
	/**
	 * Instantiate new User object. Its used on jsp form as sample of fields populating.
	 */
	public void setEditableUserAsFakeNewUser() {
		editableUser = new User();
		editableUser.setName("Login");
		editableUser.setFirstname("John");
		editableUser.setLastname("Fool");
	}
	
	 /**
	 * User showed in Edit form.
	 *
	 * @param userid
	 */
	public void setEditableUserId(long userid) {
		editableUser = userDao.retriveUser(userid);
		if (editableUser == null) {
			editableUser = new User();
		}
	}
	/**
	 * Adding new User object to database.
	 * @return
	 */
	public void submitUser(User user) {
		if (user == null) throw new IllegalArgumentException("user must be not null");
		
		Role role = roleDao.retriveRole(user.getRole().getId());
		
		if (role == null) throw new IllegalArgumentException("Can't find user role");
		
		user.setRole(role);
		
		if (getEditableUser().getId() != null) { // save edited user
			user.setId(getEditableUser().getId());
		}
		
		userDao.saveUser(user);
		
		setEditableUserAsFakeNewUser();
	}
	
	public void deleteUser(Long id) {
		userDao.removeUser(id);
		setEditableUserAsFakeNewUser();
	}

}
