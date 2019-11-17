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
import javax.mail.*;
import javax.mail.internet.*;
/**
 * Klasa odpowiadajπca za informacje pobierane przez klienta
 */
public class ServerTree {
	/**
	 * Tabele string odpowiadajπce za zamienianie s≥Ûw na polskie znaki
	 */
	private static String[] tab = {"+","$1","$2","$3","$4","$5","$6","$7","$8","$9","$D1","$D2","$D3","$D4","$D5","$D6","$D7","$D8","$D9"};
	private static String[] tabPol = {" ","π","Ê","Í","≥","Ò","Û","ú","ø","ü","•","∆"," ","£","—","”","å","Ø","è"};
	/**
	 * Tablica departamentÛw
	 */
	private static List<Departaments> deps = new ArrayList<>();
	/**
	 * Tablica uøytkownikÛw
	 */
	private static Map<String, User> user = new HashMap<>();
	/**
	 * Konstruktor klasy odpowiadajπcy za wczytywanie wydza≥Ûw z pliku
	 */
	public ServerTree(){
		try{
			Scanner s = new Scanner(new File("src/database/departaments.txt"));
			while(s.hasNextLine()){
				String next = s.nextLine();
				if(!next.equals("")) {
					createDep(next);
				}
			}
			s.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Scanner user = new Scanner(new File("src/database/User.txt"));
			while(user.hasNextLine()) {
				String next = user.nextLine();
				if(!next.equals("")){
					String[] userDate = next.split("&");
					createUser(userDate[0],userDate[1],userDate[2],userDate[3],Boolean.parseBoolean(userDate[4]));
				}
			}
			user.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metoda wysy≥ajπca informacje o danym uøytkowniku
	 * @param nummberInd numer Indeksu(Login) wybranego uøytkownika
	 * @return User - informacje o danym uøytkowniku
	 */
	public User getUser(String nummberInd) {
		return user.get(nummberInd);
	}
	/**
	 * Metoda dodawania nowego uøytkownika wywo≥ana jest z konstruktora
	 * @param numberInd numer Indeksu(Login) wybranego uøytkownika
	 * @param password has≥o uøytkownika
	 * @param adminuser informacja czy uøytkownik jest Adminem lub Zwyk≥ym uøytkownikiem
	 * @param numberAcc klucz aktywacyjny
	 * @param acc true - gry aktywne konto, false - gdy konto jest nie aktywne 
	 * @return User - zwraca informacje o zapisanym uøytkowniku
	 */
	private User createUser(String numberInd, String password, String adminuser, String numberAcc, Boolean acc) {
		User newuser = new User(Crypt.cryptPassword(password), adminuser, numberAcc, acc);
		user.put(numberInd, newuser);
		return newuser;
	}
	/**
	 * Metoda dodawania nowego uøytkownika
	 * @param numberInd numer Indeksu(Login) wybranego uøytkownika
	 * @param password has≥o uøytkownika
	 * @return User - zwraca informacje o nowym uøytkowniku
	 */
	public User createUser(String numberInd, String password) {
		String replacaPassword = replace(password);
		String adminuser = "User";
		String numberAcc = sendMail(numberInd, password);
		Boolean acc = false;
		try {
			Writer add = new BufferedWriter(new FileWriter("src/database/User.txt",true));
			add.append("\n"+numberInd+"&"+replacaPassword+"&"+adminuser+"&"+numberAcc+"&"+acc);
			add.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		User newuser = new User(Crypt.cryptPassword(replacaPassword), adminuser, numberAcc ,acc);
		user.put(numberInd, newuser);
		return newuser;
	}
	/**
	 * Metoda tworzπca klucz aktywacyjny do konta 
	 * @return String - wygenerowany klucz
	 */
	private String generateAcc() {
		Random random = new Random();
		String generate = "0123456789abcdefghijklmnopqrstuvwxyz0123456789";
		String generateLetter = "";
		while(generateLetter.length() != 6) {
			generateLetter += generate.charAt(random.nextInt(generate.length()));
		}
		return generateLetter;
	}
	/**
	 * Metoda wysy≥ajπca e-mail
	 * Do swysy≥ania e-maila wykorzystany zosta≥ nastepujπcy
	 * przyk≥ad: https://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/ 
	 * @param numberInd numer Indeksu(Login) uøytkownika
	 * @param password has≥o uøytkownika
	 * @return String - wygenerowany klucz
	 */
	private String sendMail(String numberInd, String password) {
		String stringAcc = generateAcc();
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("app.praca.inz@gmail.com","app2017klient-server");
				}
			});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("app.praca.inz@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(numberInd+"@student.pwr.edu.pl"));
			message.setSubject("Mail aktywacyjny aplikacji mobilnej");
			message.setText("DzieÒ Dobry,"
					+ "\nJeúli nie Logowa≥eú siÍ do Aplikacji mobilnej do przeglπdania i oceny kursÛw, to zignoruj poniøsze informacje."
					+ "\n\n--------------------------------------------------------------------------------------------------------------------"
					+ "\n--------------------------------------------------------------------------------------------------------------------"
					+ "\n\nTwoj Dane:"
					+ "\nLogin: "+numberInd
					+ "\nHas≥o: "+password
					+ "\nNumer Aktywacyjny: "+ stringAcc
					+ "\n\n\nZ powaøaniem,"
					+ "\nAdministratorzy");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return stringAcc;
	}
	/**
	 * Metoda wysy≥ajπca tablice wydza≥Ûw do klienta
	 * @return List<> - tablica wydzia≥Ûw
	 */
	public List<Departaments> getAllDep() {
		return deps;
	}
	/**
	 * Metoda wyúwietlajπca wybrany wydza≥
	 * @param id identyfikator wydzia≥u
	 * @return Departaments - informacje o wybranym wydziale 
	 */
	public Departaments getDep(Integer id) {
		return deps.get(id);
	}
	/**
	 * Metoda zmieniajπca na polskie znaki
	 * @param name s≥owo/a bez polskich znakÛw
	 * @return String - zmienione s≥owo/a z polskimi znakami
	 */
	public static String replace(String name) {
		name = name.trim();
		for(int i=0;i<tab.length;i++) {
			name = name.replace(tab[i], tabPol[i]);
		}
		return name;
	}
	/**
	 * Metoda dodajπca nowy wydzia≥ wywo≥ana jest z konstruktora
	 * @param name nazwa wydzia≥u
	 * @return Departaments - zwraca informacje o zapisanym wydziale
	 */
	private Departaments createDep(String name) {
		Departaments dep = new Departaments(deps.size(),replace(name),"src/database/");
		deps.add(deps.size(),dep);
		return dep;
	}
	/**
	 * Metoda dodajπca nowy wydzia≥
	 * @param id numer identyfikatora wydzia≥u
	 * @param name nazwa wydzia≥u
	 * @return Departaments - zwraca informacje o nowym wydziale
	 */
	public Departaments createDep(Integer id, String name){
		String nameRep = replace(name);
		try {
			Files.createDirectories(Paths.get("src/database/"+nameRep.replace(":"," ")));
			Files.createFile(Paths.get("src/database/"+nameRep.replace(":"," ")+"/specializations.txt"));
			System.out.println("Katalog : src/database/"+nameRep.replace(":"," "));
			System.out.println("Plik: src/database/"+nameRep.replace(":"," ")+"/specializations.txt");
			
			Writer add = new BufferedWriter(new FileWriter("src/database/departaments.txt",true));
			add.append("\n"+nameRep);
			add.close();
		}catch (FileAlreadyExistsException e) {
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (InvalidPathException c) {
			c.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		Departaments dep = new Departaments(id, nameRep);
		deps.add(id,dep);
		return dep;
	}
	/**
	 * Metoda dodajπca do wydza≥u nowych kierunkÛw
	 * @param idDep numer identyfikatora wydzia≥u
	 * @param idSpec numer identyfikatora kierunku 
	 * @param name nazwa kierunku
	 * @return Departaments - zaktualizowane informacje o wydziale
	 */
	public Departaments updateDep(Integer idDep, Integer idSpec, String name) {
		Departaments dep = deps.get(idDep);
		dep.createSpec(idSpec, replace(name), "src/database/"+dep.getName().replace(":"," "));
		return dep;
	}
	/**
	 * Metoda aktualizujπca kierunki w danym wydzale
	 * @param idDep numer identyfikatora wydzia≥u
	 * @param idSpec numer identyfikatora kierunku 
	 * @param idDeg numer identyfikatora stopnia studiowania
	 * @param name nazwa stopnia studiowania
	 * @return Departaments - zaktualizowane informacje o wydziale
	 */
	public Departaments updateSpec(Integer idDep, Integer idSpec, Integer idDeg, String name) {
		Departaments dep = deps.get(idDep);
		dep.updateSpec(idSpec, idDeg, replace(name), "src/database/"+dep.getName().replace(":"," "));
		return dep;
	}
	/**
	 * Metoda aktualizujπca stopnie studiowania w danym wydzale
	 * @param idDep numer identyfikatora wydzia≥u
	 * @param idSpec numer identyfikatora kierunku 
	 * @param idDeg numer identyfikatora stopnia studiowania
	 * @param idCourse numer identyfikatora kursu
	 * @param name nazwa kursu
	 * @return Departaments - zaktualizowane informacje o wydziale
	 */
	public Departaments updateDeg(Integer idDep, Integer idSpec, Integer idDeg, Integer idCourse, String name) {
		Departaments dep = deps.get(idDep);
		dep.updateDeg(idSpec, idDeg, idCourse, replace(name), "src/database/"+dep.getName().replace(":"," "));
		return dep;
	}
	/**
	 * Metoda aktualizujπca kursy w danym wydzale
	 * @param idDep numer identyfikatora wydzia≥u
	 * @param idSpec numer identyfikatora kierunku 
	 * @param idDeg numer identyfikatora stopnia studiowania
	 * @param idCourse numer identyfikatora kursu
	 * @param idSemester numer identyfikatora semestru
	 * @param name nazwa semestru
	 * @return Departaments - zaktualizowane informacje o wydziale
	 */
	public Departaments updateCourse(Integer idDep, Integer idSpec, Integer idDeg, Integer idCourse, Integer idSemester, String name) {
		Departaments dep = deps.get(idDep);
		dep.updateCourse(idSpec, idDeg, idCourse, idSemester, replace(name), "src/database/"+dep.getName().replace(":"," "));
		return dep;
	}
	/**
	 * Metoda aktualizujπca semestry w danym wydzale
	 * @param idDep numer identyfikatora wydzia≥u
	 * @param idSpec numer identyfikatora kierunku 
	 * @param idDeg numer identyfikatora stopnia studiowania
	 * @param idCourse numer identyfikatora kursu
	 * @param idSemester numer identyfikatora semestru
	 * @param idListOfRev numer identyfikatora opinii
	 * @param rate ocena kursu od 1..5
	 * @param nameUser nazwa uøytkownika(login)
	 * @param name imie i nazwisko prowadzπcego
	 * @param review opinia o kursie
	 * @return Departaments - zaktualizowane informacje o wydziale
	 */
	public Departaments updateSemester(Integer idDep, Integer idSpec, Integer idDeg, Integer idCourse, Integer idSemester, Integer idListOfRev, Integer rate, String nameUser, String name, String review) {
		Departaments dep = deps.get(idDep);
		dep.updateSemester(idSpec, idDeg, idCourse, idSemester, idListOfRev, rate, replace(nameUser), replace(name), replace(review), "src/database/"+dep.getName().replace(":"," "));
		return dep;
	}
}
