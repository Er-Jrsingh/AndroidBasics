package com.androidbasics;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

//      Using Local Broadcast Receiver -Send Broadcast with in Application

public class MyIntentService extends IntentService {

    public static final String MY_SERVICE_INTENT = "my_service_intent";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent resultIntent = new Intent(MY_SERVICE_INTENT);
        resultIntent.putExtra("key", "Download Has Been Completed");

        LocalBroadcastManager.getInstance(getApplicationContext())
                .sendBroadcast(resultIntent);
    }
}

