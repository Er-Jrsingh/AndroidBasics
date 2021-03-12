package com.androidbasics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText mEmail;
    EditText mPass;
    public static final String EMAIL_KEY = "email_key";
    public static final String PASS_KEY ="pass_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.mail);
        mPass = findViewById(R.id.password);

        SharedPreferences preferences=getSharedPreferences(MainActivity.FILE_NAME,MODE_PRIVATE);
        String email=preferences.getString(MainActivity.EMAIL_KEY,"Default Email");
        String pass=preferences.getString(MainActivity.PASS_KEY,"Default Pass");

        mEmail.setText(email);
        mPass.setText(pass);

    }

    public void signIn(View view) {

        String mail= mEmail.getText().toString();
        String pass= mPass.getText().toString();

        Intent intent= new Intent(LoginActivity.this,MainActivity.class);
        intent.putExtra(EMAIL_KEY,mail);
        intent.putExtra(PASS_KEY,pass);

        startActivity(intent);
        finish();

    }
}