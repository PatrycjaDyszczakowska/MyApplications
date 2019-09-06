package com.example.patry.androidproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patry.androidproject.Data.DataTree;
import com.example.patry.androidproject.Data.FavoriteItem;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    RecyclerView recyclerView, recyclerView2;
    ArrayList<FavoriteItem> categoryItems, categotyItem2;
    RecycleViewAdapter adapter, adapter2;
    public MainFragment(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.main_fragment, container, false);
        recyclerView = v.findViewById(R.id.horizontal_list1);
        recyclerView2 = v.findViewById(R.id.horizontal_list2);
        recyclerView.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);
        categoryItems = new ArrayList<>();
        categotyItem2 = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        for(int i = 0; i<DataTree.getCategory().size()-1;i++){
            int j = DataTree.getCategoryItem(i).size()-1;
            Log.d("Kategorie", ""+j);
            categoryItems.add(new FavoriteItem(i,j,DataTree.getCategoryItem(i).get(j).getName(),
                    DataTree.getCategoryItem(i).get(j).getPrice(),DataTree.getCategoryItem(i).get(j).getInfo_short(),
                    DataTree.getCategoryItem(i).get(j).getInfo_long(),DataTree.getCategoryItem(i).get(j).getImage_id(),
                    DataTree.getCategoryItem(i).get(j).getFavorite()));
            int k = 0;
            categotyItem2.add(new FavoriteItem(i,k,DataTree.getCategoryItem(i).get(k).getName(),
                    DataTree.getCategoryItem(i).get(k).getPrice(),DataTree.getCategoryItem(i).get(k).getInfo_short(),
                    DataTree.getCategoryItem(i).get(k).getInfo_long(),DataTree.getCategoryItem(i).get(k).getImage_id(),
                    DataTree.getCategoryItem(i).get(k).getFavorite()));
        }
        if(categoryItems.size()>0&recyclerView!=null){
            adapter = new RecycleViewAdapter(categoryItems,v.getContext());
            recyclerView.setAdapter(adapter);
        }
        if(categotyItem2.size()>0&recyclerView!=null){
            adapter2 = new RecycleViewAdapter(categotyItem2,v.getContext());
            recyclerView2.setAdapter(adapter2);
        }
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        return v;
    }
}