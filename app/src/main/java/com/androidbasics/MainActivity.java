package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.MediaRouteButton;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.androidbasics.services.MyDownloadService;

//    Code(For Nougat(Api 25 & Lower) For Started Service (onBind Return Null),By Default It Run On UI Thread So That Screen Face Drop Frames(Screen Freeze)
//     Here We Stop The Service At clearCode Method(Clear Code Button) Of MainActivity
//     Here We Set up Worker Thread, Handler & Looper in Started Service(followed Production Ready Code Approach)
//     use Async Task in Started Service
//     Stop Started Service with Stop Self & Stop Self Result In Download Handler(Worker Thread).We'll make out Song Download Service to restart for only those songs that are not downloaded. If a song is downloaded and our service crashes and restarts, then that song will not be downloaded again.
//     Update UI/Main Thread from Service using Result Receiver
//     Send Data from Service to UI using Broadcast Receiver(Better Approach)

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyTag";
    public static final String MESSAGE_KEY = "message_key";
    private TextView nLog;
    private ScrollView mScrollView;
    private ProgressBar mProgressBar;
//    private Handler mHandler;


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String songName = intent.getStringExtra(MESSAGE_KEY);

            log(songName + " Downloaded");
            Log.d(TAG, "onReceive : Thread Name : " + Thread.currentThread().getName());
            displayProgressBar(false);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

//        mHandler = new Handler();
    }

    public void runCode(View view) {

        log("Song Downloading Start... ");
        displayProgressBar(true);

/*

//        Send Intent To MyDownloadService.onStartCommand

        for (String song : Playlist.songs) {

            Intent intent = new Intent(MainActivity.this, MyDownloadService.class);
            intent.putExtra(MESSAGE_KEY, song);
            startService(intent);

        }
*/

/*
        //     Update UI/Main Thread from Service using Result Receiver

        ResultReceiver resultReceiver = new MyDownloadResultReciever(null);

        for (String song : Playlist.songs) {

            Intent intent = new Intent(MainActivity.this, MyDownloadService.class);
            intent.putExtra(MESSAGE_KEY, song);
            intent.putExtra(Intent.EXTRA_RESULT_RECEIVER, resultReceiver);
            startService(intent);

        }
    */

//     Send Data from Service to UI using Broadcast Receiver(Better Approach)

        for (String song : Playlist.songs) {

            Intent intent = new Intent(MainActivity.this, MyDownloadService.class);
            intent.putExtra(MESSAGE_KEY, song);
            startService(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mReceiver, new IntentFilter(DownloadHandler.SERVICE_MESSAGE));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mReceiver);
    }

    public void clearCode(View view) {

        Intent intent = new Intent(MainActivity.this, MyDownloadService.class);
        stopService(intent);

        nLog.setText("");
        scrollTextToEnd();
    }

    public void displayProgressBar(boolean display) {
        if (display) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void scrollTextToEnd() {
        mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    public void log(String message) {
        Log.i(TAG, message);
        nLog.append("\n" + message + "\n");
        scrollTextToEnd();
    }

    private void initView() {
        mScrollView = findViewById(R.id.scroll_view);
        nLog = findViewById(R.id.txt_view);
        mProgressBar = findViewById(R.id.pro_bar);
    }

//     Update UI/Main Thread from Service using Result Receiver
/*

    public class MyDownloadResultReciever extends ResultReceiver {

        public MyDownloadResultReciever(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);

            if (resultCode == RESULT_OK && resultData != null) {

                Log.d(TAG, "onReceiveResult : Thread Name : " + Thread.currentThread().getName());     // It Is Not Running On UI Thread

                String songName = resultData.getString(MESSAGE_KEY);

//                MainActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        log(songName + " Downloaded");
//                        displayProgressBar(false);
//                    }
//                });

//          Another Way To Get UI Thread
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        log(songName + " Downloaded");
                        displayProgressBar(false);
                    }
                });


            }
        }
    }
*/
}