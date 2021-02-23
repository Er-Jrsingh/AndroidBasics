package com.androidbasics;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.DialogCompat;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createDialog(View view) {
        AlertDialog.Builder alertBuilder= new AlertDialog.Builder(this);
        alertBuilder.setTitle("Confirmation Box");
        alertBuilder.setMessage("Are You Sure??");
        alertBuilder.setCancelable(true);
        alertBuilder.show();

    }
}