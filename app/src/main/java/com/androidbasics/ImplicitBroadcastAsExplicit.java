package com.androidbasics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

//      Calling Implicit Broadcast as Explicit Broadcast - Receiver Code  ( Transfer This Code Into Another Project To Run Both App At a Time ) (Ignore Previous code )

public class ImplicitBroadcastAsExplicit extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "ImplicitBroadcastAsExplicit Called : Receiver App", Toast.LENGTH_SHORT).show();
    }
}
