package com.androidbasics.recyclerview.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.androidbasics.R;
import com.androidbasics.model.CityItem;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//          Show Downloaded JSON Data(POJO Objects) in Recycler View
//          Get Images From assets & Data From Api & Show in Recycler View
//          Get Data With Image From Api & Show in Recycler View
//          Lazy Load (Download) Images & Show in RecyclerView

public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.MyViewHolder> {

    public static final String TAG = "MyTag";
    private final List<CityItem> mDataList;
    private final Context context;
    private final Map<String, Bitmap> mBitmaps =new HashMap<>();

    //    public MyDataAdapter(Context context, List<CityItem> mDataList, Map<String, Bitmap> mBitmaps) {
    public MyDataAdapter(Context context, List<CityItem> mDataList) {
        this.context = context;
        this.mDataList = mDataList;
//        this.mBitmaps = mBitmaps;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CityItem cityItem = mDataList.get(position);
        Log.d(TAG, "onBindViewHolder: Name: " + cityItem.getCityname() + " : " + cityItem.getImage());

        holder.textView.setText(cityItem.getCityname());
//        Image From Api
        holder.imageView.setImageBitmap(mBitmaps.get(cityItem.getCityname()));

//        Lazy Load (Download) Images & Show in RecyclerView
        if (mBitmaps.containsKey(cityItem.getCityname())) {
            holder.imageView.setImageBitmap(mBitmaps.get(cityItem.getCityname()));
        } else {
            MyImageAsyncTask task = new MyImageAsyncTask();
            task.setViewHolder(holder);
            task.execute(cityItem);
        }
/*
//        if We Want To Get Image From assets Directory
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(cityItem.getImage());
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            holder.imageView.setImageBitmap(bitmap);
            Log.d(TAG, "getView: Image Downloaded: " + cityItem.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream == null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
  */
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    //    Lazy Load (Download) Images & Show in RecyclerView
    //    Best Use Of AsyncTask

    class MyImageAsyncTask extends AsyncTask<CityItem, Void, Bitmap> {

        private static final String PHOTO_BASE_URL = "http://192.168.43.54/hamara_pyara_bharat/images/";
        private CityItem mCityItem;
        private MyViewHolder mViewHolder;

        public void setViewHolder(MyViewHolder myViewHolder) {
            this.mViewHolder = myViewHolder;
        }

        @Override
        protected Bitmap doInBackground(CityItem... cityItems) {

            Bitmap bitmap = null;
            mCityItem = cityItems[0];

            String imgUrl = PHOTO_BASE_URL + mCityItem.getImage();

            InputStream inputStream = null;

            try {
                URL imageUrl = new URL(imgUrl);
                inputStream = (InputStream) imageUrl.getContent();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mViewHolder.imageView.setImageBitmap(bitmap);      // set Image In RecyclerView
            mBitmaps.put(mCityItem.getCityname(), bitmap);      //  Hold data For future Use (Before App Destroy It Present In Memory)
        }
    }
}
