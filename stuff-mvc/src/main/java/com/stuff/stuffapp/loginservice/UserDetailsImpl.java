package com.stuff.stuffapp.loginservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.stuff.stuffapp.domain.Role;
import com.stuff.stuffapp.domain.User;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -5718248482046103637L;
	private User user;
	
	public UserDetailsImpl(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthorityImpl> authList = new ArrayList<GrantedAuthorityImpl>();
		authList.add(new GrantedAuthorityImpl( grantedAuthMapper(user.getRole()) ));
		return authList;
	}
	
	private String grantedAuthMapper(Role role) {
		if (role.getName().equals("Admin")) {
			return "ROLE_ADMIN";
		}
		if (role.getName().equals("User")) {
			return "ROLE_USER";
		}
		return "ROLE_GUEST";
	}
	
	public User getUser() {
		return user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
