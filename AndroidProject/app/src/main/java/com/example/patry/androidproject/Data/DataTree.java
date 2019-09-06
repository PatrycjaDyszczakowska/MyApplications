package com.example.patry.androidproject.Data;

import com.example.patry.androidproject.R;

import java.util.ArrayList;

public class DataTree {
    private static ArrayList<Category> category = new ArrayList<>();
    private static ArrayList<FavoriteItem> basket = new ArrayList<>();
    private static ArrayList<MailItem> mail = new ArrayList<>();
    private static ArrayList<String> basketHistory = new ArrayList<>();

    public DataTree(){
        new SearchTable();
        category.add(new Category("Laptopy"));
        category.get(0).setCategoryItems("Laptop MICROSOFT Surface Platinum",3899.00,"Wyświetlacz: 13.5\" (2256x1504)\n" +
                "Procesor: Intel® Core™ i5-7200U\n" +
                "Pamięć RAM: 8 GB\n" +
                "Dysk SSD: 128 GB\n" +
                "Grafika: Intel HD 620\n" +
                "System operacyjny: Windows 10 S","",R.drawable.microsoft_surface_laptop_platinum_2,false,
                13.5, "Windows 10");
        category.get(0).setCategoryItems("Laptop APPLE MacBook Air 13",3849.00,"Wyświetlacz: 13.3\" (1440x900)\n" +
                "Procesor: Intel Core i5\n" +
                "Pamięć RAM: 8 GB DDR3\n" +
                "Dysk SSD: 128 GB\n" +
                "Grafika: Intel HD 6000\n" +
                "System operacyjny: macOS Sierra","",R.drawable.mba13_pf_screen,false, 13.3,
                "macOS Sierra");
        category.get(0).setCategoryItems("Laptop ASUS VivoBook 15",2599.00,"Wyświetlacz: 15.6\" (1920x1080(Full HD))\n" +
                "Procesor: Intel® Core™ i5-8250U\n" +
                "Pamięć RAM: 8 GB DDR4\n" +
                "Dysk HDD: 1 TB\n" +
                "Grafika: NVIDIA GeForce MX130 2GB GDDR5\n" +
                "System operacyjny: Windows 10 Home PL","",R.drawable.asus_vivobook_15_f510ua_szary_1,false, 15.6,
                "Windows 10");
        category.get(0).setCategoryItems("Laptop HP 15-ra071nw",1399.00,"Wyświetlacz: 15.6\" (1366x768(HD))\n" +
                "Procesor: Intel® Pentium® N3710\n" +
                "Pamięć RAM: 4 GB DDR3\n" +
                "Dysk HDD: 500 GB\n" +
                "Grafika: Intel HD 405\n" +
                "System operacyjny: Windows 10 Home PL","",R.drawable.hp_15_bs031nw_1,false, 15.6,
                "Windows 10");
        category.get(0).setCategoryItems("Laptop APPLE MacBook Pro 13.3",5499.00,"Wyświetlacz: 13.3\" (2560x1600)\n" +
                "Procesor: Intel Core i5\n" +
                "Pamięć RAM: 8 GB DDR3\n" +
                "Dysk SSD: 128 GB\n" +
                "Grafika: Intel Iris Plus 640\n" +
                "System operacyjny: macOS Sierra","",R.drawable.mbp13rd_2016_pf_open_spgry,false, 13.3,
                "macOS Sierra");
        category.get(0).setCategoryItems("Laptop LENOVO IdeaPad",3399.00,"Wyświetlacz: 15.6\" (1920x1080(Full HD))\n" +
                        "Procesor: Intel® Core™ i5-8250U\n" +
                        "Pamięć RAM: 8 GB DDR4\n" +
                        "Dysk HDD: 1 TB\n" +
                        "Grafika: AMD Radeon RX 550M 2 GB\n" +
                        "System operacyjny: Windows 10 Home PL","",R.drawable.lenovo_ideapad_720_15_1,false, 15.6,
                "Windows 10");
        category.get(0).setCategoryItems("Laptop LENOVO Yoga Onyx Black",2399.00,"Wyświetlacz: 14\" (1920x1080(Full HD))\n" +
                "Procesor: Intel® Core™ i3-7130U\n" +
                "Pamięć RAM: 4 GB DDR4\n" +
                "Dysk HDD: 1 TB\n" +
                "Grafika: Intel HD 620\n" +
                "System operacyjny: Windows 10 Home PL","",R.drawable.lenovo_yoga_520_onyx_black_1,false, 14.0,
                "Windows 10");
        category.add(new Category("Telefony"));
        category.get(1).setCategoryItems("Smartfon SAMSUNG Galaxy S8",1849.00,"Przekątna ekranu [cal]: 5.8\n" +
                "Rozdzielczość: 1440 x 2960\n" +
                "Pamięć wewnętrzna: 64 GB\n" +
                "Pamięć RAM: 4 GB\n" +
                "Rozdzielczość aparatu [Mpix]: 12\n" +
                "System operacyjny: Android 7.0 Nougat","",R.drawable.galaxys8_silver_2,false, 5.8,
                "Android 7.0 Nougat");
        category.get(1).setCategoryItems("Smartfon XIAOMI Redmi Note 5",898.00,"Przekątna ekranu [cal]: 5.99\n" +
                "Rozdzielczość: 1080 x 2160\n" +
                "Pamięć wewnętrzna: 32 GB\n" +
                "Pamięć RAM: 3 GB\n" +
                "Rozdzielczość aparatu [Mpix]: 12 + 5\n" +
                "System operacyjny: Android 8.1 Oreo","", R.drawable.redmi_note_5,false, 5.99,
                "Android 8.1 Oreo");
        category.get(1).setCategoryItems("Smartfon SONY Xperia XA2",1248.00,"Przekątna ekranu [cal]: 5.2\n" +
                "Rozdzielczość: 1080 x 1920\n" +
                "Pamięć wewnętrzna: 32 GB\n" +
                "Pamięć RAM: 3 GB\n" +
                "Rozdzielczość aparatu [Mpix]: 23\n" +
                "System operacyjny: Android 8.0 Oreo","",R.drawable.sony_xperia_xa2,false, 5.2,
                "Android 8.0 Oreo");
        category.get(1).setCategoryItems("Smartfon APPLE iPhone 7 Plus",3268.00,"Przekątna ekranu [cal]: 5.5\n" +
                "Rozdzielczość: 1080 x 1920\n" +
                "Pamięć wewnętrzna: 128 GB\n" +
                "Pamięć RAM: 3 GB\n" +
                "Rozdzielczość aparatu [Mpix]: 2 x 12\n" +
                "System operacyjny: iOS 10","",R.drawable.iphone7plus,false, 5.5,
                "iOS 10");
        category.add(new Category("Tablety"));
        category.get(2).setCategoryItems("Tablet SAMSUNG Galaxy Tab S4",2999.00,"Ekran: 10.5\" (2560 x 1600, Super AMOLED)\n" +
                "Pamięć wbudowana: 64 GB\n" +
                "Pamięć RAM: 4 GB\n" +
                "System operacyjny: Android 8.1 Oreo\n" +
                "Ilość rdzeni procesora: 8\n" +
                "Komunikacja: WiFi 802.11 a/b/g/n/ac, Bluetooth 5.0, 4G LTE","",R.drawable.samsung_galaxy_tab_s4_czarny_1,false, 10.5,
                "Android 8.1 Oreo");
        category.get(2).setCategoryItems("Tablet HUAWEI MediaPad M5",1399.00,"Ekran: 10.1\" (1920x1200, IPS)\n" +
                "Pamięć wbudowana: 32 GB\n" +
                "Pamięć RAM: 3 GB\n" +
                "System operacyjny: Android 8.0 Oreo\n" +
                "Ilość rdzeni procesora: 8\n" +
                "Komunikacja: WiFi 802.11 a/b/g/n/ac, Bluetooth 4.2, 4G LTE","",R.drawable.mediapad_m5_lite_1,false, 10.1,
                "Android 8.0 Oreo");
        category.get(2).setCategoryItems("Tablet LENOVO Yoga Tab 3 Pro",1999.00,"Ekran: 10.1\" (2560x1600, IPS)\n" +
                "Pamięć wbudowana: 64 GB\n" +
                "Pamięć RAM: 4 GB\n" +
                "System operacyjny: Android 6.0 Marshmallow\n" +
                "Ilość rdzeni procesora: 4\n" +
                "Komunikacja: Bluetooth 4.0, WiFi 802.11 a/b/g/n/ac, 4G LTE","",R.drawable.lenovo_yoga_tab_3_pro_10_1,false, 10.1,
                "Android 6.0 Marshmallow");
        category.get(2).setCategoryItems("Tablet APPLE iPad Pro 10.5",4599.00,"Ekran: 10.5\" (2224 x 1668, Retina)\n" +
                        "Pamięć wbudowana: 512 GB\n" +
                        "Pamięć RAM: 4 GB\n" +
                        "System operacyjny: iOS 10\n" +
                        "Ilość rdzeni procesora: 6\n" +
                        "Komunikacja: WiFi 802.11 a/b/g/n/ac, Bluetooth 4.2,","",R.drawable.ipadpro105_pure_angles_space_gray,false, 10.5,
                "iOS 10");
        category.get(2).setCategoryItems("Tablet LENOVO Tab 4 8 Plus",599.00,"Ekran: 8\" (1280 x 800, IPS)\n" +
                "Pamięć wbudowana: 16 GB\n" +
                "Pamięć RAM: 3 GB\n" +
                "System operacyjny: Android 7.0 Nougat\n" +
                "Ilość rdzeni procesora: 8\n" +
                "Komunikacja: WiFi 802.11 b/g/n, Bluetooth 4.2, 4G LTE","",R.drawable.lenovo_tab_4_8_bialy_1,false, 8.0,
                "Android 7.0 Nougat");
        category.add(new Category("Laptopy"));
        category.get(3).setCategoryItems("Słuchawka Bluetooth JABRA Talk 45",279.00,"Typ Bluetooth: Wersja 4.0\n" +
                "Maksymalny czas czuwania [h]: 192\n" +
                "Maksymalny czas pracy [h]: 6","",R.drawable.jabra_talk_4,false, 0.0, "");
        category.get(3).setCategoryItems("Słuchawka Bluetooth PLANTRONICS M75",99.90,"Typ Bluetooth: Wersja 3.0 z A2DP\n" +
                "Maksymalny czas czuwania [h]: 384\n" +
                "Maksymalny czas pracy [h]: 11","",R.drawable.plantronic,false, 0.0,"");
        category.get(3).setCategoryItems("Słuchawki bezprzewodowe JBL",179.00,"Konstrukcja: Dokanałowe","",
                R.drawable.t100,false, 0.0,"");
        category.get(3).setCategoryItems("Słuchawki RAZER ManOWar",739.00,"Impedancja [Om]: 32\n" +
                "Rodzaj słuchawek: Bezprzewodowe\n" +
                "Konstrukcja: Nauszne","",R.drawable.razer_man_o_war_1,false, 0.0,"");
        category.get(3).setCategoryItems("Słuchawki STEELSERIES Arctis 3 Czarny",299.00,"Impedancja [Om]: 32\n" +
                "Konstrukcja: Nauszne\n" +
                "Wtyk/Złącza: MiniJack (3.5 mm) , 2x MiniJack (3.5 mm)","",R.drawable.ar3,false, 0.0,"");
        category.get(3).setCategoryItems("Słuchawki przewodowe LOGITECH G231",249.00,"Impedancja [Om]: 32\n" +
                "Rodzaj słuchawek: Przewodowe\n" +
                "Konstrukcja: Nauszne\n" +
                "Wtyk/Złącza: MiniJack (3.5 mm) 4-stykowy","",R.drawable.logitech_g231_prodigy_1,false, 0.0,
                "");
    }

    public static ArrayList<Category> getCategory() {
        return category;
    }

    public static ArrayList<CategoryItem> getCategoryItem(Integer id){
        return category.get(id).getCategoryItems();
    }

    public static ArrayList<FavoriteItem> getBasket() {
        return basket;
    }

    public static void addToBasket(Integer category, Integer position, String name, Double price, String info_short, String info_long, Integer image_id, Boolean favorite){
        basket.add(new FavoriteItem(category, position, name, price, info_short, info_long, image_id, favorite));
    }
    public static void deleteElementFromBasket(Integer position){
        basket.remove(position);
    }

    public static void deleteAllFromBasket(){
        basket = new ArrayList<>();
    }

    public static void addMail(String address, String subject, String message){
        mail.add(new MailItem(address, subject, message));
    }

    public static ArrayList<MailItem> getMail(){
        return mail;
    }

    public static void deleteMail(Integer position){
        mail.remove(position);
    }

    public static ArrayList<String> getBasketHistory(){
        return basketHistory;
    }

    public static void addBasketHistory(String text){
        basketHistory.add(text);
    }
}
