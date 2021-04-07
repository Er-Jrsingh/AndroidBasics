package com.androidbasics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

//      Creating & Working with Dynamic Broadcast Receiver
public class DynamicBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            boolean boolExtra = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            if (!boolExtra) {
                Toast.makeText(context, "Connectivity Status : Internet Available ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Connectivity Status : Internet Not Available!! ", Toast.LENGTH_SHORT).show();
            }
        } else if (Intent.ACTION_TIME_TICK.equals(intent.getAction())) {
            Toast.makeText(context, "Time Increased By 1 Min ", Toast.LENGTH_SHORT).show();
        }
    }
}
