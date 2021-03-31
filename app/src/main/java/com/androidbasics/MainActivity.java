package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.androidbasics.services.MyIntentService;

//          Create & Run Intent Service It Helps To Simplify Started Service Intent Functionality(it create handler, thread , handleMessage StopSelf,etc Implicitly)
//          Implement Lifecycle of  a IntentService
//          Send Data from Intent Service to UI/Main Thread

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyTag";
    public static final String MESSAGE_KEY = "message_key";
    private TextView nLog;
    private ScrollView mScrollView;
    private ProgressBar mProgressBar;

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String songName = intent.getStringExtra(MESSAGE_KEY);
            log(songName + " Downloaded");
            displayProgressBar(false);

            Log.d(TAG, "onReceive :  Thread Name :  " + Thread.currentThread().getName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mReceiver, new IntentFilter(MyIntentService.INTENT_SERVICE_MESSAGE));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(mReceiver);
    }

    public void runCode(View view) {

        log("Song Downloading Start... ");
        displayProgressBar(true);

        for (String song : Playlist.songs) {

            Intent intent = new Intent(MainActivity.this, MyIntentService.class);
            intent.putExtra(MESSAGE_KEY, song);
            startService(intent);
        }
    }

    public void clearCode(View view) {

        Intent intent = new Intent(MainActivity.this, MyIntentService.class);
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
}