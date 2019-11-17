package com.praca_inzynierska_server.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

/**
 * Klasa odpowiadaj¹ca za infromacje o semestrach
 */
public class Semester {
	/**
	 * Zmienne odpowiadaj¹ce za numer identyfikatora oraz nazwe semestru
	 */
	private Integer id;
	private String name;
	/**
	 * Tablica opinii
	 */
	private List<List_of_reviews> List_reviews = new ArrayList<>();
	/**
	 * Konstruktor klasy odpowiadaj¹cy za wczytywanie opinii z pliku
	 * oraz zapisanie informacje o semestrze
	 * @param id numer identyfikacyjny semestru
	 * @param name nazwa semstru
	 * @param path scie¿ka pliku
	 */
	public Semester(Integer id, String name, String path) {
		this.id = id;
		this.name = name;
		try{
			Scanner s = new Scanner(new File(path+name.trim().replace(":","_").replace("/", " ")+"/listofreview.txt"));
			while(s.hasNextLine()){
				String next = s.nextLine();
				if(!next.equals("")) {
					String[] nameSplit = next.split("&");
					createListOfRev(Integer.parseInt(nameSplit[0]), nameSplit[1], nameSplit[2], nameSplit[3]);
				}
			}
			s.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Konstruktor klasy odpowiadaj¹cy za zapisanie infromacji o semestrze
	 * @param id numer identyfikacyjny semestru
	 * @param name nazwa semstru
	 */
	public Semester(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	/**
	 * Metoda pobieraj¹ca identyfikator semestru
	 * @return Integer - identyfikator semestru
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Metoda pobieraj¹ca nazwe semestru
	 * @return String - nazwa semestru
	 */
	public String getName() {
		return name;
	}
	/**
	 * Metoda wysy³aj¹ca tablice opinii do klienta
	 * @return List<> - tablica opinii
	 */
	public List<List_of_reviews> getAllListOfRev() {
		return List_reviews;
	}
	/**
	 * Metoda wyœwietlaj¹ca wybran¹ opinie
	 * @param id identyfikator opinii
	 * @return List_of_reviews - informacje o wybranej opinii
	 */
	public List_of_reviews getListOfRev(Integer id) {
		return List_reviews.get(id);
	}
	/**
	 * Metoda dodaj¹ca now¹ opinie wywo³ana jest z konstruktora
	 * @param rate ocena kursu
	 * @param nameUser numer u¿ytkownika
	 * @param name imie i nazwisko prowadz¹cego
	 * @param review opinia kursu
	 * @return List_of_reviews - zwraca informacje o zapisanej opinii
	 */
	private List_of_reviews createListOfRev(Integer rate, String nameUser, String name, String review) {
		List_of_reviews List_review = new List_of_reviews(List_reviews.size(),rate, nameUser, name, review);
		List_reviews.add(List_reviews.size(),List_review);
		return List_review;
	}
	/**
	 * Metoda dodaj¹ca now¹ opinie
	 * @param id numer identyfikatora opinii
	 * @param rate ocena kursu
	 * @param nameUser numer u¿ytkownika
	 * @param name imie i nazwisko prowadz¹cego
	 * @param review opinia kursu
	 * @param path scie¿ka pliku
	 * @return List_of_reviews - zwraca informacje o nowej opinii
	 */
	public List_of_reviews createListOfRev(Integer id, Integer rate, String nameUser, String name, String review, String path) {
		try {
			Writer add = new BufferedWriter(new FileWriter(path+"/listofreview.txt",true));
			add.append("\n"+rate+"&"+nameUser+"&"+name+"&"+review);
			add.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		List_of_reviews List_review = new List_of_reviews(id, rate, nameUser, name, review);
		List_reviews.add(id,List_review);
		return List_review;
	}
}
