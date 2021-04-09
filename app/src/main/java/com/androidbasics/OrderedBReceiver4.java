package com.androidbasics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class OrderedBReceiver4 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context, "OrderedBReceiver_4 Called : Receiver App", Toast.LENGTH_SHORT).show();


//        Get & Change Property
        if (isOrderedBroadcast()) {
            int initialCode = getResultCode();
            String initialData = getResultData();
            Bundle bundle = getResultExtras(true);
            String stringExtra = bundle.getString("message_key");

            initialCode++;
            stringExtra += "->BR4";

            String output = "initial Code : " + initialCode + "\n" +
                    "initial Data : " + initialData + "\n" +
                    "String Extra : " + stringExtra;

            Toast.makeText(context, output, Toast.LENGTH_SHORT).show();

            initialData = "BR4";
            bundle.putString("message_key", stringExtra);

            setResult(initialCode, initialData, bundle);
        }
    }
}
