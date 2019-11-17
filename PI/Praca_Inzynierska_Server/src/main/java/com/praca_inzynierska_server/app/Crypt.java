package com.praca_inzynierska_server.app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Klasa odpowiadaj¹ca za szyfrowanie has³a
 * Do szyfrowania has³a wykorzystany zosta³ nastepuj¹cy
 * kod: http://www.java2s.com/Code/Java/Security/UseMD5toencryptastring.htm
 */
public class Crypt {
	/**
	 * Zmienna crypt odpowiadaj¹ca za rodzaj 
	 * funkcji skrótu odpowiadaj¹cy za szyfrowanie has³a
	 */
	private static MessageDigest crypt;
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
	 * Metoda odpowiadaj¹ca za szyfrowanie has³a
	 * @param password Has³o jakie metoda ma zaszyfrowaæ
	 * @return Zaszyfrowane has³o
	 */
	public static String cryptPassword(String password) {
		if(password == null || password.length() == 0) {
			throw new IllegalArgumentException("Has³o b³êdne");
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
	
}
