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

	public static Account createAccount(long id, String username,
			String password, String email, boolean ommitedEmail) {
		Account account;

		if (ommitedEmail) {
			account = new Account(1, username, password);
		} else {
			account = new Account(1, username, password, email);
		}

		return account;
	}

	private static boolean storeAccount(String username, String password,
			String email, boolean ommitedEmail) {
		boolean status = false;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;

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

			status = true;
		} catch (ClassNotFoundException | IOException ex) {
			System.err.println(ex.getMessage());
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}

				if (oos != null) {
					oos.close();
				}
			} catch (IOException ex) {
				System.err.println(ex.getMessage());
			}
		}

		return status;
	}

	public static boolean isUsernameValid (String s) {
		return isValid(s, "\\w+\\ +\\w+");
	}

	public static boolean isPasswordValid (String s) {
		return isValid(s, "\\X{0,5}");
	}

	public static boolean isEmailValid (String s) {
		return isValid(s, "\\w+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}");
	}
	private static boolean isValid (String s, String pattern) {
		if (s.matches(pattern)) {
			return false;
		} else {
			return true;
		}
	}

	public static void createAccountForm() {
		Scanner scan = new Scanner(System.in);
		String username = null;
		String password = null;
		String email = null;
		boolean invalidUsername = false;
		boolean invalidPassword = false;
		boolean invalidEmail = false;
		boolean ommitedEmail = false;


		do {
			if (invalidUsername) {
				System.out.println("El nombre de usuario es incorrecto.");
			}

			System.out.println("Introduzca un nombre de usuario: ");
			username = scan.nextLine();
		} while (invalidUsername = !isUsernameValid(username));

		do {
			if (invalidPassword) {
				System.out.println("La contraseña es demasiado corta.");
			}

			System.out.println("Introduzca una contraseña (min. 6 caracteres): ");
			password = scan.nextLine();
		} while (invalidPassword = !isPasswordValid(password));


		do {
			if (invalidEmail) {
				System.out.println("La dirección de correo introducida es invalida.");
			}
			System.out.println("Introduzca una dirección de correo: ");
			email = scan.nextLine();

			if (email.isEmpty()) {
				ommitedEmail = true;
			}
		} while (!ommitedEmail || (invalidEmail = !isEmailValid(email)));

		if (storeAccount(username, password, email, ommitedEmail)) {
			System.out.println("La cuenta ha sido correctamente creada.");
		} else {
			System.err.println("Hubo un error durante la creación de la "
					+ "cuenta.");
		}

		scan.close();
	}

	private static void connectAccount(String username, String password) {
		//ObjectInputStream
	}

	private static void connectAccountForm() {
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
