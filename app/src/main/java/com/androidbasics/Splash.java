package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*          Method  First       */

//        SystemClock.sleep(2000);
//        startActivity(new Intent(this,MainActivity.class));
//        finish();

        /*          Method  Second       */

//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Intent intent =new Intent(Splash.this,MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },3000);

        /*          Method  Third       */

//        Thread thread=new Thread(){
//            public void run(){
//                try {
//                    sleep(1000);
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                }
//                finally {
//                    Intent intent=new Intent(Splash.this,MainActivity.class);
//                    startActivity(intent);
//                }
//            }
//        };thread.start();

        /*          Method  Fourth       */

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        }, 5000);

    }
}