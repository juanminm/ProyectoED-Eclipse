package org.institutoserpis.ed.juanminm;

import java.io.Serializable;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	private final long ID;
	private final String username;
	private String password;
	private String email;

	public Account (long id, String username, String password) {
		this(id, username, password, null);
	}

	public Account (long id, String username, String password, String email) {
		this.ID = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public long getID() {
		return ID;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
