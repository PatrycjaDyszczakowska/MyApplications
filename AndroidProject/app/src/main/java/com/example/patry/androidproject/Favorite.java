package com.example.patry.androidproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.patry.androidproject.Data.DataTree;
import com.example.patry.androidproject.Data.FavoriteItem;

import java.util.ArrayList;

public class Favorite extends Fragment {
    ListView list;
    RelativeLayout relativeLayout;
    FavoriteAdapter adapter;
    ArrayList<FavoriteItem> favoriteItems;
    public Favorite(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.favorite, container, false);
        list = v.findViewById(R.id.list_view_favorite);
        relativeLayout = v.findViewById(R.id.relative_view);
        list.setEmptyView(relativeLayout);
        favoriteItems= new ArrayList<>();
        for(int i=0;i<DataTree.getCategory().size();i++){
            for(int j=0;j<DataTree.getCategoryItem(i).size();j++){
                if(DataTree.getCategoryItem(i).get(j).getFavorite()){
                    favoriteItems.add(new FavoriteItem(i,j,DataTree.getCategoryItem(i).get(j).getName(),
                            DataTree.getCategoryItem(i).get(j).getPrice(),DataTree.getCategoryItem(i).get(j).getInfo_short(),
                            DataTree.getCategoryItem(i).get(j).getInfo_long(),DataTree.getCategoryItem(i).get(j).getImage_id(),
                            DataTree.getCategoryItem(i).get(j).getFavorite()));
                }
            }
        }
        adapter = new FavoriteAdapter(favoriteItems,v.getContext());

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Detailed_view detailed_view = new Detailed_view();
                Bundle args = new Bundle();
                args.putInt("category_list",favoriteItems.get(position).getCategory());
                args.putInt("position",favoriteItems.get(position).getPosition());
                detailed_view.setArguments(args);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack("Widok Szczegółowy");
                transaction.replace(R.id.fragment_layout,detailed_view, "Widok Szczegółowy");
                transaction.commitAllowingStateLoss();


                /*Snackbar.make(view, favoriteItems.get(position).getName()+"\nCena: "+favoriteItems.get(position).getPrice()+"\n"+favoriteItems.get(position).getInfo_short(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();*/
            }
        });
        return v;
    }
}
