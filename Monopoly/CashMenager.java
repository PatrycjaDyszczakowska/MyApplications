package com.example.patrycja.monopolygame;

import com.example.patrycja.monopolygame.Tables.Cash;
/**
 * @author Patrycja Dyszczakowska
 * Klaza zarządzająca pieniedzmi graczy
 */
public class CashMenager {
	/**
	 * Metoda odpowiadająca za dodawanie pieniedzy gracza
	 */
    public void addCashMenager(int money, int IDPlayer){
        int cash = Cash.cash[IDPlayer];
        cash = cash+money;
        Cash.cash[IDPlayer]=cash;
    }
	/**
	 * Metoda odpowiadająca za odejmowanie pieniedzy gracza
	 */
    public void removeCashMenager(int money, int IDPlayer){
        int cash = Cash.cash[IDPlayer];
        cash = cash-money;
        Cash.cash[IDPlayer]=cash;
    }
}
