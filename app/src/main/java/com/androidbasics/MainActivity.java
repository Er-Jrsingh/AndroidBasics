package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

//      Working with Implicit & Static Broadcast Receiver
//      Creating & Working with Dynamic & Sticky Broadcast Receiver ,Best Practice : Register in start() & Unregister at stop()(It is Destroyed When Activity Destroy )
//      Creating & Working with Dynamic & Sticky Broadcast Receiver Using Application Class  (It is  not Destroyed When Activity Destroy & Active Until App Close )
//      Creating & Working with Custom Broadcast Receiver(Broadcast Sender Code is In CustomBroadcastSender Branch)
//      Create Explicit Broadcast & Call In same App

public class MainActivity extends AppCompatActivity {

//    DynamicBroadcastReceiver dynamicBroadcastReceiver = new DynamicBroadcastReceiver();

//    CustomReceiver customReceiver = new CustomReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
//        Creating & Working with Custom Broadcast Receiver
        IntentFilter intentFilter = new IntentFilter("com.androidbasics.ACTION_SEND");
        registerReceiver(customReceiver, intentFilter);
*/

/*
//        Creating & Working with Dynamic & Sticky Broadcast Receiver

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);       // Using This We Can Add Multiple IntentFilters
        registerReceiver(dynamicBroadcastReceiver, intentFilter);
*/
    }

    //      Create Explicit Broadcast & Call In same App
    public void sendBroadcast(View view) {
//        Intent intent = new Intent(this, MyExplicitBroadcast.class);
//        sendBroadcast(intent);

//        Similar as above code
//        Intent intent = new Intent();
//        intent.setClass(this, MyExplicitBroadcast.class)
//        sendBroadcast(intent);

//        Similar as above code
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName(this, MyExplicitBroadcast.class);
        intent.setComponent(componentName);
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(dynamicBroadcastReceiver);
//        unregisterReceiver(customReceiver);
    }
}