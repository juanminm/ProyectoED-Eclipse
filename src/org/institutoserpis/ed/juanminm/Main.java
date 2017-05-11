package org.institutoserpis.ed.juanminm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static Account loggedAccount;

	static Account createAccount(long id, String username,
			String password, String email, boolean ommitedEmail) {
		Account account;

		if (ommitedEmail) {
			account = new Account(1, username, password);
		} else {
			account = new Account(1, username, password, email);
		}

		return account;
	}
	private static void createAccountForm() {
		Scanner scan = new Scanner(System.in);
		String username = null;
		String password = null;
		String email = null;
		boolean validUsername = false;
		boolean validPassword = false;
		boolean validEmail = false;
		boolean ommitedEmail = false;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;

		while (!validUsername) {
			System.out.println("Introduzca un nombre de usuario: ");
			username = scan.nextLine();

			if (username.matches("\\w+\\ +\\w+")) {
				System.err.println("Nombre de usuario invalido. No puede "
						+ "tener espacios.");;
			} else {
				validUsername = true;

			}
		}


		while (!validPassword) {
			System.out.println("Introduzca una contraseña (min. 6 "
					+ "caracteres): ");
			password = scan.nextLine();

			if (password.equals("")) {
				System.err.println("La contraseña está vacia.");
			}
			if (password.matches("\\X{0,5}")) {
				System.err.println("La contraseña es demasiado corta.");
			} else {
				validPassword = true;
			}
		}

		while (!validEmail || ommitedEmail) {
			System.out.println("(Opcional) Introduzca un correo: ");
			email = scan.nextLine();

			if (email.equals("")) {
				ommitedEmail = true;
			} else if (email.matches("\\w+@[a-zA-Z]+\\.[a-zA-Z]{2,}")) {
				System.err.println("El correo introducizo es invalido.");
			} else {
				validEmail = true;
			}
		}

		try {
			File file = new File("accounts.ser");
			AccountList accountList;
			ArrayList<Account> accList;

			if (file.exists()) {
				ois = new ObjectInputStream(new FileInputStream(file));
				accountList = (AccountList) ois.readObject();
			} else {
				file.createNewFile();
				accountList = new AccountList();
			}

			accList = accountList.getAccounts();
			if (accList.isEmpty()) {
				accList.add(createAccount(1, username, password, email,
						ommitedEmail));
			} else {
				int size = accList.size();
				long newID = accList.get(size - 1).getID() + 1;

				accList.add(createAccount(newID, username, password, email,
						ommitedEmail));
			}
			accountList.setAccounts(accList);

			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(accountList);
		} catch (ClassNotFoundException | IOException ex) {
			System.err.println(ex.getMessage());
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}

				if (oos != null) {
					ois.close();
				}
			} catch (IOException ex) {
				System.err.println(ex.getMessage());
			}
		}

		scan.close();
	}

	private void connecAccount() {

	}

	private void connecAccountForm() {

	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		if (loggedAccount == null) {

		} else {
			System.out.println("Programa de chat");
			System.out.println("1. Crear cuenta");
			System.out.println();
			System.out.print("Opcion: ");
			int opcion = scan.nextInt();
			scan.nextLine();
			switch(opcion) {
			case 1:
				createAccountForm();
			default:
				System.out.println("Opción invalida. Finalizando...");
			}
		}
		scan.close();
	}

}
