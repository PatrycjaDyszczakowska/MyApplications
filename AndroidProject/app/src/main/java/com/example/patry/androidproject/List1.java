package com.example.patry.androidproject;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.patry.androidproject.Data.CategoryItem;
import com.example.patry.androidproject.Data.DataTree;

import java.util.ArrayList;


public class List1 extends Fragment {
    ListView list;
    private static List1ViewAdapter adapter;
    ArrayList<CategoryItem> categoryItems;
    Integer category;
    public List1(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.list1, container, false);
        list = v.findViewById(R.id.list_view_lista1);
        Bundle args = getArguments();
        category = args.getInt("category");
        categoryItems = DataTree.getCategoryItem(category);
        adapter = new List1ViewAdapter(categoryItems,v.getContext());

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

                CategoryItem dataModel= categoryItems.get(position);

                Snackbar.make(view, dataModel.getName()+"\nCena: "+dataModel.getPrice()+"\n"+dataModel.getInfo_short(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
        return v;
    }
}

