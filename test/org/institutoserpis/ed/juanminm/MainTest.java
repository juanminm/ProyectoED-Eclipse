package org.institutoserpis.ed.juanminm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MainTest {

	@Test
	public void bothAccountsWithoutEmailAreTheSame() {
		int id = 1;
		String username = "johndoe";
		String password = "12345678";
		Account account = new Account(id, username,
				password);

		assertEquals(true, account.equals(Main.createAccount(id, username,
				password, null, true)));
	}

	@Test
	public void bothAccountsWithEmailAreTheSame() {
		int id = 1;
		String username = "johndoe";
		String password = "12345678";
		String email = "john@d.oe";
		Account account = new Account(id, username,
				password, email);

		assertEquals(true, account.equals(Main.createAccount(id, username,
				password, email, false)));
	}

	@Test
	public void accountsHaveDifferentIDs() {
		String username = "johndoe";
		String password = "12345678";
		String email = "john@d.oe";
		Account account = new Account(1, username,
				password, email);

		assertEquals(false, account.equals(Main.createAccount(2, username,
				password, email, false)));
	}

	@Test
	public void accountsHaveDifferentUsernames() {
		int id = 1;
		String username = "johndoe";
		String password = "12345678";
		String email = "john@d.oe";
		Account account = new Account(1, username,
				password, email);

		assertEquals(false, account.equals(Main.createAccount(id, "janedoe",
				password, email, false)));
	}

	@Test
	public void accountsHaveDifferentPasswords() {
		int id = 1;
		String username = "johndoe";
		String password = "12345678";
		String email = "john@d.oe";
		Account account = new Account(1, username,
				password, email);


		assertEquals(false, account.equals(Main.createAccount(id, username,
				"qwerty", email, false)));
	}

	@Test
	public void accountsHaveDifferentEmails() {
		int id = 1;
		String username = "johndoe";
		String password = "12345678";
		String email = "john@d.oe";
		Account account = new Account(1, username,
				password, email);


		assertEquals(false, account.equals(Main.createAccount(id, username,
				password, "jane@d.oe", false)));
	}

	@Test
	public void oneAccountHasOmmitedEmail() {
		int id = 1;
		String username = "johndoe";
		String password = "12345678";
		String email = "john@d.oe";
		Account account = new Account(1, username,
				password, email);


		assertEquals(false, account.equals(Main.createAccount(id, username,
				password, email, true)));
	}

	@Test
	public void usernameHasSpaces() {
		assertEquals(false, Main.isUsernameValid("juanmi navarro"));
	}

	@Test
	public void passwordLessThanSixCharacters() {
		assertEquals(false, Main.isPasswordValid("12345"));
	}

	@Test
	public void passwordContainsInvalidCharacters() {
		assertEquals(false, Main.isPasswordValid("qwerty♀-∟"));
	}

	@Test
	public void emailMissingUsernamePart() {
		assertEquals(false, Main.isEmailValid("@gmail.com"));
	}

	@Test
	public void emailMissingAt() {
		assertEquals(false, Main.isEmailValid("john.doe.com"));
	}

	@Test
	public void emailMissingDomainPart() {
		assertEquals(false, Main.isEmailValid("john.doe@"));
	}

	@Test
	public void emailWithOneLetterTLD() {
		assertEquals(false, Main.isEmailValid("@gmail.c"));
	}
}
