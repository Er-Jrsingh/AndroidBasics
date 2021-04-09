package com.androidbasics;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//        Broadcast Receiver -Send One Broadcast to Multiple Receivers & Set Priority at Receiver
//       Receiver Code - Ordered Broadcast Receiver -Send One Broadcast to Multiple Receivers ,Get And Change Property ,Practical Scenario (Transfer This Code Into Another Project To Run Both App At a Time ) (Sender Code written In BroadcastReceiver)

public class MainActivity extends AppCompatActivity {
    private TextView mTv;
    //    This Is Called When App is In Foreground
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            mTv.setText("Congo !! UI Updated");
            if (isOrderedBroadcast()) {
                abortBroadcast();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTv = findViewById(R.id.tv);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("com.example.custombroadcastsender.ACTION_SEND");
        intentFilter.setPriority(121);
        registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mReceiver);
    }
}
