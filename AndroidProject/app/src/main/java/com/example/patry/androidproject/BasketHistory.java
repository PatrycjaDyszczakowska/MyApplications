package com.example.patry.androidproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.patry.androidproject.Data.DataTree;

import java.util.ArrayList;

public class BasketHistory extends Fragment {
    ListView listView;
    ArrayList<String> basketList;
    ArrayAdapter<String> adapter;
    public BasketHistory() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list1, container, false);
        listView = v.findViewById(R.id.list_view_lista1);
        basketList = DataTree.getBasketHistory();
        adapter = new ArrayAdapter<>(getActivity(),R.layout.basket_history_row,basketList);
        listView.setAdapter(adapter);
        return v;
    }
}