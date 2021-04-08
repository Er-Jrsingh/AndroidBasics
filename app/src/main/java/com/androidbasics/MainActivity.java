package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

//      Working with Implicit & Static Broadcast Receiver
//      Creating & Working with Dynamic & Sticky Broadcast Receiver ,Best Practice : Register in start() & Unregister at stop()(It is Destroyed When Activity Destroy )
//      Creating & Working with Dynamic & Sticky Broadcast Receiver Using Application Class  (It is  not Destroyed When Activity Destroy & Active Until App Close )
//      Creating & Working with Custom Broadcast Receiver(Broadcast Sender Code is In CustomBroadcastSender Branch)

public class MainActivity extends AppCompatActivity {

//    DynamicBroadcastReceiver dynamicBroadcastReceiver = new DynamicBroadcastReceiver();

    CustomReceiver customReceiver = new CustomReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Creating & Working with Custom Broadcast Receiver

        IntentFilter intentFilter = new IntentFilter("com.androidbasics.ACTION_SEND");
        registerReceiver(customReceiver, intentFilter);


/*
//        Creating & Working with Dynamic & Sticky Broadcast Receiver

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);       // Using This We Can Add Multiple IntentFilters
        registerReceiver(dynamicBroadcastReceiver, intentFilter);
*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(dynamicBroadcastReceiver);
        unregisterReceiver(customReceiver);
    }
}