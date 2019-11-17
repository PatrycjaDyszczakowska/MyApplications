package com.praca_inzynierska_server.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.*;

/**
 * Klasa odpowiadaj¹ca za infromacje o kursach
 */
public class Course {
	/**
	 * Zmienne odpowiadaj¹ce za numer identyfikatora oraz nazwe kursu
	 */
	private Integer id;
	private String name;
	/**
	 * Tablica semestrów
	 */
	private List<Semester> semes = new ArrayList<>();
	/**
	 * Konstruktor klasy odpowiadaj¹cy za wczytywanie semestrów z pliku
	 * oraz zapisanie informacje o kursie
	 * @param id numer identyfikacyjny kursu
	 * @param name nazwa kursu
	 * @param path scie¿ka pliku
	 */
	public Course(Integer id, String name, String path) {
		this.id = id;
		this.name = name;
		/*try {
			Files.createDirectories(Paths.get(path+name.trim().replace(":"," ")));
			Files.createFile(Paths.get(path+name.trim().replace(":"," ")+"/semester.txt"));
		}catch (FileAlreadyExistsException e) {
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (InvalidPathException c) {
			c.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}*/
		
		try{
			Scanner s = new Scanner(new File(path+name.trim().replace(":"," ")+"/semester.txt"));
			while(s.hasNextLine()){
				String next = s.nextLine();
				if(!next.equals("")) {
					createSemester(next,path+name+"/");
				}
			}
			s.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Konstruktor klasy odpowiadaj¹cy za zapisanie infromacji o kursie
	 * @param id numer identyfikacyjny kursu
	 * @param name nazwa kursu
	 */
	public Course(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	/**
	 * Metoda pobieraj¹ca identyfikator kursu
	 * @return Integer - identyfikator kursu
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Metoda pobieraj¹ca nazwe kursu
	 * @return String - nazwa kursu
	 */
	public String getName() {
		return name;
	}
	/**
	 * Metoda wysy³aj¹ca tablice semestrów do klienta
	 * @return List<> - tablica semestrów
	 */
	public List<Semester> getAllSemester() {
		return semes;
	}
	/**
	 * Metoda wyœwietlaj¹ca wybrany semestr
	 * @param id identyfikator semestru
	 * @return Semester - informacje o wybranym semestrze
	 */
	public Semester getSemester(Integer id) {
		return semes.get(id);
	}
	/**
	 * Metoda dodaj¹ca nowy semestr wywo³ana jest z konstruktora
	 * @param name nazwa semestru
	 * @param path scie¿ka pliku
	 * @return Semester - zwraca informacje o zapisanym semestrze
	 */
	private Semester createSemester(String name, String path) {
		Semester seme = new Semester(semes.size(),name,path);
		semes.add(semes.size(),seme);
		return seme;
	}
	/**
	 * Metoda dodaj¹ca nowy semestr
	 * @param id numer identyfikatora semestru
	 * @param name nazwa semestru
	 * @param path scie¿ka pliku
	 * @return Semester - zwraca informacje o nowym semestrze
	 */
	public Semester createSemester(Integer id, String name, String path) {
		try {
			Files.createDirectories(Paths.get(path+"/"+name.replace(":"," ").replace("/", " ")));
			Files.createFile(Paths.get(path+"/"+name.replace(":"," ").replace("/", " ")+"/listofreview.txt"));
			System.out.println("Katalog :"+path+"/"+name.replace(":"," ").replace("/", " "));
			System.out.println("Plik: "+path+"/"+name.replace(":"," ").replace("/", " ")+"/listofreview.txt");
			
			Writer add = new BufferedWriter(new FileWriter(path+"/semester.txt",true));
			add.append("\n"+name);
			add.close();
		}catch (FileAlreadyExistsException e) {
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (InvalidPathException c) {
			c.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		Semester seme = new Semester(id, name);
		semes.add(id,seme);
		return seme;
	}
	/**
	 * Metoda dodaj¹ca do semestru nowych opinii
	 * @param idSemester numer identyfikatora semestru
	 * @param idListOfRev numer identyfikatora opinii
	 * @param rate ocena kursu od 1..5
	 * @param nameUser nazwa u¿ytkownika(login)
	 * @param name imie i nazwisko prowadz¹cego
	 * @param review opinia o kursie
	 * @param path scie¿ka pliku
	 * @return Semester - zaktualizowane informacje o semestrze
	 */
	public Semester updateSemester(Integer idSemester, Integer idListOfRev, Integer rate, String nameUser, String name, String review, String path) {
		Semester seme = semes.get(idSemester);
		seme.createListOfRev(idListOfRev, rate, nameUser, name, review, path+"/"+seme.getName().replace(":"," ").replace("/", " "));
		return seme;
	}
}
