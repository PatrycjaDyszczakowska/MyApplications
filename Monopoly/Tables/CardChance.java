package com.example.patrycja.monopolygame.Tables;

/**
 * @author Patrycja Dyszczakowska
 * Klasa gdzie przechowywane są dane kart szansy
 */
public class CardChance {
	/**
	 * Tablica przechowująca id karty
	 */
    public static String ID[] = {
            "c_add1",
            "c_add2",
            "c_sub1",
            "c_sub2",
            "c_field1",
            "c_field2",
            "c_prisonIN",
            "c_prisonOUT",
            "c_start",
            "c_back"
    };
	/**
	 * Tablica zajmująca się trescią karty
	 */
    public static String text[] = {
            "Wygrałeś konkurs krzyżówkowy.\n Pobierz 100 zł.",
            "Bank wypłaci ci dywidendę w wysokości 50 zł.",
            "Mandat za przekroczenie szybkości.\n Zapłać 15 zł",
            "Zapłać za szkołę, 150 zł.",
            "Przejdź na Plac Wilsona.\n Jeśli miniesz po drodze START, pobierz 200 zł.",
            "Przejdź na Dworzec Gdański\n Jeśli miniesz po drodze START, pobierz 200 zł.",
            "Idź do WIĘZIENIA.\n Przejdź prosto do WIĘZIENIA.\n Nie przechodź przez START.\n Nie pobieraj 200 zł.",
            "WYJDZ BEZPŁATNIE Z WIĘZIENIA\n\n Tę kartę możesz zatrzymać\n do póżniejszego wykorzystania lub sprzedaży.",
            "Przejdż na START",
            "Cofnij się o trzy pola."
    };
	/**
	 * Tablica ile dostanie lub odda pieniędzy
	 * gracz po wylosowaniu danej karty
	 */
    public static int pay[] ={
            100,
            50,
            15,
            150,
            0,
            0,
            0,
            0,
            0,
            0
    };
}
