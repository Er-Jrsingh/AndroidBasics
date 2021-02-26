package com.androidbasics;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Fragi fragi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void showFragi(View view) {
        fragi = new Fragi();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragi_container,fragi)
                .commit();
    }

    public void removeFragi(View view) {
        if (fragi != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(fragi)
                    .commit();
        }
    }
}