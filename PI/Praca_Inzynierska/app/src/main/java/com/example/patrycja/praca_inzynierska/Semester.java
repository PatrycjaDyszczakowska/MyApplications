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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.patrycja.praca_inzynierska.data.Data;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Klasa odpowiadające za wyświtlanie oraz dodawanie semestrów
 */
public class Semester extends Fragment {
    /**
     * Zmiennie inicjujące widok, liste elementów, przyciski oraz wywołanie tekstu.
     */
    View view;
    ListView list_sem;
    Button back, add_semester;
    TextView PathSem;
    /**
     * Adapter odpowaidający za wyświtlenie listy elementów
     */
    ArrayAdapter<String> adapter;
    /**
     * Tablica string elementów
     */
    ArrayList<String> listdata;
    /**
     * Długość tablicy
     */
    Integer size;
    /**
     * Metoda tworząca widok fragmentu
     * @param inflater  zmienna odpowiadająca za wywołanie widoków w fragmencie
     * @param container widok rodzica do którefo fragment ma być przywiązany
     * @param savedInstanceState zapisuje stan fragmentu
     * @return View - Stworzony widok
     */
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final String url = this.getArguments().getString("URLCourse");
        final String path = this.getArguments().getString("Path");
        Log.i("URL SEND ",url);
        view = inflater.inflate(R.layout.semester, container, false);
        list_sem = (ListView)view.findViewById(R.id.list_semester);
        back = (Button)view.findViewById(R.id.back_sem);
        add_semester = (Button) view.findViewById(R.id.add_semester);
        PathSem = (TextView)view.findViewById(R.id.pathSemester);
        PathSem.setText(path);
        JsonArray json = new JsonArray();
        Ion.getDefault(view.getContext()).configure().getResponseCache().setCaching(false);
        Ion.with(view.getContext())
                .load("GET",url)
                .setLogging("LOG",Log.VERBOSE)
                .setJsonArrayBody(json)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        //Log.i("Semester ", ""+result.get(0).getAsJsonObject().get("name").toString().replace("\"",""));
                        listdata = new ArrayList<String>();
                        if (result != null) {
                            for (int i=0;i<result.size();i++){
                                listdata.add(result.get(i).getAsJsonObject().get("name").toString().replace("\"",""));
                            }
                            size = result.size();
                        }
                        //Log.i("ArrayList ", listdata.toString());
                        adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_expandable_list_item_1, listdata);
                        list_sem.setAdapter(adapter);
                        /**
                         * Słuchacz odpowiadający za przyciśnięcie danego elementu na liście
                         */
                        list_sem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                Bundle send = new Bundle();
                                send.putString("URLSemester", url + "/" + position);
                                send.putString("Path", path+"<"+listdata.get(position));
                                List_of_reviews list_of_reviews = new List_of_reviews();
                                list_of_reviews.setArguments(send);
                                FragmentManager manager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                                fragmentTransaction.replace(R.id.Body, list_of_reviews);
                                fragmentTransaction.commit();
                            }
                        });
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
                    send.putString("URLDeg", tab[0]+"//"+tab[2]+"/"+tab[3]+"/"+tab[4]+"/"+tab[5]+"/"+tab[6]+"/"+tab[7]);
                }
                if(path != null){
                    String tab1[]=path.split("<");
                    send.putString("Path", tab1[0]+"<"+tab1[1]+"<"+tab1[2]);
                }
                Course course = new Course();
                course.setArguments(send);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.Body, course);
                fragmentTransaction.commit();
            }
        });

        if(!Data.adminuser.equals("guest")){
            add_semester.setVisibility(View.VISIBLE);
            /**
             * Słuchacz odpowiadający za przyciśnięcie przycisku przez
             * zalogowanych użytkowników do dodawania nowego semestru oraz opinii
             * Wywołany jest Dialog gdzie uzytkownik wpisuje dane do dodania nowego semestry oraz opinii
             */
            add_semester.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GregorianCalendar calendar = new GregorianCalendar();
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                    final View dialogView = inflater.inflate(R.layout.add_reviews,null);
                    final Spinner spinner = (Spinner)dialogView.findViewById(R.id.spinner);
                    final RatingBar ratingBar = (RatingBar)dialogView.findViewById(R.id.rarigBar);
                    final EditText nameEdit = (EditText)dialogView.findViewById(R.id.name);
                    final EditText reviewEdit = (EditText)dialogView.findViewById(R.id.review);
                    List<String> list = new ArrayList<>();
                    for(int i = 0; i<5 ;i++){
                        list.add((calendar.get(Calendar.YEAR)-i)+"/"+(calendar.get(Calendar.YEAR)+(1-i))+" Zimowy");
                        list.add((calendar.get(Calendar.YEAR)-i)+"/"+(calendar.get(Calendar.YEAR)+(1-i))+" Letni");
                    }
                    ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_item,list);
                    adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapterSpinner);
                    calendar.get(Calendar.YEAR);
                    dialog.setView(dialogView);
                    dialog.setPositiveButton(getResources().getString(R.string.add), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            final String name = nameEdit.getText().toString();
                            final String review = reviewEdit.getText().toString();
                            if(review.equals("")||name.equals("")){
                                return;
                            }
                            final Integer rate = Math.round(ratingBar.getRating());
                            String semester = spinner.getSelectedItem().toString();
                            //System.out.println("name: "+Data.replace(name)+"\nreview "+Data.replace(review)+"\n rate "+rate+"\nsemester "+Data.replace(semester)+"\nnameUser "+Data.nameUser);
                            final JsonObject json = new JsonObject();
                            final Integer id = search(semester);
                            if(id==size) {
                                Ion.getDefault(dialogView.getContext()).configure().getResponseCache().setCaching(false);
                                Ion.with(dialogView.getContext())
                                        .load("PUT", url + "?id=" + id + "&name=" + Data.replace(semester))
                                        .setLogging("LOG", Log.VERBOSE)
                                        .setJsonObjectBody(json)
                                        .asJsonObject()
                                        .setCallback(new FutureCallback<JsonObject>() {
                                            @Override
                                            public void onCompleted(Exception e, JsonObject result) {
                                                if(e==null) {
                                                    Ion.with(dialogView.getContext())
                                                            .load("PUT", url + "/" + size + "?id=" + 0 + "&rate=" + rate + "&nameUser=" + Data.nameUser + "&name=" + Data.replace(name) + "&review=" + Data.replace(review))
                                                            .setLogging("LOG", Log.VERBOSE)
                                                            .setJsonObjectBody(json)
                                                            .asJsonObject();
                                                    Bundle send = new Bundle();
                                                    send.putString("URLCourse", url);
                                                    send.putString("Path", path);
                                                    Semester semester1 = new Semester();
                                                    semester1.setArguments(send);
                                                    FragmentManager manager = getFragmentManager();
                                                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                                                    fragmentTransaction.replace(R.id.Body, semester1);
                                                    fragmentTransaction.commit();
                                                }
                                            }
                                        });
                            }else {
                                JsonArray json1 = new JsonArray();
                                Ion.with(dialogView.getContext())
                                        .load("GET", url+"/"+id)
                                        .setLogging("LOG", Log.VERBOSE)
                                        .setJsonArrayBody(json1)
                                        .asJsonArray()
                                        .setCallback(new FutureCallback<JsonArray>() {
                                            @Override
                                            public void onCompleted(Exception e, JsonArray result) {
                                                //Log.i("List of review ", ""+result.get(0).getAsJsonObject().get("name").toString().replace("\"",""));
                                                if (result != null) {
                                                    Ion.with(dialogView.getContext())
                                                            .load("PUT", url + "/" + id + "?id=" + result.size() + "&rate=" + rate + "&nameUser=" + Data.nameUser + "&name=" + Data.replace(name) + "&review=" + Data.replace(review))
                                                            .setLogging("LOG", Log.VERBOSE)
                                                            .setJsonObjectBody(json)
                                                            .asJsonObject();
                                                }
                                            }
                                        });
                            }
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

        }else{
            add_semester.setVisibility(View.INVISIBLE);
        }
        return view;
    }

    /**
     * Metoda szukająca identyfikator isniejącego semestru
     * @param name nazwa semestru
     * @return identyfikato semestru jak isnieje jak nie to długość listy semestrów
     */
    private Integer search(String name){
        for(int i=0;i<size;i++){
            if(name.equals(listdata.get(i))){
                return i;
            }
        }
        return size;
    }
}