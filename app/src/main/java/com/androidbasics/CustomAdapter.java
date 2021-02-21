package com.androidbasics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    private final  String[] name;
    private final int[] image;
    private final LayoutInflater layoutInflater;

    public CustomAdapter(Context context, String[] name, int[] image) {
        this.name = name;
        this.image = image;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView;
        rootView= layoutInflater.inflate(R.layout.list_item_layout,null);
        TextView textView=rootView.findViewById(R.id.txtVw);
        ImageView imageView=rootView.findViewById(R.id.imgVw);
        textView.setText(name[position]);
        imageView.setImageResource(image[position]);

        return rootView;
    }
}
