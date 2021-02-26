package com.androidbasics;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void showFragi(View view) {
        Fragi fragi = new Fragi();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragi_container,fragi,"frag_tag")
                .commit();
    }

    public void removeFragi(View view) {
        Fragi frag = (Fragi) getSupportFragmentManager()
                .findFragmentByTag("frag_tag");

        if (frag != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(frag)
                    .commit();
        }
    }
}