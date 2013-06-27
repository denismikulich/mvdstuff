package com.stuff.stuffapp.loginservice;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {

	private static final long serialVersionUID = -7363694531742161265L;
	private String authority = null;

	public GrantedAuthorityImpl(String auth) {
		authority = auth;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

}
