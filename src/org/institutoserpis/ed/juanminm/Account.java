package org.institutoserpis.ed.juanminm;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	private final long id;
	private final String username;
	private String password;
	private String email;

	public Account (long id, String username, String password) {
		this(id, username, password, null);
	}

	public Account (long id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public long getID() {
		return id;
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

	@Override
	public boolean equals(Object o) {
		if (o instanceof Account) {
			final Account a = (Account) o;

			return Objects.equals(id, a.id)
					&& Objects.equals(username, a.username)
					&& Objects.equals(password, a.password)
					&& Objects.equals(email, a.email);
		}
		return false;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(id, username, password, email);
	}
}
