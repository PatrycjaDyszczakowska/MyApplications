package com.example.patry.androidproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.patry.androidproject.Data.CategoryItem;
import com.example.patry.androidproject.Data.DataTree;

import java.util.ArrayList;

public class List2 extends Fragment {
    GridView gridView;
    GridViewAdapter gridViewAdapter;
    ArrayList<CategoryItem> gridDataModel;
    Integer category;
    public List2(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list2, container, false);
        gridView = v.findViewById(R.id.grid_list);
        gridDataModel= new ArrayList<>();
        Bundle args = getArguments();
        category = args.getInt("category");
        gridDataModel = DataTree.getCategoryItem(category);
        gridViewAdapter= new GridViewAdapter(gridDataModel,v.getContext());

        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Detailed_view detailed_view = new Detailed_view();
                Bundle args = new Bundle();
                args.putInt("category_list",category);
                args.putInt("position",position);
                detailed_view.setArguments(args);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack("Widok Szczegółowy");
                transaction.replace(R.id.fragment_layout,detailed_view, "Widok Szczegółowy");
                transaction.commitAllowingStateLoss();
            }
        });
        return v;
    }
}