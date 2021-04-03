package com.androidbasics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


//Start Broadcast from Notification with Channels
public class NotificationBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null) {
            if (intent.hasExtra(MainActivity.MESSAGE_KEY)) {
                String message = intent.getStringExtra(MainActivity.MESSAGE_KEY);
                Toast.makeText(context, message + " From BroadcastReceiver Notification", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
