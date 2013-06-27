package com.stuff.stuffapp.dao;

import java.util.List;

import com.stuff.stuffapp.domain.User;

public interface UserDao {
	
	public void saveUser(User user);
	
	public List<User> userList();

    public void removeUser(Long id);

    public User retriveUser(Long id);
    
    public List<User> findUsersByName(String name);

}
