package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*          Method  First       */

        SystemClock.sleep(2000);
        startActivity(new Intent(this,MainActivity.class));
        finish();

    }
}