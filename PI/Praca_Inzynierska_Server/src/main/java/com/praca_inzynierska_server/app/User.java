package com.praca_inzynierska_server.app;
/**
 * Klasa odpowiadaj�ca za informacje o u�ytkowniku
 */
public class User {
	/**
	 * Zmienne odpowiadaj�ce za has�o, administrator/zwyk�y uzytkownik, 
	 * klucz aktywacyjny oraz czy aktywne konto uzytkownika
	 */
	String password;
	String adminuser;
	String numberAcc;
	Boolean acc;
	/**
	 * Konstruktor klasy odpowiadaj�cy za zapisanie infromacji o u�ytkowniku
	 * @param password has�o uzytkownika
	 * @param adminuser informacja czy uzytkownik to administrator czy zwyk�y uzytkownik
	 * @param numberAcc klucz aktywacyjny
	 * @param acc true - gry aktywne konto, false - gdy konto jest nie aktywne 
	 */
	public User(String password, String adminuser, String numberAcc, Boolean acc ) {
		this.password = password;
		this.adminuser = adminuser;
		this.numberAcc = numberAcc;
		this.acc = acc;
	}
	/**
	 * Metoda pobieraj�ca has�o uzytkownika
	 * @return String - has�o uzytkownika
	 */
	public String password() {
		return password;
	}
	/**
	 * Metoda pobieraj�ca informacje czy jest administratorem czy zwyk�ym uzytkownikiem
	 * @return String - informacja czy jest administratorem czy zwyk�ym uzytkownikiem
	 */
	public String getAdminUser() {
		return adminuser;
	}
	/**
	 * Metoda pobieraj�ca klucz aktywacyjny
	 * @return String - klucz aktywacyjny
	 */
	public String getNumberAcc() {
		return numberAcc;
	}
	/**
	 * Metoda pobieraj�ca czy aktywne konto
	 * @return Boolean true - gry aktywne konto, false - gdy konto jest nie aktywne 
	 */
	public Boolean getAcc() {
		return acc;
	}
	/**
	 * Metoda zmieniaj�ca na aktywne konto
	 * @param acc true - gry aktywne konto, false - gdy konto jest nie aktywne 
	 */
	public void setAcc(Boolean acc) {
		this.acc = acc;
	}
}
