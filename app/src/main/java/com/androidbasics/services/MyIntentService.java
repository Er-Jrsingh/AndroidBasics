package com.androidbasics.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.androidbasics.MainActivity;

//  Create & Run Intent Service It Helps To Simplify Started Service Intent Functionality(it create handler, thread , handleMessage StopSelf,etc Implicitly),Only One Thread Is Allocate To Process

public class MyIntentService extends IntentService {

    private static final String TAG = "MyTag";

    public MyIntentService() {
        super("MyIntentService");
        setIntentRedelivery(true);  //START_REDELIVER_INTENT if True Otherwise START_STICKY
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String songName=intent.getStringExtra(MainActivity.MESSAGE_KEY);
        downloadSong(songName);

    }
    
    private void downloadSong(String song) {
        Log.d(TAG, "downloadSong : " + song + " Download Started...");

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "downloadSong : " + song + " Download Finished ");

    }
}