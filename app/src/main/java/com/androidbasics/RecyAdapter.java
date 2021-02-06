package com.androidbasics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.imagesViewHolder> {

    private final List imagesList;

    public RecyAdapter(List<Images> imagesList) {
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public imagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new imagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull imagesViewHolder holder, int position) {
        Images images = (Images) imagesList.get(position);
        holder.txt.setText(images.getTxt());
        holder.img.setImageResource(images.getImg());

    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public static class imagesViewHolder extends RecyclerView.ViewHolder {
        public TextView txt;
        public ImageView img;

        public imagesViewHolder(View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.brandTxt);
            img = itemView.findViewById(R.id.logoPic);
        }
    }
}
