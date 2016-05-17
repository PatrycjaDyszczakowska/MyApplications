package com.example.patrycja.monopolygame.Tables;

/**
 * @author Patrycja Dyszczakowskas
 * KLasa przechowująca dane o graczu
 */ 
public class Players {
	/**
	 * Zmienna odpowiadająca za ilość graczy
	 */
    public static int myID = 2;
	/**
	 * Tablica przechowująca id graczy
	 */
    public static int ID[] = {
            0,
            0,
            0,
            0
    };
	/**
	 * Tablica przechowująca nick graczy
	 */
    public static String nick[] = {
            "",
            "",
            "",
            ""
    };
	/**
	 * Tablica przechowująca kolejność graczy
	 */
    public static int sequence[] = {
            0,
            0,
            -1,
            -1
    };
}
