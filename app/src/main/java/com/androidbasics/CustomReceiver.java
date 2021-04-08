package com.androidbasics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if ("com.androidbasics.ACTION_SEND".equals(intent.getAction())) {
            String extraData = intent.getStringExtra("com.androidbasics.EXTRA_DATA");

            Toast.makeText(context, "Toast From Receiver : " + extraData, Toast.LENGTH_SHORT).show();
        }
    }
}
