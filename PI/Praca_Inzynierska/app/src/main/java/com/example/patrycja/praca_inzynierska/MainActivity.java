package com.example.patrycja.praca_inzynierska;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * @author Patrycja Dyszczakowska
 * Nr Indeksu: 208795
 * @version 1.0
 * Aplikacja mobilna do przeglądania i oceny kursów - Klient
 *
 *Główna klasa odpowiadająca za inicjacje 2 fragmentów
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * Fragment odpowiadający za zainicjowanie klasy Head_guest
         */
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction  = fm.beginTransaction();
        fragmentTransaction.replace(R.id.Head, new Head_guest());
        fragmentTransaction.commit();

        /**
         * Fragment odpowiadający za zainicjowanie klasy Departaments
         */
        FragmentManager fm1 = getFragmentManager();
        FragmentTransaction fragmentTransaction1 = fm1.beginTransaction();
        fragmentTransaction1.replace(R.id.Body, new Departments());
        fragmentTransaction1.commit();
    }
}
