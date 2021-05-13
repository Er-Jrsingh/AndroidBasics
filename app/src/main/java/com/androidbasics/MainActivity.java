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

    public void createDialog(View view) {
        FragmentAlertDialog alertDialog = new FragmentAlertDialog();
//        alertDialog.setCancelable(false);
        alertDialog.show(getSupportFragmentManager(), "FragmentAlertDialog");
    }
}