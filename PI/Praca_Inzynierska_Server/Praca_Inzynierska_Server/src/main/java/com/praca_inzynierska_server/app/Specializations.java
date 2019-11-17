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
 * Klasa odpowiadaj¹ca za infromacje o kierunku
 */
public class Specializations {
	/**
	 * Zmienne odpowiadaj¹ce za numer identyfikatora oraz nazwe kierunku
	 */
	private Integer id;
	private String name;
	/**
	 * Tablica stopni studiowania
	 */
	private List<Degree> degs = new ArrayList<>();
	/**
	 * Konstruktor klasy odpowiadaj¹cy za wczytywanie stopni studiowania z pliku
	 * oraz zapisanie informacje o kierunku
	 * @param id numer identyfikacyjny kierunku
	 * @param name nazwa kierunku
	 * @param path scie¿ka pliku
	 */
	public Specializations(Integer id, String name, String path) {
		this.id = id;
		this.name = name;
		try{
			Scanner s = new Scanner(new File(path+name.trim().replace(":"," ")+"/degree.txt"));
			while(s.hasNextLine()){
				String next = s.nextLine();
				if(!next.equals("")) {
					createDeg(next,path+name+"/");
				}
			}
			s.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Konstruktor klasy odpowiadaj¹cy za zapisanie infromacji o kierunku
	 * @param id numer identyfikacyjny kierunku
	 * @param name nazwa kierunku
	 */
	public Specializations(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	/**
	 * Metoda pobieraj¹ca identyfikator kierunku
	 * @return Integer - identyfikator kierunku
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Metoda pobieraj¹ca nazwe kierunku
	 * @return String - nazwa kierunku
	 */
	public String getName() {
		return name;
	}
	/**
	 * Metoda wysy³aj¹ca tablice stopnii studiowania do klienta
	 * @return List<> - tablica stopnii studiowania
	 */
	public List<Degree> getAllDeg() {
		return degs;
	}
	/**
	 * Metoda wyœwietlaj¹ca wybrany stopieñ studiowania
	 * @param id identyfikator stopnia studiowania
	 * @return Degree - informacje o wybranym stopniu studiowania
	 */
	public Degree getDeg(Integer id) {
		return degs.get(id);
	}
	/**
	 * Metoda dodaj¹ca nowy stopieñ studiowania wywo³ana jest z konstruktora
	 * @param name nazwa stopnia studiowania
	 * @param path scie¿ka pliku
	 * @return Degree - zwraca informacje o zapisanym stopniu studiowania
	 */
	private Degree createDeg(String name, String path) {
		Degree deg = new Degree(degs.size(),name,path);
		degs.add(degs.size(),deg);
		return deg;
	}
	/**
	 * Metoda dodaj¹ca nowy stopieñ studiowania
	 * @param id numer identyfikatora stopnia studiowania
	 * @param name nazwa stopnia studiowania
	 * @param path scie¿ka pliku
	 * @return Degree - zwraca informacje o nowym stopniu studiowania
	 */
	public Degree createDeg(Integer id, String name, String path) {
		try {
			Files.createDirectories(Paths.get(path+"/"+name.replace(":"," ")));
			Files.createFile(Paths.get(path+"/"+name.replace(":"," ")+"/courses.txt"));
			System.out.println("Katalog :"+path+"/"+name.replace(":"," "));
			System.out.println("Plik: "+path+"/"+name.replace(":"," ")+"/courses.txt");
			
			Writer add = new BufferedWriter(new FileWriter(path+"/degree.txt",true));
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
		Degree deg = new Degree(id, name);
		degs.add(id,deg);
		return deg;
	}
	/**
	 * Metoda dodaj¹ca do stopnia studiowania nowe kursy
	 * @param idDeg numer identyfikatora stopnia studiowania
	 * @param idCourse numer identyfikatora kursu
	 * @param name nazwa kursu
	 * @param path scie¿ka pliku
	 * @return Degree - zaktualizowane informacje o stopniu studiowania
	 */
	public Degree updateDeg(Integer idDeg, Integer idCourse, String name, String path) {
		Degree deg = degs.get(idDeg);
		deg.createCourse(idCourse, name, path + "/" + deg.getName().replace(":"," "));
		return deg;
	}
	/**
	 * Metoda aktualizuj¹ca kursy w danym stopniu studiowania
	 * @param idDeg numer identyfikatora stopnia studiowania
	 * @param idCourse numer identyfikatora kursu
	 * @param idSemester numer identyfikatora semestru
	 * @param name nazwa semestru
	 * @param path scie¿ka pliku
	 * @return Degree - zaktualizowane informacje o stopniu studiowania
	 */
	public Degree updateCourse(Integer idDeg, Integer idCourse, Integer idSemester, String name, String path) {
		Degree deg = degs.get(idDeg);
		deg.updateCourse(idCourse, idSemester, name, path + "/" + deg.getName().replace(":"," "));
		return deg;
	}
	/**
	 * Metoda aktualizuj¹ca semestry w danym stopniu studiowania
	 * @param idDeg numer identyfikatora stopnia studiowania
	 * @param idCourse numer identyfikatora kursu
	 * @param idSemester numer identyfikatora semestru
	 * @param idListOfRev numer identyfikatora opinii
	 * @param rate ocena kursu od 1..5
	 * @param nameUser nazwa u¿ytkownika(login)
	 * @param name imie i nazwisko prowadz¹cego
	 * @param review opinia o kursie
	 * @param path scie¿ka pliku
	 * @return Degree - zaktualizowane informacje o stopniu studiowania
	 */
	public Degree updateSemester(Integer idDeg, Integer idCourse, Integer idSemester, Integer idListOfRev, Integer rate, String nameUser, String name, String review, String path) {
		Degree deg = degs.get(idDeg);
		deg.updateSemester(idCourse, idSemester, idListOfRev, rate, nameUser, name, review, path + "/" + deg.getName().replace(":"," "));
		return deg;
	}
}
