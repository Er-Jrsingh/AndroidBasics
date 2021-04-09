package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import java.util.List;

//      Working with Implicit & Static Broadcast Receiver
//      Creating & Working with Dynamic & Sticky Broadcast Receiver ,Best Practice : Register in start() & Unregister at stop()(It is Destroyed When Activity Destroy )
//      Creating & Working with Dynamic & Sticky Broadcast Receiver Using Application Class  (It is  not Destroyed When Activity Destroy & Active Until App Close )
//      Creating & Working with Custom Broadcast Receiver(Broadcast Sender Code is In CustomBroadcastSender Branch)
//      Create Explicit Broadcast & Call In same App
//      Explicit Broadcast- Send Broadcast from One App to Other (Receiver Code Written in Branch CustomBroadcastSender )
//      Calling Implicit Broadcast as Explicit Broadcast In Android Oreo (Receiver Code Written in Branch CustomBroadcastSender Manifest File  & This  App Manifest File  )
//      Ordered Broadcast Receiver -Send One Broadcast to Multiple Receivers & Set Priority at Receiver(Receiver Code Written in Branch CustomBroadcastSender )
//      Ordered Broadcast Receiver -Send One Broadcast to Multiple Receivers ,Get And Change Property
//      Ordered Broadcast Receiver -Send One Broadcast to Multiple Receivers ,Get And Change Property,Practical Scenario (Receive code written in Branch BroadcastPracticalScenario)(Test App - Open both App In Split Mode)


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

    public void sendBroadcast(View view) {

//      Ordered Broadcast Receiver -Send One Broadcast to Multiple Receivers ,Get And Change Property,Practical Scenario
        Intent intent = new Intent("com.example.custombroadcastsender.ACTION_SEND");
        intent.setPackage("com.example.custombroadcastsender");
        sendOrderedBroadcast(intent, null);
/*
//      Ordered Broadcast Receiver -Send One Broadcast to Multiple Receivers & Set Priority at Receiver
        Intent intent = new Intent("com.example.custombroadcastsender.ACTION_SEND");
        intent.setPackage("com.example.custombroadcastsender");
        Bundle bundle = new Bundle();
        bundle.putString("message_key", "Start");
        sendOrderedBroadcast(intent, null, new OrderedBroadcastReceiver(), null, RESULT_CANCELED, "Start", bundle);
*/

/*

//        Calling Implicit Broadcast as Explicit Broadcast
        Intent intent = new Intent("com.example.custombroadcastsender.ACTION_SEND");

        PackageManager packageManager = getPackageManager();

        List<ResolveInfo> resolveInfo = packageManager.queryBroadcastReceivers(intent, 0);

        for (ResolveInfo info : resolveInfo) {
            ComponentName componentName = new ComponentName(info.activityInfo.packageName, info.activityInfo.name);
            intent.setComponent(componentName);
            sendBroadcast(intent);
        }
*/

 /*
//      Explicit Broadcast- Send Broadcast from One App to Other (Receiver Code Written in Branch CustomBroadcastSender )
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.example.custombroadcastreceiver", "com.example.custombroadcastreceiver.AndroidBasicsBroadcastReceiver");
        intent.setComponent(componentName);
        sendBroadcast(intent);
*/

/*
//          Create Explicit Broadcast & Call In same App

//        Intent intent = new Intent(this, MyExplicitBroadcast.class);
//        sendBroadcast(intent);

//        Similar as above code
//        Intent intent = new Intent();
//        intent.setClass(this, MyExplicitBroadcast.class)
//        sendBroadcast(intent);

//        Similar as above code
//        Intent intent = new Intent();
//        ComponentName componentName = new ComponentName(this, MyExplicitBroadcast.class);
//        intent.setComponent(componentName);
//        sendBroadcast(intent);
*/
    }
/*
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(dynamicBroadcastReceiver);
//        unregisterReceiver(customReceiver);
    }
*/

}