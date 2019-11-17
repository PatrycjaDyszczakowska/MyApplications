package com.praca_inzynierska_server.app;

/**
 * Klasa odpowiadaj¹ca za opinie o kursie
 */
public class List_of_reviews {
	/**
	 * Zmienne odpowiadaj¹ce za numer identyfikatora, ocene kursu, 
	 * numer indeksu uzytkownika, imie i nazwisko prowadz¹cego oraz opinie o kursie
	 */
	private Integer id;
	private Integer rate;
	private String nameUser;
	private String name;
	private String review;
	/**
	 * Konstruktor klasy odpowiadaj¹cy za zapisanie infromacji o opinii
	 * @param id numer identyfikacyjny opinii
	 * @param rate ocena kursi
	 * @param nameUser nazwa uzytkownika wystawiaj¹cego ocene
	 * @param name imie i nazwisko prowadz¹cego kurs
	 * @param review opini o kursie
	 */
	public List_of_reviews(Integer id, Integer rate, String nameUser, String name, String review) {
		this.id = id;
		this.rate = rate;
		this.nameUser = nameUser;
		this.name = name;
		this.review = review;
	}
	/**
	 * Metoda pobieraj¹ca identyfikator opinii
	 * @return Integer - identyfikator opinii
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Metoda pobieraj¹ca ocene kursu
	 * @return Integer - ocena kursu
	 */
	public Integer getRate() {
		return rate;
	}
	/**
	 * Metoda pobieraj¹ca nazwe u¿ytkownika
	 * @return String - nazwa u¿ytkownika
	 */
	public String getNameUser() {
		return nameUser;
	}
	/**
	 * Metoda pobieraj¹ca imie i nazwisko prowadz¹cego
	 * @return String - imie i nazwisko prowadz¹cego
	 */
	public String getName() {
		return name;
	}
	/**
	 * Metoda pobieraj¹ca opinie o kursie
	 * @return String - opinia o kursie
	 */
	public String getRev() {
		return review;
	}
}
