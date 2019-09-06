package com.example.patry.androidproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.patry.androidproject.Data.DataTree;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    Integer category;
    Integer pos_item;
    public ImageAdapter(Context c, Integer category, Integer pos_item){
        mContext = c;
        this.category=category;
        this.pos_item=pos_item;
        setTable();
    }

    private void setTable() {
        mThumbIds=new Integer[8];
        for(int i=0;i<8;i++) {
            mThumbIds[i] = DataTree.getCategoryItem(category).get(pos_item).getImage_id();
        }
    }

    public Integer[] mThumbIds;
    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setAdjustViewBounds(true);
        imageView.setBackgroundResource(R.drawable.layout_border);
        imageView.setPadding(10,10,10,10);
        imageView.setImageResource(mThumbIds[position]);
        //imageView.setScaleType(ImageView.ScaleType.CENTER);
        //imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return imageView;
    }
}
