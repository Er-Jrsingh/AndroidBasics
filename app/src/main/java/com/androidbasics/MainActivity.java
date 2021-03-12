package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EMAIL_KEY = "email_key";
    public static final String PASS_KEY ="pass_key";
    public static final String FILE_NAME = "my_file";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String email=getIntent().getStringExtra(LoginActivity.EMAIL_KEY);
        String pass=getIntent().getStringExtra(LoginActivity.PASS_KEY);
        TextView mTextVw= findViewById(R.id.tv_mail);

        mTextVw.setText("Your Email :- \n"+ email+"\nPassword :- \n"+pass);
        Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show();

        SharedPreferences.Editor editor= getSharedPreferences(FILE_NAME,MODE_PRIVATE).edit();
        editor.putString(EMAIL_KEY,email);
        editor.putString(PASS_KEY,pass);
        editor.apply();

        Toast.makeText(this,"Stored At SharedPreference",Toast.LENGTH_LONG).show();

    }

    public void signOut(View view) {

        Intent intent= new Intent(MainActivity.this,LoginActivity.class);
        
        startActivity(intent);
        finish();
    }
}