package com.stuff.stuffapp.loginservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.stuff.stuffapp.dao.RoleDao;
import com.stuff.stuffapp.dao.StuffDao;
import com.stuff.stuffapp.dao.StuffFlowDao;
import com.stuff.stuffapp.dao.UserDao;
import com.stuff.stuffapp.domain.Role;
import com.stuff.stuffapp.domain.Stuff;
import com.stuff.stuffapp.domain.StuffFlow;
import com.stuff.stuffapp.domain.User;

@Component
public class LoginServiceImpl implements UserDetailsService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	StuffDao stuffDao;
	
	@Autowired
	StuffFlowDao stuffFlowDao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		if (username.equals("defaultuser")) {
			
			defaultInit();
		}
		
		List<User> list = userDao.findUsersByName(username);
		return new UserDetailsImpl(list.get(0));

	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	private void defaultInit() {
		List<Role> roleList = roleDao.roleList();
		if (!roleList.isEmpty()) { //if db contain roles - its initialized.
			return;
		}
		initDefaultAdmin();
		// TODO: remove next 2 rows. 
		/*defaultInitUsers();
		defaultInitStuffFlow();*/
	}
	
	private void initDefaultAdmin() {
		defaultInitRoles();
		User user = new User();
		user.setName("defaultuser");
		user.setPassword("password");
		user.setFirstname("default");
		user.setLastname("user");
		user.setRole(roleDao.roleList().get(0));
		userDao.saveUser(user);
	}
	
	private void defaultInitRoles() {
		Role adminRole = new Role();
		adminRole.setName("Admin");
		Role userRole = new Role();
		userRole.setName("User");
		roleDao.saveRole(adminRole);
		roleDao.saveRole(userRole);
	}
	
	private void defaultInitUsers() {
		
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setName("user0"+i);
			user.setPassword("pass0"+i);
			user.setFirstname("first0"+i);
			user.setLastname("last name 0"+i);
			user.setRole(roleDao.roleList().get(1));
			userDao.saveUser(user);
		}
	}
	
	private void defaultInitStuffFlow() {
		List<String> addressList = new ArrayList<String>();
		addressList.add("InfoCenter");
		addressList.add("Rozysk");
		addressList.add("Sledstvie");
		addressList.add("Prokuratura");
		addressList.add("Ministerstvo");
		addressList.add("Ivanov I.I.");
		addressList.add("Petrov P.P.");
		
		
		Math.random();
		
		for (int i=1; i<1000; i++) {
			
			String regNumber = ""+ (int)(1000+(1000*Math.random())); // Number between 1000 and 2000.
			String sender =addressList.get((int)(7*Math.random()));
			String receiver = addressList.get( (int)(7*Math.random()) );
			
			Stuff stuff = new Stuff();
			stuff.setRegNumber(regNumber);
			stuff.setYear(2013);
			stuffDao.saveStuff(stuff);
			stuff = stuffDao.findStuff(regNumber, 2013);
			
			StuffFlow stuffFlow = new StuffFlow();
			stuffFlow.setSender(sender);
			stuffFlow.setReciever(receiver);
			stuffFlow.setSendDate(new Date());
			stuffFlow.setSign(sender);
			stuffFlow.setStuff(stuff);
			stuffFlow.setUser( userDao.userList().get((int)(4*Math.random())) );
			stuffFlowDao.saveFlow(stuffFlow);
		}
	}

}
