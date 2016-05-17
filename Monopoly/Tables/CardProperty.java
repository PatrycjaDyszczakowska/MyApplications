package com.example.patrycja.monopolygame.Tables;

/**
 * @author Patrycja Dyszczakowska
 * Klasa przechowująca dane o nieruchomościach
 */
public class CardProperty {
		/**
		 * Tablica przechowująca id nieruchomości
		 */
        public static String ID[] = {
                "s1",
                "s2",
                "rw",
                "s3",
                "s4",
                "s5",
                "s6",
                "ps",
                "s7",
                "s8",
                "rn",
                "s9",
                "s10",
                "s11",
                "s12",
                "s13",
                "s14",
                "re",
                "s15",
                "s16",
                "h2o",
                "s17",
                "s18",
                "s19",
                "s20",
                "rs",
                "s21",
                "s22",
        };
		/**
		 * Tablica przechowująca kto jest właścicielem nieruchomości
		 */
        public static String owner[] = {
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
                "bank",
        };
		/**
		 * Tablica przechowująca nazwy nieruchomości
		 */
        public static String text[]={
                "ULICA KONOPACKA",
                "ULICA STALOWA",
                "DWORZEC ZACHODNI",
                "ULICA RADZYMIŃSKA",
                "ULICA JAGIELLOŃSKA",
                "ULICA TARGOWA",
                "ULICA PŁOWIECKA",
                "ELEKTROWNIA",
                "ULICA MARSA",
                "ULICA GROCHOWSKA",
                "DWORZEC GDAŃSKI",
                "ULICA OBOZOWA",
                "ULICA GÓRCZEWSKA",
                "ULICA WOLSKA",
                "ULICA MICKIEWICZA",
                "ULICA SŁOWACKIEGO",
                "PLAC WILSONA",
                "DWORZEC WSCHODNI",
                "ULICA ŚWIĘTOKRZYSKA",
                "KRAKOWSKIE PRZEDMIEŚCIE",
                "WODOCIĄGI",
                "NOWY ŚWIAT",
                "PLAC TRZECH KRZYŻY",
                "ULICA MARSZAŁKOWSKA",
                "ALJE JEROZOLIMSKIE",
                "DWORZEC CENTRALNY",
                "ULICA BELWEDERSKA",
                "ULICA UJAZDOWSKA"
        };
		/**
		 * Tablica przechowująca koszt zakupu nieruchomości
		 */
        public static int price[]={
                60,
                60,
                200,
                100,
                100,
                120,
                140,
                150,
                140,
                160,
                200,
                180,
                180,
                200,
                220,
                220,
                240,
                200,
                260,
                260,
                150,
                280,
                300,
                300,
                320,
                200,
                350,
                400
        };
		/**
		 * Tablica przechowująca koszt sprzedazy nieruchomości
		 */
        public static int mortgage[] = {
                30,
                30,
                100,
                50,
                50,
                60,
                70,
                75,
                70,
                80,
                100,
                90,
                90,
                100,
                110,
                110,
                120,
                100,
                130,
                130,
                75,
                140,
                150,
                150,
                160,
                100,
                175,
                200
        };
		/**
		 * Tablica przechowująca koszt podatku pustej nieruchomości
		 * gdy inny gracz staniena na innego gracza nieruchomości
		 */
        public static int rentEmptyPlot[] = {
                2,
                4,
                25,
                6,
                6,
                8,
                10,
                0,
                10,
                12,
                25,
                14,
                14,
                16,
                18,
                18,
                20,
                25,
                22,
                22,
                0,
                24,
                26,
                26,
                28,
                25,
                35,
                50
        };
		/**
		 * Tablica przechowująca koszt podatku nieruchomości z jednym domkiem
		 * gdy inny gracz staniena na innego gracza nieruchomości
		 */
        public static int rentOneHause[] = {

        };
		/**
		 * Tablica przechowująca koszt podatku nieruchomości z dwoma domkiem
		 * gdy inny gracz staniena na innego gracza nieruchomości
		 */
        public static int renttwoHause[] = {

        };
		/**
		 * Tablica przechowująca koszt podatku nieruchomości z trzema domkiem
		 * gdy inny gracz staniena na innego gracza nieruchomości
		 */
        public static int rentTreeHause[] = {

        };
		/**
		 * Tablica przechowująca koszt podatku nieruchomości z czterema domkiem
		 * gdy inny gracz staniena na innego gracza nieruchomości
		 */
        public static int rentFourHause[] = {

        };
		/**
		 * Tablica przechowująca koszt podatku nieruchomości z hotelem
		 * gdy inny gracz staniena na innego gracza nieruchomości
		 */
        public static int rentHotel[] = {

        };
		/**
		 * Tablica przechowująca koszt domku dla danej nieruchomości
		 */
        public static int priceHause[] = {
                50,
                50,
                0,
                50,
                50,
                50,
                50,
                0,
                100,
                100,
                0,
                100,
                100,
                100,
                150,
                150,
                150,
                0,
                150,
                150,
                0,
                150,
                200,
                200,
                200,
                0,
                200,
                200,
        };
		/**
		 * tablica przchowująca ilośc domku na nieruchomości
		 */
        public static int numberHause[] = {
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0
        };
}