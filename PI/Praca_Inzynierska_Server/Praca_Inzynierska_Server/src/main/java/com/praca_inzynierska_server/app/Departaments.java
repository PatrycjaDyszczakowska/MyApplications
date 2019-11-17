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
 * Klasa odpowiadaj¹ca za infromacje o wydziale
 */
public class Departaments {
	/**
	 * Zmienne odpowiadaj¹ce za numer identyfikatora oraz nazwe wydza³u
	 */
	private Integer id;
	private String name;
	/**
	 * Tablica kierunków
	 */
	private List<Specializations> specs = new ArrayList<>();
	/**
	 * Konstruktor klasy odpowiadaj¹cy za wczytywanie kierunków z pliku
	 * oraz zapisanie informacje o wydziale
	 * @param id numer identyfikacyjny wydza³u
	 * @param name nazwa wydza³u
	 * @param path scie¿ka pliku
	 */
	public Departaments(Integer id, String name, String path) {
		this.id = id;
		this.name = name;
		try{
			Scanner s = new Scanner(new File(path+name.trim().replace(":"," ")+"/specializations.txt"));
			while(s.hasNextLine()){
				String next = s.nextLine();
				if(!next.equals("")) {
					createSpec(next,path+name+"/");
				}
			}
			s.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Konstruktor klasy odpowiadaj¹cy za zapisanie infromacji o wydzale
	 * @param id numer identyfikacyjny wydza³u
	 * @param name nazwa wydza³u
	 */
	public Departaments(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	/**
	 * Metoda pobieraj¹ca identyfikator wydza³u
	 * @return Integer - identyfikator wydza³u
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Metoda pobieraj¹ca nazwe wydzia³u
	 * @return String - nazwa wydza³u
	 */
	public String getName() {
		return name;
	}
	/**
	 * Metoda wysy³aj¹ca tablice kierunków do klienta
	 * @return List<> - tablica kierunków
	 */
	public List<Specializations> getAllSpec() {
		return specs;
	}
	/**
	 * Metoda wyœwietlaj¹ca wybrany kierunek
	 * @param id identyfikator kierunku
	 * @return Specializations - informacje o wybranym kierunku
	 */
	public Specializations getSpec(Integer id) {
		return specs.get(id);
	}
	/**
	 * Metoda dodaj¹ca nowy kierunek wywo³ana jest z konstruktora
	 * @param name nazwa kierunku
	 * @param path scie¿ka plikuscie¿ka pliku
	 * @return Specializations - zwraca informacje o zapisanym kierunku
	 */
	private Specializations createSpec(String name, String path) {
		Specializations spec = new Specializations(specs.size(),name,path);
		specs.add(specs.size(),spec);
		return spec;
	}
	/**
	 * Metoda dodaj¹ca nowy kierunek
	 * @param id numer identyfikatora kierunku
	 * @param name nazwa kierunku
	 * @param path scie¿ka pliku
	 * @return Specializations - zwraca informacje o nowym kierunku
	 */
	public Specializations createSpec(Integer id, String name, String path) {
		try {
			Files.createDirectories(Paths.get(path+"/"+name.replace(":"," ")));
			Files.createFile(Paths.get(path+"/"+name.replace(":"," ")+"/degree.txt"));
			System.out.println("Katalog :"+path+"/"+name.replace(":"," "));
			System.out.println("Plik: "+path+"/"+name.replace(":"," ")+"/degree.txt");
			
			Writer add = new BufferedWriter(new FileWriter(path+"/specializations.txt",true));
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
		Specializations spec = new Specializations(id, name);
		specs.add(id,spec);
		return spec;
	}
	/**
	 * Metoda dodaj¹ca do kierunku nowych stopnii studiowania
	 * @param idSpec numer identyfikatora kierunku 
	 * @param idDeg numer identyfikatora stopnia studiowania
	 * @param name nazwa stopnia studiowania
	 * @param path scie¿ka pliku
	 * @return Specializations - zaktualizowane informacje o kierunku
	 */
	public Specializations updateSpec(Integer idSpec, Integer idDeg, String name, String path) {
		Specializations spec = specs.get(idSpec);
		spec.createDeg(idDeg, name, path+"/"+spec.getName().replace(":"," "));
		return spec;
	}
	/**
	 * Metoda aktualizuj¹ca stopnie studiowania w danym kierunku
	 * @param idSpec numer identyfikatora kierunku 
	 * @param idDeg numer identyfikatora stopnia studiowania
	 * @param idCourse numer identyfikatora kursu
	 * @param name nazwa kursu
	 * @param path scie¿ka pliku
	 * @return Specializations - zaktualizowane informacje o kierunku
	 */
	public Specializations updateDeg(Integer idSpec, Integer idDeg, Integer idCourse, String name, String path) {
		Specializations spec = specs.get(idSpec);
		spec.updateDeg(idDeg, idCourse, name, path+"/"+spec.getName().replace(":"," "));
		return spec;
	}
	/**
	 * Metoda aktualizuj¹ca kursy w danym kierunku
	 * @param idSpec numer identyfikatora kierunku 
	 * @param idDeg numer identyfikatora stopnia studiowania
	 * @param idCourse numer identyfikatora kursu
	 * @param idSemester numer identyfikatora semestru
	 * @param name nazwa semestru
	 * @param path scie¿ka pliku
	 * @return Specializations - zaktualizowane informacje o kierunku
	 */
	public Specializations updateCourse(Integer idSpec, Integer idDeg, Integer idCourse, Integer idSemester, String name, String path) {
		Specializations spec = specs.get(idSpec);
		spec.updateCourse(idDeg, idCourse, idSemester, name, path+"/"+spec.getName().replace(":"," "));
		return spec;
	}
	/**
	 * Metoda aktualizuj¹ca semestry w danym kierunku
	 * @param idSpec numer identyfikatora kierunku 
	 * @param idDeg numer identyfikatora stopnia studiowania
	 * @param idCourse numer identyfikatora kursu
	 * @param idSemester numer identyfikatora semestru
	 * @param idListOfRev numer identyfikatora opinii
	 * @param rate ocena kursu od 1..5
	 * @param nameUser nazwa u¿ytkownika(login)
	 * @param name imie i nazwisko prowadz¹cego
	 * @param review opinia o kursie
	 * @param path scie¿ka pliku
	 * @return Specializations - zaktualizowane informacje o kierunku
	 */
	public Specializations updateSemester(Integer idSpec, Integer idDeg, Integer idCourse, Integer idSemester, Integer idListOfRev, Integer rate, String nameUser, String name, String review, String path) {
		Specializations spec = specs.get(idSpec);
		spec.updateSemester(idDeg, idCourse, idSemester, idListOfRev, rate, nameUser, name, review, path+"/"+spec.getName().replace(":"," "));
		return spec;
	}
}
