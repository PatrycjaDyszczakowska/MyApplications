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

public class Picture extends Fragment {
    RecyclerView recyclerView;
    Integer category_gallery;
    Integer position_gallery;
    RecycleViewPictureAdapter adapter;
    public Picture(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.picture, container, false);
        Bundle args = getArguments();
        category_gallery = args.getInt("category_gallery");
        position_gallery = args.getInt("position_gallery");
        Log.d("Kategoria z galerii ",""+category_gallery);
        Log.d("Pozycja ",""+position_gallery);
        recyclerView = v.findViewById(R.id.horizontal_picture);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        int[] images = new int[8];
        for(int i=0;i<images.length;i++){
            images[i] = DataTree.getCategoryItem(category_gallery).get(position_gallery).getImage_id();
        }
        recyclerView.setAdapter(new RecycleViewPictureAdapter(images));
        return  v;
    }
}
