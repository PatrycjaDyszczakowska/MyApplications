package com.example.patry.androidproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;

import com.example.patry.androidproject.Data.DataTree;

public class AddMail extends Fragment {
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
    EditText mail_adres;
    EditText mail_temat;
    EditText mail_wiadomosc;
    Button wyslij;
    public AddMail() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mail, container, false);
        mail_adres = v.findViewById(R.id.mail_adres);
        mail_temat = v.findViewById(R.id.topic);
        mail_wiadomosc = v.findViewById(R.id.info);
        wyslij = v.findViewById(R.id.send);
        wyslij.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mail_adres.getText().toString().equals("") && !mail_temat.getText().toString().equals("") && !mail_wiadomosc.getText().toString().equals("")) {
                    view.startAnimation(buttonClick);
                    DataTree.addMail(mail_adres.getText().toString(), mail_temat.getText().toString(), mail_wiadomosc.getText().toString());
                    getFragmentManager().popBackStack();
                }
            }
        });
        return v;
    }
}