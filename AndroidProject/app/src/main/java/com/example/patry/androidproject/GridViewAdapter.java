package com.example.patry.androidproject;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patry.androidproject.Data.CategoryItem;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter<CategoryItem> implements View.OnClickListener {

    private ArrayList<CategoryItem> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView txtGridName;
        TextView txtGridPrice;
        ImageView imageGridElement;
        ImageView imageGridFavorite;
    }

    public GridViewAdapter(ArrayList<CategoryItem> data, Context context) {
        super(context, R.layout.grid_row, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        CategoryItem gridDataModel=(CategoryItem) object;

        switch (v.getId())
        {
            case R.id.grid_favorite:
                ImageView i = (ImageView)v.findViewById(R.id.grid_favorite);
                if(gridDataModel.getFavorite()==false){
                    gridDataModel.setFavorite(true);
                    i.setImageResource(R.drawable.ic_favorite_all);
                }else{
                    gridDataModel.setFavorite(false);
                    i.setImageResource(R.drawable.ic_favorite_border);
                }
                /*Snackbar.make(v, "Release date " +gridDataModel.getFavorite(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();*/
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CategoryItem gridDataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.grid_row, parent, false);
            viewHolder.txtGridName = convertView.findViewById(R.id.grid_name);
            viewHolder.txtGridPrice = convertView.findViewById(R.id.grid_price);
            viewHolder.imageGridElement = convertView.findViewById(R.id.grid_image);
            viewHolder.imageGridFavorite = convertView.findViewById(R.id.grid_favorite);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        //Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        //result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtGridName.setText(gridDataModel.getName());
        viewHolder.txtGridPrice.setText("Cena: "+gridDataModel.getPrice()+"zł");
        viewHolder.imageGridElement.setImageResource(gridDataModel.getImage_id());
        if(gridDataModel.getFavorite()==false) {
            viewHolder.imageGridFavorite.setImageResource(R.drawable.ic_favorite_border);
        }else{
            viewHolder.imageGridFavorite.setImageResource(R.drawable.ic_favorite_all);
        }
        viewHolder.imageGridFavorite.setOnClickListener(this);
        viewHolder.imageGridFavorite.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
