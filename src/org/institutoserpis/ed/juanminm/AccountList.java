package org.institutoserpis.ed.juanminm;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountList implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<Account> accounts = new ArrayList<Account>();

	public void addAccount(Account account) {
		accounts.add(account);
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}
}
