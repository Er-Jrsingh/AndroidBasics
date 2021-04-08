/*
package com.androidbasics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

//Working with Implicit & Static Broadcast Receiver
public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Toast.makeText(context, "Boot Complete", Toast.LENGTH_SHORT).show();                                 //  It is working In Nougat Above & Below Devices
        } else if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            Toast.makeText(context, "Connectivity Status Changed", Toast.LENGTH_SHORT).show();          // It is working only Nougat below devices
        }
    }
}
*/
