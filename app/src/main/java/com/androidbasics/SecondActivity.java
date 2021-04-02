package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView mOutputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mOutputText = findViewById(R.id.sec_tv);

        if (getIntent().hasExtra(MainActivity.MESSAGE_KEY)) {
            String message = getIntent().getStringExtra(MainActivity.MESSAGE_KEY);
            Toast.makeText(this, message + " From Notification", Toast.LENGTH_LONG).show();
            mOutputText.setText(message);

        }

    }
}