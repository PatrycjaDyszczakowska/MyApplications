package com.example.patrycja.praca_inzynierska;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.patrycja.praca_inzynierska.data.Data;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Klasa odpowiadające za wyświtlanie oraz dodawanie opinii
 */
public class List_of_reviews extends Fragment {
    /**
     * Zmiennie inicjujące widok, liste elementów, przyciski oraz wywołania tekstów.
     */
    View view;
    ListView list_rev;
    Button back, add_list;
    TextView PathRev, average;
    /**
     * Długość tablicy
     */
    Integer size;
    /**
     * Tablice string elementów
     */
    ArrayList<String> listUser, listProwadzących, listRev;
    /**
     * Tablica integer elementów
     */
    ArrayList<Integer> listRate;
    /**
     * Metoda tworząca widok fragmentu
     * @param inflater  zmienna odpowiadająca za wywołanie widoków w fragmencie
     * @param container widok rodzica do którefo fragment ma być przywiązany
     * @param savedInstanceState zapisuje stan fragmentu
     * @return View - Stworzony widok
     */
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final String url = this.getArguments().getString("URLSemester");
        final String path = this.getArguments().getString("Path");
        Log.i("URL SEND ",url);
        view = inflater.inflate(R.layout.list_of_reviews, container, false);
        list_rev = (ListView)view.findViewById(R.id.list_of_reviews);
        back = (Button)view.findViewById(R.id.back_rev);
        add_list = (Button)view.findViewById(R.id.add_rev);
        average = (TextView)view.findViewById(R.id.rate_view);
        PathRev = (TextView)view.findViewById(R.id.pathRev);
        PathRev.setText(path);
        JsonArray json = new JsonArray();
        Ion.getDefault(view.getContext()).configure().getResponseCache().setCaching(false);
        Ion.with(view.getContext())
                .load("GET",url)
                .setLogging("LOG", Log.VERBOSE)
                .setJsonArrayBody(json)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        //Log.i("List of review ", ""+result.get(0).getAsJsonObject().get("name").toString().replace("\"",""));
                        if (result != null) {
                            Integer  sum=0;
                            size = result.size();
                            listRate = new ArrayList<>();
                            listUser = new ArrayList<>();
                            listProwadzących = new ArrayList<>();
                            listRev = new ArrayList<>();
                            for (int i=0;i<result.size();i++) {
                                listRate.add(Integer.parseInt(result.get(i).getAsJsonObject().get("rate").toString().replace("\"", "")));
                                sum += listRate.get(i);
                                listUser.add(result.get(i).getAsJsonObject().get("nameUser").toString().replace("\"", ""));
                                listProwadzących.add(result.get(i).getAsJsonObject().get("name").toString().replace("\"", ""));
                                listRev.add(result.get(i).getAsJsonObject().get("review").toString().replace("\"", ""));
                            }
                            average.setText(getResources().getString(R.string.average)+" "+(sum/size));
                            list_rev.setAdapter(new CustomAdapter(view.getContext(), listRate, listUser, listProwadzących, listRev));
                        }
                        //Log.i("ArrayList ", listdata.toString());

                    }
                });
        /**
         * Słuchacz odpowiadający za przyciśnięcie przycisku powrotu
         */
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle send = new Bundle();
                if (url != null) {
                    String tab[]=url.split("/");
                    send.putString("URLCourse", tab[0]+"//"+tab[2]+"/"+tab[3]+"/"+tab[4]+"/"+tab[5]+"/"+tab[6]+"/"+tab[7]+"/"+tab[8]);
                }
                if(path != null){
                    String tab1[]=path.split("<");
                    send.putString("Path", tab1[0]+"<"+tab1[1]+"<"+tab1[2]+"<"+tab1[3]);
                }
                Semester semester = new Semester();
                semester.setArguments(send);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.Body, semester);
                fragmentTransaction.commit();
            }
        });

        if(!Data.adminuser.equals("guest")){
            add_list.setVisibility(View.VISIBLE);
            /**
             * Słuchacz odpowiadający za przyciśnięcie przycisku przez
             * zalogowanego uzytkownika do dodawania nowej opinii
             * Wywołany jest Dialog gdzie uzytkownik wpisuje nową opinie
             */
            add_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                    final View dialogView = inflater.inflate(R.layout.add_reviews,null);
                    dialog.setView(dialogView);
                    dialogView.findViewById(R.id.spinner).setVisibility(View.INVISIBLE);
                    final RatingBar ratingBar = (RatingBar)dialogView.findViewById(R.id.rarigBar);
                    final EditText nameEdit = (EditText)dialogView.findViewById(R.id.name);
                    final EditText review = (EditText)dialogView.findViewById(R.id.review);
                    dialog.setPositiveButton(getResources().getString(R.string.add), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(Data.searchElement(Data.nameUser, listUser)){
                                return;
                            }
                            JsonObject json = new JsonObject();
                            Ion.getDefault(dialogView.getContext()).configure().getResponseCache().setCaching(false);
                            Ion.with(dialogView.getContext())
                                    .load("PUT",url+"?id="+size+"&rate="+Math.round(ratingBar.getRating())+"&nameUser="+Data.nameUser+"&name="+Data.replace(nameEdit.getText().toString())+"&review="+Data.replace(review.getText().toString()))
                                    .setLogging("LOG",Log.VERBOSE)
                                    .setJsonObjectBody(json)
                                    .asJsonObject();
                            Bundle send = new Bundle();
                            send.putString("URLSemester", url);
                            send.putString("Path", path);
                            List_of_reviews list_of_reviews = new List_of_reviews();
                            list_of_reviews.setArguments(send);
                            FragmentManager manager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = manager.beginTransaction();
                            fragmentTransaction.replace(R.id.Body, list_of_reviews);
                            fragmentTransaction.commit();
                        }
                    });

                    dialog.setNegativeButton(getResources().getString(R.string.back), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog alert = dialog.create();
                    alert.show();
                }
            });
        }else {
            add_list.setVisibility(View.INVISIBLE);
        }
        return view;
    }
}
