package com.example.patrycja.praca_inzynierska;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.patrycja.praca_inzynierska.data.Data;

/**
 * Klasa odpowiadająca za wylogowanie uzytkownika
 */
public class Head_user extends Fragment {
    /**
     * Zmiennie inicjujące widok, wiświtlanie tekstu oraz przycisk
     */
    View view;
    TextView user;
    Button log_out;
    /**
     * Metoda tworząca widok fragmentu
     * @param inflater  zmienna odpowiadająca za wywołanie widoków w fragmencie
     * @param container widok rodzica do którefo fragment ma być przywiązany
     * @param savedInstanceState zapisuje stan fragmentu
     * @return View - Stworzony widok
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.head_user, container, false);
        user = (TextView)view.findViewById(R.id.User_id);
        log_out = (Button)view.findViewById(R.id.Log_out);
        user.setText(Data.nameUser);
        /**
         * Słuchacz odpowiadający za przyciśnięcie przycisku wyloguj się
         */
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data.nameUser = "";
                Data.adminuser = "guest";

                FragmentManager manager = getFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.Head, new Head_guest());
                fragmentTransaction.commit();

                FragmentManager manager1 = getFragmentManager();
                FragmentTransaction fragmentTransaction1 = manager1.beginTransaction();
                fragmentTransaction1.replace(R.id.Body, new Departments());
                fragmentTransaction1.commit();
            }
        });
        return view;
    }
}
