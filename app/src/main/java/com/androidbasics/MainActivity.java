package com.androidbasics;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.DialogCompat;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
        alertBuilder.setCancelable(false);

        // Add User Actions On AlertDialog
        alertBuilder.setPositiveButton("Yup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"Ohh Yesssss!!...",Toast.LENGTH_LONG).show();
            }
        });
        alertBuilder.setNegativeButton("Nooop", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"So Sorry !!...",Toast.LENGTH_LONG).show();
            }
        });
        alertBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"Thik Hai Bhai",Toast.LENGTH_LONG).show();
                alertBuilder.create().dismiss();
            }
        });

        alertBuilder.show();

    }
}