package com.example.patry.androidproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.patry.androidproject.Data.FavoriteItem;

import java.util.ArrayList;

public class SearchNameAdapter extends BaseAdapter implements Filterable, View.OnClickListener {

    private ArrayList<FavoriteItem> mOriginalValues; // Original Values
    private ArrayList<FavoriteItem> mDisplayedValues;    // Values to be displayed
    LayoutInflater inflater;
    Context mContext;

    public SearchNameAdapter(Context context, ArrayList<FavoriteItem> mProductArrayList) {
        this.mOriginalValues = mProductArrayList;
        this.mDisplayedValues = mProductArrayList;
        inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mDisplayedValues.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        RelativeLayout llContainer;
        TextView txtName;
        TextView txtPrice;
        TextView txtInfo;
        ImageView imageElement;
        ImageView imageFavorite;
    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        FavoriteItem dataModel=mDisplayedValues.get(position);

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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final FavoriteItem dataModel=mDisplayedValues.get(position);
        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.row, null);
            holder.llContainer = convertView.findViewById(R.id.search_name_layout);
            holder.txtName = (TextView) convertView.findViewById(R.id.name);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.price);
            holder.txtInfo = (TextView) convertView.findViewById(R.id.info);
            holder.imageElement = (ImageView) convertView.findViewById(R.id.image_element);
            holder.imageFavorite = (ImageView) convertView.findViewById(R.id.favorite);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtName.setText(dataModel.getName());
        holder.txtPrice.setText("Cena: "+dataModel.getPrice()+"zł");
        holder.txtInfo.setText(dataModel.getInfo_short());
        holder.imageElement.setImageResource(dataModel.getImage_id());
        if(dataModel.getFavorite()==false) {
            holder.imageFavorite.setImageResource(R.drawable.ic_favorite_border);
        }else{
            holder.imageFavorite.setImageResource(R.drawable.ic_favorite_all);
        }
        holder.imageFavorite.setOnClickListener(this);
        holder.imageFavorite.setTag(position);

        holder.llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Detailed_view detailed_view = new Detailed_view();
                Bundle args = new Bundle();
                args.putInt("category_list",dataModel.getCategory());
                args.putInt("position",dataModel.getPosition());
                detailed_view.setArguments(args);
                FragmentTransaction transaction = ((MainActivity)mContext).getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack("Widok Szczegółowy");
                transaction.replace(R.id.fragment_layout,detailed_view, "Widok Szczegółowy");
                transaction.commitAllowingStateLoss();
            }
        });

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                mDisplayedValues = (ArrayList<FavoriteItem>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<FavoriteItem> FilteredArrList = new ArrayList<>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<FavoriteItem>(mDisplayedValues); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i).getName();
                        if (data.toLowerCase().contains(constraint.toString())) {
                            FilteredArrList.add(new FavoriteItem(mOriginalValues.get(i).getCategory(),mOriginalValues.get(i).getPosition(),
                                    mOriginalValues.get(i).getName(),mOriginalValues.get(i).getPrice(),
                                    mOriginalValues.get(i).getInfo_short(),mOriginalValues.get(i).getInfo_long(),
                                    mOriginalValues.get(i).getImage_id(),mOriginalValues.get(i).getFavorite()));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }
}
