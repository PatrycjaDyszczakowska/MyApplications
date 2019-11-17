package com.praca_inzynierska_server.app;
/**
 * Klasa odpowiadaj鉍a za informacje o u篡tkowniku
 */
public class User {
	/**
	 * Zmienne odpowiadaj鉍e za has這, administrator/zwyk造 uzytkownik, 
	 * klucz aktywacyjny oraz czy aktywne konto uzytkownika
	 */
	String password;
	String adminuser;
	String numberAcc;
	Boolean acc;
	/**
	 * Konstruktor klasy odpowiadaj鉍y za zapisanie infromacji o u篡tkowniku
	 * @param password has這 uzytkownika
	 * @param adminuser informacja czy uzytkownik to administrator czy zwyk造 uzytkownik
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
	 * Metoda pobieraj鉍a has這 uzytkownika
	 * @return String - has這 uzytkownika
	 */
	public String password() {
		return password;
	}
	/**
	 * Metoda pobieraj鉍a informacje czy jest administratorem czy zwyk造m uzytkownikiem
	 * @return String - informacja czy jest administratorem czy zwyk造m uzytkownikiem
	 */
	public String getAdminUser() {
		return adminuser;
	}
	/**
	 * Metoda pobieraj鉍a klucz aktywacyjny
	 * @return String - klucz aktywacyjny
	 */
	public String getNumberAcc() {
		return numberAcc;
	}
	/**
	 * Metoda pobieraj鉍a czy aktywne konto
	 * @return Boolean true - gry aktywne konto, false - gdy konto jest nie aktywne 
	 */
	public Boolean getAcc() {
		return acc;
	}
	/**
	 * Metoda zmieniaj鉍a na aktywne konto
	 * @param acc true - gry aktywne konto, false - gdy konto jest nie aktywne 
	 */
	public void setAcc(Boolean acc) {
		this.acc = acc;
	}
}
