package com.androidbasics;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyIntentService extends IntentService {

    private static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(TAG, "onHandleIntent : Called ");

//        Start Foreground Service so that service is not stopped when app is crashed
        startForeground(10001, getNotification());

        if (intent != null) {
            if (intent.hasExtra(MainActivity.MESSAGE_KEY)) {
                String data = intent.getStringExtra(MainActivity.MESSAGE_KEY);
                Log.d(TAG, "onHandleIntent : Data " + data);

                for (int i = 0; i < 10; i++) {
                    Log.d(TAG, "onHandleIntent : Counter Is : " + (i + 1));

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //    Notification for foreground service
    private Notification getNotification() {
        Notification
                notification = new NotificationCompat.Builder(this, App.CHANNEL_TWO_ID)
                .setSmallIcon(R.drawable.ic_baseline_sentiment_very_satisfied_24)
                .setContentTitle("Service Notification")
                .setContentText("A Background Service Is Running")
                .setPriority(NotificationCompat.PRIORITY_LOW) // Used By Less the 26 api Level Devices
                .setColor(Color.CYAN)
                .build();

        return notification;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy : Called ");
        stopForeground(true);
    }
}