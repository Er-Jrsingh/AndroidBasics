package com.androidbasics;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//        Broadcast Receiver -Send One Broadcast to Multiple Receivers & Set Priority at Receiver( Transfer This Code Into Another Project To Run Both App At a Time ) (Ignore Previous code )
//       Receiver Code - Ordered Broadcast Receiver -Send One Broadcast to Multiple Receivers ,Get And Change Property

public class MainActivity extends AppCompatActivity {
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.tv);
    }
}
