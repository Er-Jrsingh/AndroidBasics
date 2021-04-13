package com.androidbasics.recyclerview.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.androidbasics.R;
import com.androidbasics.model.CityItem;
import com.androidbasics.network.MyIntentService;
import com.androidbasics.recyclerview.adapter.MyDataAdapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.androidbasics.MainActivity.JSON_URL;

//          Show Downloaded JSON Data(POJO Objects) in Recycler View
//          Get Images From assets & Data From Api & Show in Recycler View
//          Get Data With Image From Api & Show in Recycler View
//          Lazy Load (Download) Images & Show in RecyclerView

public class ListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Map<String, Bitmap>> {

    public static final String TAG = "myTag";
    private List<CityItem> mDataList;
    private RecyclerView mRecyclerView;
    private MyDataAdapter mDataAdapter;
    private Map<String, Bitmap> mBitmaps;

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.hasExtra(MyIntentService.SERVICE_PAYLOAD)) {
                CityItem[] cityItems = (CityItem[])
                        intent.getParcelableArrayExtra(MyIntentService.SERVICE_PAYLOAD);

                mDataList = Arrays.asList(cityItems);       //  Converting List As Array
                Toast.makeText(context, "Items Downloaded: " + mDataList.size(), Toast.LENGTH_SHORT).show();

//                getSupportLoaderManager().initLoader(0, null, ListActivity.this)
//                        .forceLoad();
                Log.d(TAG, "onReceive: called");

//           Lazy Load (Download) Images & Show in RecyclerView
                showRecyclerData();

            } else if (intent.hasExtra(MyIntentService.SERVICE_EXCEPTION)) {
                String message = intent.getStringExtra(MyIntentService.SERVICE_EXCEPTION);

                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRecyclerView = findViewById(R.id.list_city);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        Intent intent = new Intent(ListActivity.this, MyIntentService.class);
        intent.setData(Uri.parse(JSON_URL));
        startService(intent);

        Log.d(TAG, "onCreate: Called");
    }

    private void showRecyclerData() {
//        mDataAdapter = new MyDataAdapter(this, mDataList, mBitmaps);
        mDataAdapter = new MyDataAdapter(this, mDataList);
        mRecyclerView.setAdapter(mDataAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver, new IntentFilter(MyIntentService.SERVICE_MESSAGE));
    }

    @Override
    protected void onStop() {
        super.onStop();

        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(mReceiver);
    }

//    Ignore Below Code Not Used In Lazy Loading
    @NonNull
    @Override
    public Loader<Map<String, Bitmap>> onCreateLoader(int id, @Nullable Bundle args) {
        return new MyImageTask(this, mDataList);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Map<String, Bitmap>> loader, Map<String, Bitmap> bitmapMap) {

        mBitmaps = bitmapMap;
//        mDataAdapter = new MyDataAdapter(this, mDataList, mBitmaps);
        mDataAdapter = new MyDataAdapter(this, mDataList);
        mRecyclerView.setAdapter(mDataAdapter);
        showRecyclerData();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Map<String, Bitmap>> loader) {

    }

    private static class MyImageTask extends AsyncTaskLoader<Map<String, Bitmap>> {

        private static final String PHOTO_BASE_URL = "http://192.168.43.54/hamara_pyara_bharat/images/";
        private static List<CityItem> mCityList;

        public MyImageTask(@NonNull Context context, List<CityItem> cityItems) {
            super(context);
            mCityList = cityItems;
        }

        @Nullable
        @Override
        public Map<String, Bitmap> loadInBackground() {

            Map<String, Bitmap> map = new HashMap<>();

            for (CityItem item : mCityList) {
                String imgUrl = PHOTO_BASE_URL + item.getImage();

                InputStream inputStream = null;

                try {
                    URL imageUrl = new URL(imgUrl);
                    inputStream = (InputStream) imageUrl.getContent();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    map.put(item.getCityname(), bitmap);

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
            }
            return map;
        }
    }
}