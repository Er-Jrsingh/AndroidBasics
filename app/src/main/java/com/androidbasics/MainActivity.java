package com.androidbasics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.input);
        Button share = findViewById(R.id.share);
        Button url = findViewById(R.id.url);
        Button sms = findViewById(R.id.sms);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editText.getText().toString();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, name);
                startActivity(sendIntent);
            }
        });
        url.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View v) {
                Uri url=Uri.parse("https://stackoverflow.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW,url);
                if(webIntent.resolveActivity(getPackageManager())!=null) {
                    startActivity(webIntent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"App Not Found!!",Toast.LENGTH_LONG).show();
                }
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View v) {
                Uri sms=Uri.parse("smsto:9691177");
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO,sms);
                if(smsIntent.resolveActivity(getPackageManager())!=null) {
                    startActivity(smsIntent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"App Not Found!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}