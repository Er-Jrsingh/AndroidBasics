package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//      Using Local Broadcast Receiver -Send Broadcast with in Application
public class MainActivity extends AppCompatActivity {
    private TextView mText;

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra("key");
            mText.append("\n" + data);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = findViewById(R.id.tv);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(MyIntentService.MY_SERVICE_INTENT);
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(mReceiver);
    }

    public void sendBroadcast(View view) {
        Intent serviceIntent = new Intent(this, MyIntentService.class);
        serviceIntent.putExtra("key", "Initial Value");
        startService(serviceIntent);
    }
}