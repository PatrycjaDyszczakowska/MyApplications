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
 * Klasa odpowiadaj¹ca za infromacje o stopniu studiowania
 */
public class Degree {
	/**
	 * Zmienne odpowiadaj¹ce za numer identyfikatora oraz nazwe stopnia studiowania
	 */
	private Integer id;
	private String name;
	/**
	 * Tablica kursów
	 */
	private List<Course> courses = new ArrayList<>();
	/**
	 * Konstruktor klasy odpowiadaj¹cy za wczytywanie kursów z pliku
	 * oraz zapisanie informacje o stopniu studiowania
	 * @param id numer identyfikacyjny stopnia studiowania
	 * @param name nazwa stopnia studiowaia
	 * @param path scie¿ka pliku
	 */
	public Degree(Integer id, String name, String path) {
		this.id = id;
		this.name = name;
		try{
			Scanner s = new Scanner(new File(path+name.trim().replace(":"," ")+"/courses.txt"));
			while(s.hasNextLine()){
				String next = s.nextLine();
				if(!next.equals("")) {
					createCourse(next,path+name+"/");
				}
			}
			s.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Konstruktor klasy odpowiadaj¹cy za zapisanie infromacji o stopniu studiowania
	 * @param id numer identyfikacyjny stopnia studiowania
	 * @param name nazwa stopnia studiowaia
	 */
	public Degree(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	/**
	 * Metoda pobieraj¹ca identyfikator stopnia studiowania
	 * @return Integer - identyfikator stopnia studiowania
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Metoda pobieraj¹ca nazwe stopnia studiowania
	 * @return String - nazwa stopnia studiowania
	 */
	public String getName() {
		return name;
	}
	/**
	 * Metoda wysy³aj¹ca tablice kursów do klienta
	 * @return List<> - tablica kursów
	 */
	public List<Course> getAllCourse() {
		return courses;
	}
	/**
	 * Metoda wyœwietlaj¹ca wybrany kurs
	 * @param id identyfikator kursu
	 * @return Course - informacje o wybranym kursie
	 */
	public Course getCourse(Integer id) {
		return courses.get(id);
	}
	/**
	 * Metoda dodaj¹ca nowy kurs wywo³ana jest z konstruktora
	 * @param name nazwa kursu
	 * @param path scie¿ka pliku
	 * @return Course - zwraca informacje o zapisanym kursie
	 */
	private Course createCourse(String name, String path) {
		Course course = new Course(courses.size(),name,path);
		courses.add(courses.size(),course);
		return course;
	}
	/**
	 * Metoda dodaj¹ca nowy kurs 
	 * @param id numer identyfikatora kursu
	 * @param name nazwa kursu
	 * @param path scie¿ka pliku
	 * @return Course - zwraca informacje o nowym kursie
	 */
	public Course createCourse(Integer id, String name, String path) {
		try {
			Files.createDirectories(Paths.get(path+"/"+name.replace(":"," ")));
			Files.createFile(Paths.get(path+"/"+name.replace(":"," ")+"/semester.txt"));
			System.out.println("Katalog :"+path+"/"+name.replace(":"," "));
			System.out.println("Plik: "+path+"/"+name.replace(":"," ")+"/semester.txt");
			
			Writer add = new BufferedWriter(new FileWriter(path+"/courses.txt",true));
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
		Course course = new Course(id, name);
		courses.add(id,course);
		return course;
	}
	/**
	 * Metoda dodaj¹ca do kursu nowych semestrów
	 * @param idCourse numer identyfikatora kursu
	 * @param idSemester numer identyfikatora semestru
	 * @param name nazwa semestru
	 * @param path scie¿ka pliku
	 * @return Course - zaktualizowane informacje o kursie
	 */
	public Course updateCourse(Integer idCourse, Integer idSemester, String name, String path) {
		Course course = courses.get(idCourse);
		course.createSemester(idSemester, name, path + "/" + course.getName().replace(":"," "));
		return course;
	}
	/**
	 * Metoda aktualizuj¹ca semestry w danym kursie
	 * @param idCourse numer identyfikatora kursu
	 * @param idSemester numer identyfikatora semestru
	 * @param idListOfRev numer identyfikatora opinii
	 * @param rate ocena kursu od 1..5
	 * @param nameUser nazwa u¿ytkownika(login)
	 * @param name imie i nazwisko prowadz¹cego
	 * @param review opinia o kursie
	 * @param path scie¿ka pliku
	 * @return Course - zaktualizowane informacje o kursie
	 */
	public Course updateSemester(Integer idCourse, Integer idSemester, Integer idListOfRev, Integer rate, String nameUser, String name, String review, String path) {
		Course course = courses.get(idCourse);
		course.updateSemester(idSemester, idListOfRev, rate, nameUser, name, review, path + "/" + course.getName().replace(":"," "));
		return course;
	}
}
