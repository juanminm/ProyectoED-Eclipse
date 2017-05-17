package org.institutoserpis.ed.juanminm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	private static Account loggedAccount;

	public static Account createAccount(long id, String username,
			String password, String email, boolean ommitedEmail) {
		Account account;

		if (ommitedEmail) {
			account = new Account(id, username, password);
		} else {
			account = new Account(id, username, password, email);
		}

		return account;
	}

	private static ArrayList<Account> getAccountList()
			throws ClassNotFoundException, IOException {
		ObjectInputStream ois = null;
		AccountList accountList = null;

		File file = new File("accounts.ser");

		if (file.exists()) {
			ois = new ObjectInputStream(new FileInputStream(file));
			accountList = (AccountList) ois.readObject();
		} else {
			file.createNewFile();
			accountList = new AccountList();
		}

		return accountList.getAccounts();
	}

	private static boolean storeAccount(String username, String password,
			String email, boolean ommitedEmail) {
		boolean status = false;
		ObjectOutputStream oos = null;

		try {
			File file = new File("accounts.ser");
			ArrayList<Account> accList;
			AccountList accountList = new AccountList();

			accList = getAccountList();
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
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null,
					ex);
			}
		}

		return status;
	}

	public static boolean isUsernameValid(String s) {
		return isValid(s, "^\\w+[^\\ ]+\\w+$");
	}

	public static boolean isPasswordValid(String s) {
		return isValid(s, "^[a-zA-Z0-9()`~!@#$%^&\\*\\-\\+=|\\\\\\{}\\[\\]:;\"'"
				+ "<>,.?\\/]{6,}$");
	}

	public static boolean isEmailValid(String s) {
		return isValid(s, "^[\\w.-]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$");
	}
	private static boolean isValid (String s, String pattern) {
		if (s.matches(pattern)) {
			return true;
		} else {
			return false;
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

			System.out.print("Introduzca un nombre de usuario: ");
			username = scan.nextLine();
		} while (invalidUsername = !isUsernameValid(username));

		do {
			if (invalidPassword) {
				System.out.println("La contraseña es demasiado corta.");
			}

			System.out.print("Introduzca una contraseña (min. 6"
					+ " caracteres): ");
			password = scan.nextLine();
		} while (invalidPassword = !isPasswordValid(password));


		do {
			if (invalidEmail) {
				System.out.println("La dirección de correo introducida es"
						+ " invalida.");
			}
			System.out.print("Introduzca una dirección de correo: ");
			email = scan.nextLine();

			if (email.equals("")) {
				ommitedEmail = true;
			}
		} while (!ommitedEmail && (invalidEmail = !isEmailValid(email)));

		if (storeAccount(username, password, email, ommitedEmail)) {
			System.out.println("La cuenta ha sido correctamente creada.");
		} else {
			System.err.println("Hubo un error durante la creación de la "
					+ "cuenta.");
		}

		scan.close();
	}

	public static Account connectAccount(String username, String password) {
		ObjectInputStream ois = null;
		AccountList accountList;
		ArrayList<Account> accList;
		Account targetAccount = null;

		try {
			File file = new File("accounts.ser");

			if (file.exists()) {
				ois = new ObjectInputStream(new FileInputStream(file));
				accountList = (AccountList) ois.readObject();
			} else {
				file.createNewFile();
				accountList = new AccountList();
			}

			accList = accountList.getAccounts();

			for (Account account : accList) {
				if (account.getUsername().equals(username)
						&& account.getPassword().equals(password)) {
					targetAccount = account;
				}
			}
		} catch (IOException | ClassNotFoundException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null,
						ex);
			}
		}
		return targetAccount;
	}

	private static void connectAccountForm() {
		Scanner scan = new Scanner(System.in);
		Account account;
		String username;
		String password;

		System.out.print("Usuario: ");
		username = scan.nextLine();
		System.out.print("Contraseña: ");
		password = scan.nextLine();
		account = connectAccount(username, password);

		if (account == null) {
			System.out.println("La cuenta no existe.");
		}

		scan.close();
	}

	private static void showLogginMenu() {
		System.out.println("Conectado como: juanmi");
		System.out.println();
		System.out.println("1. Nueva conversación.");
		System.out.println("2. Listar conversaciones.");
		System.out.println("3. Listar conversaciones no leidas.");
		System.out.println("4. Eliminar historial de una conversación.");
		System.out.println("5. Eliminar todo el historial.");
		System.out.println("6. Desconectarse.");
		System.out.println("0. Desconectarse y cerrar.");
	}

	private static void showMainMenu() {
		System.out.println();
		System.out.println("Programa de chat");
		System.out.println();
		System.out.println("1. Crear cuenta.");
		System.out.println("2. Conectarse.");
		System.out.println("0. Cerrar.");
		System.out.print("Opcion: ");
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean salir = false;

		do {
			System.out.println("Programa de chat");
			if (loggedAccount != null) {
				int opcion;

				showLogginMenu();
				try {
					opcion = scan.nextInt();
				} catch (InputMismatchException ex) {
					opcion = -1;
				} finally {
					scan.nextLine();
				}

				switch (opcion) {
					case 0 :
						System.out.println("Cerrando...");
						loggedAccount = null;
						salir = true;
						break;
					case 1:
						//TODO
						break;
					case 2:
						//TODO
						break;
					case 3:
						//TODO
						break;
					case 4:
						//TODO
						break;
					case 5:
						//TODO
						break;
					case 6:
						loggedAccount = null;
						System.out.println("Desconectado. Plues ENTER para"
								+ "continuar...");
						scan.nextLine();
						break;
				}

			} else {
				int opcion;

				showMainMenu();
				try {
					opcion = scan.nextInt();
				} catch (InputMismatchException ex) {
					opcion = -1;
				} finally {
					scan.nextLine();
				}

				switch(opcion) {
					case 0:
						System.out.println("Cerrando...");
						salir = true;
						break;
					case 1:
						createAccountForm();
						break;
					case 2:
						connectAccountForm();
						break;
					default:
						System.out.println("Opción invalida.");
				}
			}
		} while(!salir);
		scan.close();
	}

}
