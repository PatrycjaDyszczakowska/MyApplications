package com.example.patrycja.praca_inzynierska.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Klasa odpowiadająca za informacje o użytkowniku
 * Do szyfrowania hasła wykorzystany został nastepujący
 * kod: http://www.java2s.com/Code/Java/Security/UseMD5toencryptastring.htm
 */
public class Data {
    /**
     * Zmienne string odpowiadające za potrzebne informacje o uzytkowniku w aplikacji
     */
    public static String nameUser="";
    public static String adminuser = "guest";
    /**
     * Zmienna crypt odpowiadająca za rodzaj
     * funkcji skrótu odpowiadający za szyfrowanie hasła
     */
    private static MessageDigest crypt;
    /**
     * Tabele string odpowiadające za zamienianie słów na zdania bez polskich znaków
     */
    private static String[] tab = {"+","$1","$2","$3","$4","$5","$6","$7","$8","$9","$D1","$D2","$D3","$D4","$D5","$D6","$D7","$D8","$D9"};
    private static String[] tabPol = {" ","ą","ć","ę","ł","ń","ó","ś","ż","ź","Ą","Ć","Ę","Ł","Ń","Ó","Ś","Ż","Ź"};
    /**
     * Ustawienie Funkcji skrótu na SHA-256
     */
    static {
        try {
            crypt = MessageDigest.getInstance("SHA-256");
        }catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metoda zmieniająca polskie znaki
     * @param name słowo/a z polskimi znakami
     * @return String - zmienione słowo/a bez polskich znaków
     */
    public static String replace(String name){
        for(int i=0;i<tab.length;i++) {
            name = name.replace(tabPol[i], tab[i]);
        }
        return name;
    }
    /**
     * Metoda odpowiadająca za szyfrowanie hasła
     * @param password Hasło jakie metoda ma zaszyfrować
     * @return Zaszyfrowane hasło
     */
    public static String cryptPassword(String password) {
        if(password == null || password.length() == 0) {
            throw new IllegalArgumentException("Hasło błędne");
        }

        crypt.update(password.getBytes());
        byte[] hash = crypt.digest();
        StringBuffer cryptedPassword = new StringBuffer();
        for(int i=0; i<hash.length;i++) {
            if((0xff & hash[i]) < 0x10) {
                cryptedPassword.append("0"+Integer.toHexString((0xFF & hash[i])));
            }else {
                cryptedPassword.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return cryptedPassword.toString();
    }
    /**
     * Metoda szukająca te same elementy
     * @param name nowa nazwa elementu
     * @param list list elementów pobranych z serwera
     * @return true - gdy została znaleziona taka sama nazwa,
     * false - gry nie powtarza się nazwa
     */
    public static Boolean searchElement(String name, ArrayList<String> list){
        for(int i=0; i<list.size();i++){
            if(name.equals(list.get(i))){
                return true;
            }
        }
        return false;
    }
}
