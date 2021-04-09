package com.androidbasics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

//      Ordered Broadcast Receiver -Send One Broadcast to Multiple Receivers & Set Priority at Receiver
//      Ordered Broadcast Receiver -Send One Broadcast to Multiple Receivers ,Get And Change Property

public class OrderedBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context, "OrderedBroadcastReceiver Called : Sender App", Toast.LENGTH_SHORT).show();

//        Get & Change Property
        int initialCode = getResultCode();
        String initialData = getResultData();
        Bundle bundle = getResultExtras(true);
        String stringExtra = bundle.getString("message_key");

        initialCode++;
        stringExtra += "->BR : Sender ";

        String output = "initial Code : " + initialCode + "\n" +
                "initial Data : " + initialData + "\n" +
                "String Extra : " + stringExtra;

        Toast.makeText(context, output, Toast.LENGTH_SHORT).show();

        initialData = "BR : Sender";
        bundle.putString("message_key", stringExtra);

        setResult(initialCode, initialData, bundle);
    }
}
