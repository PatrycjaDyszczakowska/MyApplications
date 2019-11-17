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
import android.widget.TextView;

import com.example.patrycja.praca_inzynierska.data.Data;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Klasa odpowiadające za wyświtlanie oraz dodawanie kierunków
 */
public class Specialization extends Fragment {
    /**
     * Zmiennie inicjujące widok, liste elementów, przyciski oraz wywołanie tekstu.
     */
    View view;
    ListView list_spec;
    Button back, add_spec;
    TextView PathSpec;
    /**
     * Adapter odpowaidający za wyświtlenie listy elementów
     */
    ArrayAdapter<String> adapter;
    /**
     * Długość tablicy
     */
    Integer size;
    /**
     * Tablica string elementów
     */
    ArrayList<String> listdata;
    /**
     * Metoda tworząca widok fragmentu
     * @param inflater  zmienna odpowiadająca za wywołanie widoków w fragmencie
     * @param container widok rodzica do którefo fragment ma być przywiązany
     * @param savedInstanceState zapisuje stan fragmentu
     * @return View - Stworzony widok
     */
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final String url = this.getArguments().getString("URLDep");
        final String path = this.getArguments().getString("Path");
        Log.i("URL SEND ",url);
        view = inflater.inflate(R.layout.specialization, container, false);
        list_spec = (ListView)view.findViewById(R.id.list_specializacions);
        back = (Button)view.findViewById(R.id.back_spec);
        add_spec = (Button)view.findViewById(R.id.add_spec);
        PathSpec = (TextView)view.findViewById(R.id.pathSpec);
        PathSpec.setText(path);
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
                        //Log.i("Specialization ", ""+result.get(0).getAsJsonObject().get("name").toString().replace("\"",""));
                        listdata = new ArrayList<String>();
                        if (result != null) {
                            for (int i=0;i<result.size();i++){
                                listdata.add(result.get(i).getAsJsonObject().get("name").toString().replace("\"",""));
                            }
                            size = result.size();
                        }
                        //Log.i("ArrayList ", listdata.toString());
                        adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_expandable_list_item_1, listdata);
                        list_spec.setAdapter(adapter);
                        /**
                         * Słuchacz odpowiadający za przyciśnięcie danego elementu na liście
                         */
                        list_spec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                Bundle send = new Bundle();
                                send.putString("URLSpec", url + "/" + position);
                                send.putString("Path", path+"<"+listdata.get(position));
                                Degree degree = new Degree();
                                degree.setArguments(send);
                                FragmentManager manager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                                fragmentTransaction.replace(R.id.Body, degree);
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
                FragmentManager manager = getFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.Body, new Departments());
                fragmentTransaction.commit();
            }
        });

        if(Data.adminuser.equals("Admin")) {
            add_spec.setVisibility(View.VISIBLE);
            /**
             * Słuchacz odpowiadający za przyciśnięcie przycisku przez
             * administratora do dodawania nowego kierunku
             * Wywołany jest Dialog gdzie uzytkownik wpisuje nowy kierunek
             */
            add_spec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                    final View dialogView = inflater.inflate(R.layout.add_admin,null);
                    dialog.setView(dialogView);
                    final EditText addrow = (EditText)dialogView.findViewById(R.id.add_row);
                    dialog.setPositiveButton(getResources().getString(R.string.add), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String name = addrow.getText().toString();
                            if(Data.searchElement(name, listdata)){
                                return;
                            }
                            JsonObject json = new JsonObject();
                            Ion.getDefault(dialogView.getContext()).configure().getResponseCache().setCaching(false);
                            Ion.with(dialogView.getContext())
                                    .load("PUT",url+"?id="+size+"&name="+Data.replace(name))
                                    .setLogging("LOG",Log.VERBOSE)
                                    .setJsonObjectBody(json)
                                    .asJsonObject()
                                    .setCallback(new FutureCallback<JsonObject>() {
                                        @Override
                                        public void onCompleted(Exception e, JsonObject result) {
                                            Bundle send = new Bundle();
                                            send.putString("URLDep", url);
                                            send.putString("Path", path);
                                            Specialization specialization = new Specialization();
                                            specialization.setArguments(send);
                                            FragmentManager manager = getFragmentManager();
                                            FragmentTransaction fragmentTransaction = manager.beginTransaction();
                                            fragmentTransaction.replace(R.id.Body, specialization);
                                            fragmentTransaction.commit();
                                        }
                                    });
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
            add_spec.setVisibility(View.INVISIBLE);
        }
        return view;
    }
}
