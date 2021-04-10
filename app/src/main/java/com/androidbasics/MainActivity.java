package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

//          Create Intent Service for Network Request

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyTag";
    private TextView mLog;

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra(MyIntentService.SERVICE_PAYLOAD);
            logOutput(data);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    public void runCode(View view) {
        Intent intent=new Intent(MainActivity.this,MyIntentService.class);
        startService(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver, new IntentFilter(MyIntentService.SERVICE_MESSAGE));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(mReceiver);
    }

    private void logOutput(String data) {
        Log.d(TAG, "logOutput : " + data);
        mLog.append("\n" + data);
    }

    private void initViews() {
        mLog = findViewById(R.id.txt_vw);
    }
}