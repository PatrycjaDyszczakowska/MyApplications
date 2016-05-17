package com.example.patrycja.monopolygame;

import com.example.patrycja.monopolygame.Tables.CardProperty;
/**
 * @author Patrycja Dyszczakowska
 * Klasa zajmująca się nieruchomościami
 */
public class BuyProperty {
    CashMenager money = new CashMenager();;

	/**
	 * Metoda zajmująca się kupowaniem nieruchomości
	 */
    public Boolean buyProperty(String IDCard, String IDPlayer){
        int ID = 0;
        for(int i = 0; i< CardProperty.ID.length;i++){
            if(CardProperty.ID[i].equals(IDCard)){
                ID=i;
            }
        }
        int IDP = Integer.parseInt(IDPlayer);
        if(CardProperty.owner[ID].equals("bank")){
            money.removeCashMenager(CardProperty.price[ID], IDP);
            CardProperty.owner[ID]= IDPlayer;
            return true;
        }
        else{
            return false;
        }
    }
	
	/**
	 * Metoda zajmująca się sprzedaża nieruchomości
	 */
    public Boolean sellProperty(String IDCard, String IDPlayer){
        int ID = 0;
        for(int i = 0; i< CardProperty.ID.length;i++){
            if(CardProperty.ID[i].equals(IDCard)){
                ID=i;
            }
        }
        int IDP = Integer.parseInt(IDPlayer);
        if(CardProperty.owner[ID].equals(IDPlayer)){
            money.addCashMenager(CardProperty.mortgage[ID], IDP);
            CardProperty.owner[ID]="bank";
            return true;
        }
        else{
            return false;
        }
    }
	/**
	 * Metoda zajmująca się kupowianiem domków
	 */
    public Boolean buyHause(String IDCard, String IDPlayer){
        int ID = 0;
        for(int i = 0; i< CardProperty.ID.length;i++){
            if(CardProperty.ID[i].equals(IDCard)){
                ID=i;
            }
        }
        int IDP = Integer.parseInt(IDPlayer);
        if(CardProperty.owner[ID].equals(IDPlayer)) {
            if (CardProperty.numberHause[ID] < 5) {
                money.removeCashMenager(CardProperty.priceHause[ID], IDP);
                CardProperty.numberHause[ID] = CardProperty.numberHause[ID] + 1;
            }
            return true;
        }
        else{
            return false;
        }
    }
}