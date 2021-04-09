package com.androidbasics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

//      Ordered Broadcast Receiver -Send One Broadcast to Multiple Receivers & Set Priority at Receiver
public class OrderedBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"OrderedBroadcastReceiver Called : Sender App",Toast.LENGTH_SHORT).show();
    }
}
