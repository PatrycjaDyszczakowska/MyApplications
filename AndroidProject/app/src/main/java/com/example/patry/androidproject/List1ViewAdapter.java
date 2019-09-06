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

public class List1ViewAdapter extends ArrayAdapter<CategoryItem> implements View.OnClickListener{
    private ArrayList<CategoryItem> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtPrice;
        TextView txtInfo;
        ImageView imageElement;
        ImageView imageFavorite;
    }

    public List1ViewAdapter(ArrayList<CategoryItem> data, Context context) {
        super(context, R.layout.row, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        CategoryItem dataModel=(CategoryItem) object;

        switch (v.getId())
        {
            case R.id.favorite:
                ImageView i = (ImageView)v.findViewById(R.id.favorite);
                if(dataModel.getFavorite()==false){
                    dataModel.setFavorite(true);
                    i.setImageResource(R.drawable.ic_favorite_all);
                }else{
                    dataModel.setFavorite(false);
                    i.setImageResource(R.drawable.ic_favorite_border);
                }
                /*Snackbar.make(v, "Release date " +dataModel.getFavorite(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();*/
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CategoryItem dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.price);
            viewHolder.txtInfo = (TextView) convertView.findViewById(R.id.info);
            viewHolder.imageElement = (ImageView) convertView.findViewById(R.id.image_element);
            viewHolder.imageFavorite = (ImageView) convertView.findViewById(R.id.favorite);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtPrice.setText("Cena: "+dataModel.getPrice()+"zł");
        viewHolder.txtInfo.setText(dataModel.getInfo_short());
        viewHolder.imageElement.setImageResource(dataModel.getImage_id());
        if(dataModel.getFavorite()==false) {
            viewHolder.imageFavorite.setImageResource(R.drawable.ic_favorite_border);
        }else{
            viewHolder.imageFavorite.setImageResource(R.drawable.ic_favorite_all);
        }
        viewHolder.imageFavorite.setOnClickListener(this);
        viewHolder.imageFavorite.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
