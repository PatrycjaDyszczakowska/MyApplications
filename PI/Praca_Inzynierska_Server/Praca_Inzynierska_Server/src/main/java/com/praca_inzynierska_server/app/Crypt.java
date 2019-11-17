package com.praca_inzynierska_server.app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Klasa odpowiadaj�ca za szyfrowanie has�a
 * Do szyfrowania has�a wykorzystany zosta� nastepuj�cy
 * kod: http://www.java2s.com/Code/Java/Security/UseMD5toencryptastring.htm
 */
public class Crypt {
	/**
	 * Zmienna crypt odpowiadaj�ca za rodzaj 
	 * funkcji skr�tu odpowiadaj�cy za szyfrowanie has�a
	 */
	private static MessageDigest crypt;
	/**
	 * Ustawienie Funkcji skr�tu na SHA-256
	 */
	static {
		try {
			crypt = MessageDigest.getInstance("SHA-256");
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metoda odpowiadaj�ca za szyfrowanie has�a
	 * @param password Has�o jakie metoda ma zaszyfrowa�
	 * @return Zaszyfrowane has�o
	 */
	public static String cryptPassword(String password) {
		if(password == null || password.length() == 0) {
			throw new IllegalArgumentException("Has�o b��dne");
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
