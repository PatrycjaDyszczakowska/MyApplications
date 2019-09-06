package com.example.patry.androidproject;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.patry.androidproject.Data.DataTree;
import com.example.patry.androidproject.Data.MailItem;

import java.util.ArrayList;

public class Mail extends Fragment {
    ListView listView;
    ImageButton imageButton;
    MailAdapter mailAdapter;
    ArrayList<MailItem> mailItems;

    public Mail(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mail_view, container, false);
        listView = v.findViewById(R.id.mail_list);
        imageButton = v.findViewById(R.id.mail_add);
        mailItems = DataTree.getMail();
        mailAdapter = new MailAdapter(mailItems,v.getContext());
        listView.setAdapter(mailAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                DataTree.deleteMail(position);
                mailItems.remove(position);
                Mail mail = new Mail();
                FragmentTransaction transactionMail = getActivity().getSupportFragmentManager().beginTransaction();
                transactionMail.addToBackStack("Mail");
                transactionMail.replace(R.id.fragment_layout,mail, "Mail");
                transactionMail.commitAllowingStateLoss();
                return false;
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddMail addMail = new AddMail();
                FragmentTransaction transactionMail = getActivity().getSupportFragmentManager().beginTransaction();
                transactionMail.addToBackStack("AddMail");
                transactionMail.replace(R.id.fragment_layout, addMail, "AddMail");
                transactionMail.commitAllowingStateLoss();
            }
        });
        return v;
    }
}
