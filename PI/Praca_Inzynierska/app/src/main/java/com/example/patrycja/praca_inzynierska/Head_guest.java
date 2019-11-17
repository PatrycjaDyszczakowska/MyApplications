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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.patrycja.praca_inzynierska.data.Data;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Klasa odpowiadające za Zalogowanie uzytkownika lub rejestracje nowego użytkownika
 */
public class Head_guest extends Fragment{
    /**
     * Zmiennie inicjujące widok oraz dwa przyciski
     */
    View view;
    Button sign_in;
    Button register;
    /**
     * Metoda tworząca widok fragmentu
     * @param inflater  zmienna odpowiadająca za wywołanie widoków w fragmencie
     * @param container widok rodzica do którefo fragment ma być przywiązany
     * @param savedInstanceState zapisuje stan fragmentu
     * @return View - Stworzony widok
     */
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.head_guest, container, false);
        sign_in = view.findViewById(R.id.Sign_in);
        register = view.findViewById(R.id.Register);
        /**
         * Słuchacz odpowiadający za przyciśnięcie przycisku zaloguj się
         * Wywołany jest Dialog gdzie uzytkownik wpisuje swoje dane
         */
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                final View dialogView = inflater.inflate(R.layout.sign_in, null);
                builder.setView(dialogView);
                final EditText loginEdit = (EditText)dialogView.findViewById(R.id.Login);
                final EditText passwordEdit = (EditText)dialogView.findViewById(R.id.Password);

                builder.setPositiveButton(R.string.login, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final String login = loginEdit.getText().toString();
                        final String password = passwordEdit.getText().toString();
                        JsonObject json = new JsonObject();
                        if(getResources().getString(R.string.login).equals(login)||login.equals("")||password.equals("")){
                            return;
                        }
                        Ion.getDefault(dialogView.getContext()).configure().getResponseCache().setCaching(false);
                        Ion.with(dialogView.getContext())
                                .load("GET", "http://10.0.2.2:4567/server/user/"+login)
                                .setLogging("LOGLOGIN", Log.VERBOSE)
                                .setJsonObjectBody(json)
                                .asJsonObject()
                                .setCallback(new FutureCallback<JsonObject>() {
                                    @Override
                                    public void onCompleted(Exception e, final JsonObject result) {
                                        if(e != null){
                                            return;
                                        }
                                        if(!Data.cryptPassword(password).equals(result.get("password").toString().replace("\"",""))){
                                            return;
                                        }
                                        if(!result.get("acc").getAsBoolean()) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(dialogView.getContext());
                                            final View dialogView = inflater.inflate(R.layout.activate, null);
                                            builder.setView(dialogView);
                                            final EditText acc = (EditText)dialogView.findViewById(R.id.acc);
                                            builder.setPositiveButton(R.string.acc_button, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    if(!result.get("numberAcc").toString().replace("\"", "").equals(acc.getText().toString())) {
                                                        final AlertDialog.Builder dialogError = new AlertDialog.Builder(dialogView.getContext());
                                                        dialogError.setMessage("Nieprawidłowy Klucz Aktywacyjny!!!");
                                                        dialogError.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                dialogInterface.cancel();
                                                            }
                                                        });
                                                        AlertDialog alertError = dialogError.create();
                                                        alertError.show();
                                                        return;
                                                    }
                                                    JsonObject json = new JsonObject();
                                                    Ion.getDefault(dialogView.getContext()).configure().getResponseCache().setCaching(false);
                                                    Ion.with(dialogView.getContext())
                                                            .load("PUT", "http://10.0.2.2:4567/server/user/"+login+"?acc=true")
                                                            .setLogging("LOG", Log.VERBOSE)
                                                            .setJsonObjectBody(json)
                                                            .asJsonObject()
                                                            .setCallback(new FutureCallback<JsonObject>() {
                                                                @Override
                                                                public void onCompleted(Exception e, JsonObject result) {
                                                                    Log.i("Login ", "sukces");
                                                                    Data.nameUser = login;
                                                                    Data.adminuser = result.get("adminuser").toString().replace("\"", "");
                                                                    FragmentManager manager = getFragmentManager();
                                                                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                                                                    fragmentTransaction.replace(R.id.Head, new Head_user());
                                                                    fragmentTransaction.commit();
                                                                }
                                                            });
                                                }
                                            });

                                            builder.setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.cancel();
                                                }
                                            });

                                            AlertDialog alert = builder.create();
                                            alert.show();;
                                        }else {
                                            Log.i("Login ", "sukces");
                                            Data.nameUser = login;
                                            Data.adminuser = result.get("adminuser").toString().replace("\"", "");
                                            FragmentManager manager = getFragmentManager();
                                            FragmentTransaction fragmentTransaction = manager.beginTransaction();
                                            fragmentTransaction.replace(R.id.Head, new Head_user());
                                            fragmentTransaction.commit();
                                        }
                                        FragmentManager manager1 = getFragmentManager();
                                        FragmentTransaction fragmentTransaction1 = manager1.beginTransaction();
                                        fragmentTransaction1.replace(R.id.Body, new Departments());
                                        fragmentTransaction1.commit();
                                    }
                                });
                    }
                });

                builder.setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        /**
         * Słuchacz odpowiadający za przyciśnięcie przycisku zarejestruj się
         * Wywołany jest Dialog gdzie uzytkownik wpisuje swoje dane
         */
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                final View dialogView = inflater.inflate(R.layout.register, null);
                builder.setView(dialogView);
                final EditText login = (EditText)dialogView.findViewById(R.id.newLogin);
                final EditText password = (EditText)dialogView.findViewById(R.id.newPassword);
                final EditText reaped = (EditText)dialogView.findViewById(R.id.newRepeatPassword);
                builder.setPositiveButton(R.string.register, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final AlertDialog.Builder dialog = new AlertDialog.Builder(dialogView.getContext());
                        final String passwordText = password.getText().toString();
                        String reapedText = reaped.getText().toString();
                        final String loginText = login.getText().toString();
                        if(!passwordText.equals(reapedText)||passwordText.equals("")||loginText.equals("")){
                            final AlertDialog.Builder dialogError = new AlertDialog.Builder(dialogView.getContext());
                            dialogError.setMessage("Problem z hasłem/Loginem!!!");
                            dialogError.setCancelable(true);

                            dialogError.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            AlertDialog alertError = dialogError.create();
                            alertError.show();
                            return;
                        }
                        final JsonObject json = new JsonObject();
                        Ion.getDefault(dialogView.getContext()).configure().getResponseCache().setCaching(false);
                        Ion.with(dialogView.getContext())
                                .load("GET", "http://10.0.2.2:4567/server/user/"+loginText)
                                .setLogging("LOG", Log.VERBOSE)
                                .setJsonObjectBody(json)
                                .asJsonObject()
                                .setCallback(new FutureCallback<JsonObject>() {
                                                 @Override
                                                 public void onCompleted(Exception e, final JsonObject result) {
                                                    if(e != null){
                                                        Ion.with(dialogView.getContext())
                                                                .load("POST","http://10.0.2.2:4567/server/user?name="+loginText+"&password="+Data.replace(passwordText))
                                                                .setLogging("LOG",Log.VERBOSE)
                                                                .setJsonObjectBody(json)
                                                                .asJsonObject();
                                                    }
                                                 }
                                             });
                    }
                });

                builder.setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();;
            }
        });
        return view;
    }
}
