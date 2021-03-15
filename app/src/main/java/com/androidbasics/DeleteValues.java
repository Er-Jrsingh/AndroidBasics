package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteValues extends AppCompatActivity {

    private static final String FILE_NAME = "my_prefs_file_del_val";
    private static final String NAME_KEY = "names";
    private static final String AGE_KEY = "ages";
    private Button mBtnSave, mBtnRead;
    private EditText mUName, mAge;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.OnSharedPreferenceChangeListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_values);

        mBtnSave = findViewById(R.id.save_btn_delAct);
        mBtnRead = findViewById(R.id.read_btn_delAct);
        mUName = findViewById(R.id.edt_uName_delAct);
        mAge = findViewById(R.id.edt_age_delAct);

        mListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                /*  Here We Can Write Logic To Redirect The User At Login Activity(if Not Sign In) or Homepage(if already signed in)  */

                switch (key) {
                    case NAME_KEY: {
                        String name = sharedPreferences.getString(key, "N/A");
                        Toast.makeText(getApplicationContext(), "Listener-Name : " + name, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case AGE_KEY: {
                        int age = sharedPreferences.getInt(key, 0);
                        Toast.makeText(getApplicationContext(), "Listener-Age : " + age, Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        };

        sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(mListener);


        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mUName.getText().toString();
                int age = Integer.parseInt(mAge.getText().toString());

                SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
                editor.putString(NAME_KEY, name);
                editor.putInt(AGE_KEY, age);
                editor.apply();

                Toast.makeText(getApplicationContext(), "Data Written Successfully ", Toast.LENGTH_SHORT).show();

            }
        });

        mBtnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
                String name = preferences.getString(NAME_KEY, "N/A");
                int age = preferences.getInt(AGE_KEY, 0);

                Toast.makeText(getApplicationContext(), "Your Name : " + name + " Your Age : " + age, Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void removeFile(View view) {

        SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
        editor.remove(AGE_KEY);
        editor.apply();

        Toast.makeText(getApplicationContext(), "Deleted Successfully ", Toast.LENGTH_SHORT).show();
    }

    public void removeAll(View view) {

        /*Here Listener Do Not Fire because before apply() clear() remove All Appearances */
        /*before clear() Should Store All Data Into Map,Array,List For Future Reference  */

        SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();

        Toast.makeText(getApplicationContext(), "Deleted All Successfully ", Toast.LENGTH_SHORT).show();

    }
}