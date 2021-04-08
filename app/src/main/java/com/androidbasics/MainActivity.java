package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//      Creating & Working with Custom Broadcast Receiver
//      This Is  Custom Broadcast Sender App's Code (Transfer This Code Into Another Project To Run Both App At a Time )
//       Receiver Code for Branch BroadcastReceiver Written In Class AndroidBasicsBroadcastReceiver (Transfer AndroidBasicsBroadcastReceiver Code into Another Project )(ignore Previous Codes )

public class MainActivity extends AppCompatActivity {
    private TextView mTv;
    private MyInnerReceiver myInnerReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.tv);
        myInnerReceiver = new MyInnerReceiver();
    }

    public void sendBroadcast(View view) {

        Intent intent = new Intent("com.androidbasics.ACTION_SEND");
        intent.putExtra("com.androidbasics.EXTRA_DATA", "Hola !  Coders : Message From Sender ");
        sendBroadcast(intent);
    }

//    We Can Create BroadcastReceiver Using Separate Class , Inner Class , Anonymous Class
//    This App  Will Act as Both Sender (on button Click send broadcast )& Receiver(Because below code)

    public class MyInnerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.androidbasics.ACTION_SEND".equals(intent.getAction())) {
                String extraData = intent.getStringExtra("com.androidbasics.EXTRA_DATA");

                mTv.append("\n" + extraData);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("com.androidbasics.ACTION_SEND");
        registerReceiver(myInnerReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myInnerReceiver);
    }
}