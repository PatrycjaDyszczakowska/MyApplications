package com.example.patry.androidproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;

public class Gallery extends Fragment {
    Integer category_details;
    Integer position_details;
    GridView gridView;
    ArrayAdapter<ImageView> adapter;
    public Gallery(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gallery, container, false);
        Bundle args = getArguments();
        gridView = v.findViewById(R.id.gallery);
        category_details = args.getInt("category_details");
        position_details = args.getInt("position_details");
        Log.d("Kategoria z widoku ",""+category_details);
        Log.d("Pozycja ",""+position_details);

        gridView.setAdapter(new ImageAdapter(v.getContext(), category_details,position_details));
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Picture picture = new Picture();
                Bundle args = new Bundle();
                args.putInt("category_gallery",category_details);
                args.putInt("position_gallery",position_details);
                picture.setArguments(args);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack("Zdjecie");
                transaction.replace(R.id.fragment_layout,picture, "Zdjecie");
                transaction.commitAllowingStateLoss();
            }
        });
        return v;
    }
}

