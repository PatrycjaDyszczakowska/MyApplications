package com.praca_inzynierska_server.app;

/**
 * Klasa odpowiadająca za opinie o kursie
 */
public class List_of_reviews {
	/**
	 * Zmienne odpowiadające za numer identyfikatora, ocene kursu, 
	 * numer indeksu uzytkownika, imie i nazwisko prowadzącego oraz opinie o kursie
	 */
	private Integer id;
	private Integer rate;
	private String nameUser;
	private String name;
	private String review;
	/**
	 * Konstruktor klasy odpowiadający za zapisanie infromacji o opinii
	 * @param id numer identyfikacyjny opinii
	 * @param rate ocena kursi
	 * @param nameUser nazwa uzytkownika wystawiającego ocene
	 * @param name imie i nazwisko prowadzącego kurs
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
	 * Metoda pobierająca identyfikator opinii
	 * @return Integer - identyfikator opinii
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Metoda pobierająca ocene kursu
	 * @return Integer - ocena kursu
	 */
	public Integer getRate() {
		return rate;
	}
	/**
	 * Metoda pobierająca nazwe użytkownika
	 * @return String - nazwa użytkownika
	 */
	public String getNameUser() {
		return nameUser;
	}
	/**
	 * Metoda pobierająca imie i nazwisko prowadzącego
	 * @return String - imie i nazwisko prowadzącego
	 */
	public String getName() {
		return name;
	}
	/**
	 * Metoda pobierająca opinie o kursie
	 * @return String - opinia o kursie
	 */
	public String getRev() {
		return review;
	}
}
