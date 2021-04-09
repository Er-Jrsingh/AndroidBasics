package com.androidbasics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class OrderedBReceiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context, "OrderedBReceiver_2 Called : Receiver App", Toast.LENGTH_SHORT).show();
/*
//        We Can Stop Next Broadcast in Chain
        if (isOrderedBroadcast()) {
            abortBroadcast();   // Close Next Broadcast But Sender Broadcast Still Called
        }
  */

//        Get & Change Property
        if (isOrderedBroadcast()) {
            int initialCode = getResultCode();
            String initialData = getResultData();
            Bundle bundle = getResultExtras(true);
            String stringExtra = bundle.getString("message_key");

            initialCode++;
            stringExtra += "->BR2";

            String output = "initial Code : " + initialCode + "\n" +
                    "initial Data : " + initialData + "\n" +
                    "String Extra : " + stringExtra;

            Toast.makeText(context, output, Toast.LENGTH_SHORT).show();

            initialData = "BR2";
            bundle.putString("message_key", stringExtra);

            setResult(initialCode, initialData, bundle);
        }
    }
}
