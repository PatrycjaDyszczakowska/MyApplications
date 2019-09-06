package com.example.patry.androidproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.patry.androidproject.Data.DataTree;
import com.example.patry.androidproject.Data.FavoriteItem;

import java.util.ArrayList;

public class SearchName extends Fragment {
    ListView list;
    EditText editText;
    ArrayList<FavoriteItem> searchItems;
    SearchNameAdapter searchNameAdapter;

    public SearchName() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.search_name, container, false);
        searchItems = new ArrayList<>();
        editText = v.findViewById(R.id.search_name);
        list = v.findViewById(R.id.search_list);
        for(int i = 0 ;i < DataTree.getCategory().size();i++){
            for(int j = 0;j<DataTree.getCategoryItem(i).size();j++){
                searchItems.add(new FavoriteItem(i,j,DataTree.getCategoryItem(i).get(j).getName(),DataTree.getCategoryItem(i).get(j).getPrice(),DataTree.getCategoryItem(i).get(j).getInfo_short(),
                        DataTree.getCategoryItem(i).get(j).getInfo_long(),DataTree.getCategoryItem(i).get(j).getImage_id(),DataTree.getCategoryItem(i).get(j).getFavorite()));
            }
        }
        searchNameAdapter = new SearchNameAdapter(v.getContext(), searchItems);
        list.setAdapter(searchNameAdapter);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchNameAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
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
        });*/
        return v;
    }
}
