package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidbasics.models.User;
import com.google.gson.Gson;

public class Registration extends AppCompatActivity {

    private static final String USER_KEY = "usr_key";
    Button mSaveBtn, mReadBtn;
    EditText mName, mAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mSaveBtn = findViewById(R.id.save_btn);
        mReadBtn = findViewById(R.id.read_btn);
        mName = findViewById(R.id.edt_uName);
        mAge = findViewById(R.id.edt_age);

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uName = mName.getText().toString();
                int age = Integer.valueOf(mAge.getText().toString());

                User user = new User(uName, age);

                Gson gson = new Gson();
                String jsonString = gson.toJson(user, User.class);

                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(Registration.this).edit();
                editor.putString(USER_KEY, jsonString);
                editor.apply();
                Toast.makeText(getApplicationContext(), "Saving Successfully", Toast.LENGTH_SHORT).show();

            }
        });

        mReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Registration.this);
                String val = preferences.getString(USER_KEY, "N/A");
                Gson gson = new Gson();
                User user = gson.fromJson(val, User.class);
                Toast.makeText(getApplicationContext(), "Name : " + user.getUserName() + " Age : " + user.getAge(), Toast.LENGTH_LONG).show();

            }
        });


    }
}