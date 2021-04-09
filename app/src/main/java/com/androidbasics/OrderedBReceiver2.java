package com.androidbasics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class OrderedBReceiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "OrderedBReceiver_2 Called : Receiver App", Toast.LENGTH_SHORT).show();

//        We Can Stop Next Broadcast in Chain
        if (isOrderedBroadcast()) {
            abortBroadcast();   // Close Next Broadcast But Sender Broadcast Still Called
        }
    }
}
