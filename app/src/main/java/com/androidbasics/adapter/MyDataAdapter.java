package com.androidbasics.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidbasics.R;
import com.androidbasics.model.CityDataItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.MyViewHolder> {

    public static final String ITEM_KEY = "item_key";
    public static final String TAG = "my_tag";
    private final Context mContext;
    private final List<CityDataItem> mDataList;

    public MyDataAdapter(Context mContext, List<CityDataItem> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }
//    private List<CityDataItem> mDataListFull;


    /* Called When RecyclerView Needs To Layout For ListItem      */

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*      Here We Inflate Layout(Here layout/list_item_layout.xml) & Return To DataItem   */

        View view= LayoutInflater.from(mContext).inflate(R.layout.list_item_layout,parent,false);

        return new MyViewHolder(view);

    }

    /*      Called When RecyclerView Needs To Bind Data(Data Set)     */

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CityDataItem cityDataItem=mDataList.get(position);
        holder.textView.setText(cityDataItem.getCityName());


        InputStream inputStream=null;
        try{
            inputStream = mContext.getAssets().open(cityDataItem.getImage());
            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
            holder.imageView.setImageBitmap(bitmap);
            Log.d(TAG, "getView: Image Downloaded: "+cityDataItem.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (inputStream == null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /*      Return Number of Item */
    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    /*      Generic Class( Here MyViewHolder) Should Be Subclass of Adapter(Here MyDataAdapter) */
    /*      Below Is Represent A Independent DataItem(ListItem) & Shown In RecyclerView */

    public static class  MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);

        }
    }
}

