package com.androidbasics;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import java.util.Arrays;
import java.util.List;


/*  AsyncTask cannot Handle Configuration Changes & Memory Leak So That AsyncTaskLoader Is Provided To Solve Problem But it Also Deprecated From Api 28 so Use LiveData & ViewModels  */

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private static final String TAG = "MyTag";
    private static final String DATA_KEY = "data_key";
    private ScrollView mScrollView;
    private TextView nLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


    }

    public void runCode(View view) {

//       Sending Data to Async Task Loader

        Bundle bundle=new Bundle();
        bundle.putString(DATA_KEY,"Url That Return Same Data");

//       Loader Architecture By default Cache The Data & Find By Given id(here 100) & Reuse That So That Previous Data Show Repeatedly & By Pass The loadInBackground  ,to overcome we use restartLoader

//        getSupportLoaderManager().initLoader(100, bundle, this).forceLoad();

        getSupportLoaderManager().restartLoader(100, bundle, this).forceLoad();


//        getSupportLoaderManager().initLoader(100, null, this).forceLoad();

    }

    public void clearCode(View view) {
        nLog.setText("");
        scrollTextToEnd();
    }

    private void scrollTextToEnd() {
        mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    private void initView() {
        mScrollView = findViewById(R.id.scroll_view);
        nLog = findViewById(R.id.txt_view);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {

        List<String> songList= Arrays.asList(PlayList.songs);
        return new MyAsyncTaskLoader(this,args,songList);


//        return new MyAsyncTaskLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        nLog.append("\n" + data + "\n");

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public static class MyAsyncTaskLoader extends AsyncTaskLoader<String> {

        private final Bundle mArgs;
        private final List<String> mSongList;

        public MyAsyncTaskLoader(@NonNull Context context, Bundle args, List<String> songList) {
            super(context);

            this.mArgs=args;
            this.mSongList=songList;
        }

        @Nullable
        @Override
        public String loadInBackground() {

            String data=mArgs.getString(DATA_KEY);
            Log.d(TAG, "loadInBackground URL : " +data );
            Log.d(TAG, "loadInBackground : " + Thread.currentThread().getName());

            for (String song:mSongList) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            Log.d(TAG, "loadInBackground : Song Downloaded " + song);
            }
            Log.d(TAG, "loadInBackground : Thread Terminated " + Thread.currentThread().getName());

            return "Download Completed...";     // Send To onLoadFinished
        }

        //In Below Method We Can Modify The return "Download Completed..." Data

        @Override
        public void deliverResult(@Nullable String data) {
            data += "Modified By deliverResult ";

            super.deliverResult(data);
        }
    }
}