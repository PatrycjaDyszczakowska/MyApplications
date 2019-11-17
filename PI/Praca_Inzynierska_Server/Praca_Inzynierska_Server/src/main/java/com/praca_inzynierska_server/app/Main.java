package com.praca_inzynierska_server.app;
/**
 * 
 * @author Patrycja Dyszczakowska,
 * Nr Indeksu: 208795
 * @version 1.0
 * Aplikacja mobilna do przegl�dania i oceny kurs�w - Serwer
 * Serwer stworzony by� we wzorcu REST na podstawie nastepuj�cego 
 * tutoriala: https://www.mscharhag.com/java/building-rest-api-with-spark
 * 
 * G��wna klas odpowiadaj�ca za inicjacje klass StartServer oraz ServerTree
 */
public class Main {
	public static void main(String[] args) {
		new StartServer(new ServerTree());
	}
}
