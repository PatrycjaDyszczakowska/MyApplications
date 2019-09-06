package com.example.patry.androidproject;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.patry.androidproject.Data.DataTree;
import com.example.patry.androidproject.Data.SearchItem;
import com.example.patry.androidproject.Data.SearchTable;

import java.util.ArrayList;


public class SearchList extends Fragment {
    ListView list;
    private static SearchAdapter adapter;
    ArrayList<SearchItem> searchItems;
    Integer category;

    public SearchList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.list1, container, false);
        searchItems = new ArrayList<>();
        list = v.findViewById(R.id.list_view_lista1);
        Bundle args = getArguments();
        category = args.getInt("category");
        Integer screen = args.getInt("Screen");
        Double minValue = args.getDouble("MinValue");
        Double maxValue = args.getDouble("MaxValue");
        Log.d("MinValue",""+minValue);
        Log.d("MaxValue",""+maxValue);
        if(category<3&&screen!=-1){
            Log.d("Screen",""+screen+ " Laptop "+ SearchTable.screenTable.get(category)[screen]);
            Log.d("Screen2", SearchTable.screenTableCal.get(category)[screen][0]+">laptop<"+SearchTable.screenTableCal.get(category)[screen][1]);
            for(int i = 0;i<DataTree.getCategoryItem(category).size();i++){
                Log.d("Screen2", SearchTable.screenTableCal.get(category)[screen][0]+"<="+DataTree.getCategoryItem(category).get(i).getScreen()+"<="+SearchTable.screenTableCal.get(category)[screen][1]);
                if((SearchTable.screenTableCal.get(category)[screen][0]<=DataTree.getCategoryItem(category).get(i).getScreen())
                        &&(DataTree.getCategoryItem(category).get(i).getScreen()<=SearchTable.screenTableCal.get(category)[screen][1])){
                    searchItems.add(new SearchItem(category,i,DataTree.getCategoryItem(category).get(i).getName(),
                            DataTree.getCategoryItem(category).get(i).getPrice(),DataTree.getCategoryItem(category).get(i).getInfo_short(),
                            DataTree.getCategoryItem(category).get(i).getInfo_long(),DataTree.getCategoryItem(category).get(i).getImage_id(),
                            DataTree.getCategoryItem(category).get(i).getFavorite(),DataTree.getCategoryItem(category).get(i).getOperating_system()));
                }
            }
        }else{
            for(int i = 0;i<DataTree.getCategoryItem(category).size();i++){
                searchItems.add(new SearchItem(category,i,DataTree.getCategoryItem(category).get(i).getName(),
                        DataTree.getCategoryItem(category).get(i).getPrice(),DataTree.getCategoryItem(category).get(i).getInfo_short(),
                        DataTree.getCategoryItem(category).get(i).getInfo_long(),DataTree.getCategoryItem(category).get(i).getImage_id(),
                        DataTree.getCategoryItem(category).get(i).getFavorite(),DataTree.getCategoryItem(category).get(i).getOperating_system()));
            }
        }
        for(int j=0;j<searchItems.size();j++){
            Double price = searchItems.get(j).getPrice();
            if(price>maxValue||price<minValue){
                searchItems.remove(j);
                j--;
            }
        }
        adapter = new SearchAdapter(searchItems,v.getContext());

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Detailed_view detailed_view = new Detailed_view();
                Bundle args = new Bundle();
                args.putInt("category_list",searchItems.get(position).getCategory());
                args.putInt("position",searchItems.get(position).getPosition());
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

