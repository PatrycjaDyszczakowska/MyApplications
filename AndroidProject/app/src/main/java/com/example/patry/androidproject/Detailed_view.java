package com.example.patry.androidproject;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patry.androidproject.Data.DataTree;

public class Detailed_view extends Fragment implements  View.OnClickListener{
    public Detailed_view(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
    ImageView favorite, image;
    TextView name, price, info;
    String getname, getinfo;
    Integer getimage, position, category_list;
    Double getprice;
    Boolean getfavorite;
    Button add_basket;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.detailed_view, container, false);
        favorite = v.findViewById(R.id.detailed_view_favorite);
        image = v.findViewById(R.id.detailed_view_image);
        name = v.findViewById(R.id.detailed_view_name);
        price = v.findViewById(R.id.detailed_view_price);
        info = v.findViewById(R.id.detailed_view_info);
        add_basket = v.findViewById(R.id.add_basket);
        Bundle args = getArguments();
        category_list = args.getInt("category_list");
        position = args.getInt("position");
        Log.d("Kategoria z listy: ",""+category_list);
        Log.d("Pozycja: ",""+position);
        getname = DataTree.getCategoryItem(category_list).get(position).getName();
        getinfo = DataTree.getCategoryItem(category_list).get(position).getInfo_short();
        getprice = DataTree.getCategoryItem(category_list).get(position).getPrice();
        getimage = DataTree.getCategoryItem(category_list).get(position).getImage_id();
        getfavorite =  DataTree.getCategoryItem(category_list).get(position).getFavorite();
        if(getfavorite==false) {
            favorite.setImageResource(R.drawable.ic_favorite_border);
        }else{
            favorite.setImageResource(R.drawable.ic_favorite_all);
        }
        favorite.setOnClickListener(this);
        image.setImageResource(getimage);
        image.setOnClickListener(this);
        name.setText(getname);
        price.setText("Cena: "+getprice+"zł");
        info.setText(getinfo);
        add_basket.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.detailed_view_favorite:
                if (getfavorite == false) {
                    //Log.d("Detailed", "is false");
                    DataTree.getCategoryItem(category_list).get(position).setFavorite(true);
                    favorite.setImageResource(R.drawable.ic_favorite_all);
                } else {
                    //Log.d("Detailed", "is true");
                    DataTree.getCategoryItem(category_list).get(position).setFavorite(false);
                    favorite.setImageResource(R.drawable.ic_favorite_border);
                }
                getfavorite = DataTree.getCategoryItem(category_list).get(position).getFavorite();
                /*Snackbar.make(v, "Release date " + getfavorite, Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();*/
                break;
            case R.id.detailed_view_image:
                Gallery gallery = new Gallery();
                Bundle args = new Bundle();
                args.putInt("category_details",category_list);
                args.putInt("position_details",position);
                gallery.setArguments(args);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack("Galeria");
                transaction.replace(R.id.fragment_layout,gallery, "Galeria");
                transaction.commitAllowingStateLoss();
                /*Snackbar.make(v, "Kliknieto obrazek", Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();*/
                break;
            case R.id.add_basket:
                v.startAnimation(buttonClick);
                DataTree.addToBasket(category_list,position,getname,getprice,getinfo,"",getimage,getfavorite);
                MainActivity.setupBadget();
                break;
        }
    }
}