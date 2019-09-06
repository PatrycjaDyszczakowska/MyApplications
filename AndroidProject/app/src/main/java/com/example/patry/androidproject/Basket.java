package com.example.patry.androidproject;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.patry.androidproject.Data.DataTree;
import com.example.patry.androidproject.Data.FavoriteItem;

import java.util.ArrayList;

public class Basket extends Fragment {
    TextView empty_view;
    TextView sum_text;
    TextView sum;
    Button buy;
    Button basketHistory;
    ArrayList<FavoriteItem> basketItem;
    BasketAdapter adapter;
    ListView basket_list;
    public Basket(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.basket, container, false);
        empty_view = v.findViewById(R.id.basket_empty);
        basket_list= v.findViewById(R.id.basket_list);
        sum_text = v.findViewById(R.id.basket_sum_text);
        sum = v.findViewById(R.id.basket_sum);
        buy = v.findViewById(R.id.basket_buy);
        basketHistory = v.findViewById(R.id.basket_history);
        basket_list.setEmptyView(empty_view);
        basketItem = DataTree.getBasket();
        adapter = new BasketAdapter(basketItem,v.getContext());
        basket_list.setAdapter(adapter);
        basket_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Detailed_view detailed_view = new Detailed_view();
                Bundle args = new Bundle();
                args.putInt("category_list",basketItem.get(position).getCategory());
                args.putInt("position",basketItem.get(position).getPosition());
                detailed_view.setArguments(args);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack("Widok Szczegółowy");
                transaction.replace(R.id.fragment_layout,detailed_view, "Widok Szczegółowy");
                transaction.commitAllowingStateLoss();


                Snackbar.make(view, basketItem.get(position).getName()+"\nCena: "+basketItem.get(position).getPrice()+"\n"+basketItem.get(position).getInfo_short(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
        if(basket_list.getCount()!=0){
            sum_text.setVisibility(View.VISIBLE);
            sum.setVisibility(View.VISIBLE);
            buy.setVisibility(View.VISIBLE);
            Double sum_price = 0.00;
            for(FavoriteItem favorite:basketItem){
                sum_price += favorite.getPrice();
            }
            sum.setText(sum_price + " zł");
        }
        basket_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DataTree.deleteElementFromBasket(position);
                basketItem.remove(position);
                MainActivity.setupBadget();
                Basket basket = new Basket();
                FragmentTransaction transaction_basket = ((MainActivity)getContext()).getSupportFragmentManager().beginTransaction();
                transaction_basket.addToBackStack("Koszyk");
                transaction_basket.replace(R.id.fragment_layout,basket, "Koszyk");
                transaction_basket.commitAllowingStateLoss();
                return false;
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataTree.addBasketHistory("Liczba produktów: "+basketItem.size()+"\nCena: "+sum.getText());
                basketItem = new ArrayList<>();
                DataTree.deleteAllFromBasket();
                MainActivity.setupBadget();
                Basket basket = new Basket();
                FragmentTransaction transaction_basket = ((MainActivity)getContext()).getSupportFragmentManager().beginTransaction();
                transaction_basket.addToBackStack("Koszyk");
                transaction_basket.replace(R.id.fragment_layout,basket, "Koszyk");
                transaction_basket.commitAllowingStateLoss();
            }
        });

        basketHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BasketHistory basket_history = new BasketHistory();
                FragmentTransaction transaction_basket = ((MainActivity)getContext()).getSupportFragmentManager().beginTransaction();
                transaction_basket.addToBackStack("Koszyk Historia");
                transaction_basket.replace(R.id.fragment_layout,basket_history, "Koszyk Historia");
                transaction_basket.commitAllowingStateLoss();
            }
        });
        return v;
    }
}
