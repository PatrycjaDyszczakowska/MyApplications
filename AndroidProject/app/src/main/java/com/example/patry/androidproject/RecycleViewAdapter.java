package com.example.patry.androidproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patry.androidproject.Data.CategoryItem;
import com.example.patry.androidproject.Data.FavoriteItem;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<FavoriteItem> dataSet;
    Context mContext;

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        TextView txtRecycleName;
        TextView txtRecyclePrice;
        ImageView imageRecycleElement;
        ImageView imageRecycleFavorite;
        RelativeLayout recycleLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            txtRecycleName = itemView.findViewById(R.id.recycle_name);
            txtRecyclePrice = itemView.findViewById(R.id.recycle_price);
            imageRecycleElement = itemView.findViewById(R.id.recycle_image);
            imageRecycleFavorite = itemView.findViewById(R.id.recycle_favorite);
            recycleLayout = itemView.findViewById(R.id.recycle_layout);
        }
    }

    public RecycleViewAdapter(ArrayList<FavoriteItem> data, Context context) {
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        FavoriteItem recycleDataModel=dataSet.get(position);

        switch (v.getId())
        {
            case R.id.recycle_favorite:
                ImageView i = (ImageView)v.findViewById(R.id.recycle_favorite);
                if(recycleDataModel.getFavorite()==false){
                    recycleDataModel.setFavorite(true);
                    i.setImageResource(R.drawable.ic_favorite_all);
                }else{
                    recycleDataModel.setFavorite(false);
                    i.setImageResource(R.drawable.ic_favorite_border);
                }
                /*Snackbar.make(v, "Release date " +recycleDataModel.getFavorite(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();*/
                break;
            case R.id.recycle_layout:
                Detailed_view detailed_view = new Detailed_view();
                Bundle args = new Bundle();
                args.putInt("category_list",recycleDataModel.getCategory());
                args.putInt("position",recycleDataModel.getPosition());
                detailed_view.setArguments(args);
                FragmentTransaction transaction = ((MainActivity)mContext).getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack("Widok Szczegółowy");
                transaction.replace(R.id.fragment_layout,detailed_view, "Widok Szczegółowy");
                transaction.commitAllowingStateLoss();


                /*Snackbar.make(v, recycleDataModel.getName()+"\nCena: "+recycleDataModel.getPrice()+"\n"+recycleDataModel.getInfo_short(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();*/
                break;
        }
    }

    private int lastPosition = -1;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_row, viewGroup, false);
        ViewHolder contentView = new ViewHolder(view);
        return contentView;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        FavoriteItem recycleDataModel = dataSet.get(position);
        // Check if an existing view is being reused, otherwise inflate the view
        //Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        //result.startAnimation(animation);

        viewHolder.txtRecycleName.setText(recycleDataModel.getName());
        viewHolder.txtRecyclePrice.setText("Cena: "+recycleDataModel.getPrice()+"zł");
        viewHolder.imageRecycleElement.setImageResource(recycleDataModel.getImage_id());
        if(recycleDataModel.getFavorite()==false) {
            viewHolder.imageRecycleFavorite.setImageResource(R.drawable.ic_favorite_border);
        }else{
            viewHolder.imageRecycleFavorite.setImageResource(R.drawable.ic_favorite_all);
        }
        viewHolder.imageRecycleFavorite.setOnClickListener(this);
        viewHolder.imageRecycleFavorite.setTag(position);
        viewHolder.recycleLayout.setOnClickListener(this);
        viewHolder.recycleLayout.setTag(position);
        // Return the completed view to render on screen
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
