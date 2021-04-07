package com.androidbasics;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

//          Creating & Working with Dynamic & Sticky Broadcast Receiver Using Application Class  (It is  not Destroyed When Activity Destroy & Active Until App Close)

public class App extends Application {

    DynamicBroadcastReceiver dynamicBroadcastReceiver = new DynamicBroadcastReceiver();

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);       // Using This We Can Add Multiple IntentFilters
        registerReceiver(dynamicBroadcastReceiver, intentFilter);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterReceiver(dynamicBroadcastReceiver);
    }
}
