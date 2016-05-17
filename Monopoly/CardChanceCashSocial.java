package com.example.patrycja.monopolygame;

import com.example.patrycja.monopolygame.Tables.CardCashSocial;
import com.example.patrycja.monopolygame.Tables.CardChance;

/**
 * @author Patrycja Dyszczakowska
 * Klasa zarządający kartami szansy oraz kasy społecznej
 */
public class CardChanceCashSocial {
    CashMenager money = new CashMenager();
	/**
	 * Metoda zajmująca sie sprawdzaniem która karta została wybrana
	 */
    public String CardChanceCashSocial(String IDCard, String type, String IDPlyer){
        int IDP = Integer.parseInt(IDPlyer);
        if(type.equals("Chance")){
            return cardChance(IDCard, IDP);
        }
        else{
            return cardCashSocial(IDCard, IDP);
        }
    }
	/**
	 * Metoda zajmująca sie kartami szansy
	 */
    public String cardChance(String IDCard, int IDP){
        int ID=0;
        for(int i = 0; i< CardChance.ID.length;i++){
            if(CardChance.ID[i].equals(IDCard)){
                ID=i;
            }
        }
        String a = ""+IDCard.charAt(2);
        if(a.equals("a")){
            money.addCashMenager(CardChance.pay[ID],IDP);
        }
        if (a.equals("s")) {
                money.removeCashMenager(CardChance.pay[ID], IDP);
        }
        return CardChance.text[ID];
    }
	/**
	 * Metoda zajmująca się kartami kasy spolecznej
	 */
    public String cardCashSocial(String IDCard, int IDP){
        int ID=0;
        for(int i = 0; i< CardCashSocial.ID.length;i++){
            if(CardCashSocial.ID[i].equals(IDCard)){
                ID=i;
            }
        }
        String a = ""+IDCard.charAt(2);
        if(a.equals("a")){
            money.addCashMenager(CardChance.pay[ID],IDP);
        }
        if (a.equals("s")) {
            money.removeCashMenager(CardChance.pay[ID],IDP);
        }
        return CardCashSocial.text[ID];
    }
}