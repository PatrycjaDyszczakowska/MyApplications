package com.example.patrycja.monopolygame.Tables;

/**
 * @author Patrycja Dyszczakowska
 * Klasa gdzie przechowywane są dane kart kasy społecznej
 */
public class CardCashSocial {
	/**
	 * Tablica przechowująca id karty
	 */
    public static String ID[] = {
            "s_add1",
            "s_add2",
            "s_add3",
            "s_add4",
            "s_sub1",
            "s_sub2",
            "s_sub3",
            "s_start",
            "s_prisonIN",
            "s_prisonOUT"
    };
	/**
	 * Tablica zajmująca się trescią karty
	 */
    public static String text[] = {
            "Wygrana druga nagroda w konkursie piękności.\nPobierz 10 zł.",
            "Otrzymujesz zwrot podatku dochodowego.\n Pobierz 20 zł.",
            "Błąd bankowy na twoją korzyść.\n Pobierz 200 zł.",
            "Odziedziczyłeś w spadku 100 zł.",
            "Zapłać rachunek za szpital 100 zł.",
            "Zapłać składkę ubezpieczeniową 50 zł.",
            "Honorarium lekarza.\n Zapłać 150 zł.",
            "Przejdź na START.",
            "Idź do WIĘZIENIA.\n Przejdź prosto do WIĘZIENIA.\n Nie przechodź przez START.\n Nie pobieraj 200 zł.",
            "WYJDZ BEZPŁATNIE Z WIĘZIENIA\n\n Tę kartę możesz zatrzymać\n do póżniejszego wykorzystania lub sprzedaży."
    };
	/**
	 * Tablica ile dostanie lub odda pieniędzy
	 * gracz po wylosowaniu danej karty
	 */
    public static int pay[] ={
            10,
            20,
            200,
            100,
            100,
            50,
            150,
            0,
            0,
            0
    };
}