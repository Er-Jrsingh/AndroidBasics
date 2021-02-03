package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity
{
    ImageView logo,logo_txt;
    Animation top,bottom;
    @SuppressWarnings("Convert2Lambda")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        logo= findViewById(R.id.logo);
        logo_txt= findViewById(R.id.logo_txt);

        top= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logo_anim);
        bottom= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logo_txt_anim);

        logo.setAnimation(top);
        logo_txt.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        }, 5000);
    }
}