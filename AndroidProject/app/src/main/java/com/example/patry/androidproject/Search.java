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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.example.patry.androidproject.Data.DataTree;
import com.example.patry.androidproject.Data.SearchTable;

public class Search extends Fragment {
    LinearLayout linearLayout;
    RadioGroup radioGroup;
    TextView textView,min,max;
    Button search;
    CrystalRangeSeekbar crystalRangeSeekbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.search,container,false);
        linearLayout = v.findViewById(R.id.search_layout);
        search = v.findViewById(R.id.search_item);
        crystalRangeSeekbar = v.findViewById(R.id.search_seekbar);
        min = v.findViewById(R.id.search_seekbar_min);
        max = v.findViewById(R.id.search_seekbar_max);
        final Spinner spinner = v.findViewById(R.id.list_search);
        ArrayAdapter<String> adapter_search = new ArrayAdapter<>(getContext(),R.layout.spinner_list,getResources().getStringArray(R.array.caltegory));
        spinner.setAdapter(adapter_search);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                linearLayout.removeAllViews();
                Double MaxValue = 0.0;
                for(int i = 0; i<DataTree.getCategoryItem(position).size();i++){
                    if(MaxValue<DataTree.getCategoryItem(position).get(i).getPrice()){
                        MaxValue = DataTree.getCategoryItem(position).get(i).getPrice();
                    }
                }
                crystalRangeSeekbar.setMinValue((float)0.0);
                crystalRangeSeekbar.setMaxValue(MaxValue.floatValue());
                crystalRangeSeekbar.setMaxStartValue(MaxValue.floatValue());
                crystalRangeSeekbar.apply();
                crystalRangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
                    @Override
                    public void valueChanged(Number minValue, Number maxValue) {
                        min.setText("min: "+minValue);
                        max.setText("max: "+maxValue);
                    }
                });
                if(position<3){
                    radioGroup = new RadioGroup(getContext());
                    RadioButton[] rb = new RadioButton[SearchTable.screenTable.get(position).length];
                    for (int i = 0; i < SearchTable.screenTable.get(position).length; i++) {
                        rb[i] = new RadioButton(getContext());
                        rb[i].setText(SearchTable.screenTable.get(position)[i]);
                        rb[i].setId(i);
                        rb[i].setTextSize(20);
                        rb[i].setTextColor(getResources().getColor(R.color.colorToolBar));
                        radioGroup.addView(rb[i]);
                    }
                    textView = new TextView(getContext());
                    textView.setText("Przekatna ekranu [cal]:");
                    textView.setTextSize(25);
                    textView.setTextColor(getResources().getColor(R.color.colorToolBarDark));
                    linearLayout.addView(textView);
                    linearLayout.addView(radioGroup);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchList searchList= new SearchList();
                Bundle args = new Bundle();
                int category = spinner.getSelectedItemPosition();
                args.putInt("category",category);
                Log.d("Selected", ""+category);
                Log.d("Selected2",""+radioGroup.getCheckedRadioButtonId());
                args.putInt("Screen",radioGroup.getCheckedRadioButtonId());
                args.putDouble("MinValue", crystalRangeSeekbar.getSelectedMinValue().doubleValue());
                args.putDouble("MaxValue",crystalRangeSeekbar.getSelectedMaxValue().doubleValue());
                searchList.setArguments(args);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack("SearchList");
                transaction.replace(R.id.fragment_layout,searchList, "SearchList");
                transaction.commitAllowingStateLoss();
            }
        });
        return v;
    }
}