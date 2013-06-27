package com.stuff.stuffapp.dao;

import java.util.List;

import com.stuff.stuffapp.domain.Role;

public interface RoleDao {

	public void saveRole(Role role);
	
	public List<Role> roleList();

    public void removeRole(Long id);

    public Role retriveRole(Long id);
    
}
