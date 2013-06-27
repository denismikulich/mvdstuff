package com.stuff.stuffapp.data;

/**
 * User business object
 * 
 * @author Denis.Mikulich
 * 
 */
public class UserBO {

	private String firstName;
	private String lastName;
	private String login;
	private String roleDescription;
	
	/**
	 * Build full user name.
	 * @return full name.
	 */
	public String getFullName() {
		return firstName+" "+lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

}
