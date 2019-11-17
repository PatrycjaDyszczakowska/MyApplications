package com.praca_inzynierska_server.app;
/**
 * 
 * @author Patrycja Dyszczakowska,
 * Nr Indeksu: 208795
 * @version 1.0
 * Aplikacja mobilna do przegl¹dania i oceny kursów - Serwer
 * Serwer stworzony by³ we wzorcu REST na podstawie nastepuj¹cego 
 * tutoriala: https://www.mscharhag.com/java/building-rest-api-with-spark
 * 
 * G³ówna klas odpowiadaj¹ca za inicjacje klass StartServer oraz ServerTree
 */
public class Main {
	public static void main(String[] args) {
		new StartServer(new ServerTree());
	}
}
