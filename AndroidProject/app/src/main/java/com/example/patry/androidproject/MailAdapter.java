package com.example.patry.androidproject;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.patry.androidproject.Data.MailItem;

import java.util.ArrayList;

public class MailAdapter extends ArrayAdapter<MailItem>{
    private ArrayList<MailItem> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtAddress;
        TextView txtSubject;
        TextView txtMessage;
    }

    public MailAdapter(ArrayList<MailItem> data, Context context) {
        super(context, R.layout.mail_row, data);
        this.dataSet = data;
        this.mContext = context;

    }


    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        MailItem dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.mail_row, parent, false);
            viewHolder.txtAddress = convertView.findViewById(R.id.mail_row_adres);
            viewHolder.txtSubject = convertView.findViewById(R.id.mail_row_temat);
            viewHolder.txtMessage = convertView.findViewById(R.id.mail_row_wiadomosc);
            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtAddress.setText(dataModel.getAddress());
        viewHolder.txtSubject.setPaintFlags(viewHolder.txtSubject.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        viewHolder.txtSubject.setText(dataModel.getSubject());
        viewHolder.txtMessage.setText(dataModel.getMessage());
        // Return the completed view to render on screen
        return convertView;
    }
}