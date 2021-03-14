package com.androidbasics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity {

    public static final String EMAIL_KEY = "email_key";
    public static final String PASS_KEY = "pass_key";
    public static final String FILE_NAME = "my_file";
    private static final String COLOR_KEY = "color";
    private SwitchCompat aSwitch;
    private View mParent;
    private Button mBtnSetting;

    /*      It Should Be Data Member Because It Is Strong Reference(Don't Destroy until Activity Exists) Where as local Listener is Weak Reference & Destroys With Activity      */
    private SharedPreferences.OnSharedPreferenceChangeListener mPrefListener;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*      Application Level(Named Preference Set)     */

        String email = getIntent().getStringExtra(LoginActivity.EMAIL_KEY);
        String pass = getIntent().getStringExtra(LoginActivity.PASS_KEY);
        TextView mTextVw = findViewById(R.id.tv_mail);

        mTextVw.setText("Your Email :- \n" + email + "\nPassword :- \n" + pass);
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();

        SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
        editor.putString(EMAIL_KEY, email);
        editor.putString(PASS_KEY, pass);
        editor.apply();

        Toast.makeText(this, "Stored At SharedPreference", Toast.LENGTH_LONG).show();

        /*      Activity Level(Default Preference Set)      */

        aSwitch = findViewById(R.id.switch_btn);
        mParent = findViewById(R.id.parent_layout);
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean isChecked = preferences.getBoolean(COLOR_KEY, false);
        mParent.setBackgroundColor(isChecked ? Color.DKGRAY : Color.WHITE);
        aSwitch.setChecked(isChecked);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor1.putBoolean(COLOR_KEY, isChecked);
                editor1.apply();
                mParent.setBackgroundColor(isChecked ? Color.DKGRAY : Color.WHITE);
            }
        });

        /*      Activity Preference(Default Preference Set) Valid At Application Level    */

        mBtnSetting = findViewById(R.id.settings_btn);
        mBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);

                startActivity(intent);
                finish();
            }
        });


        /*      Activity Preference(Default Preference Set)  Attach Listener to Shared Preference File     */

        mPrefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals("user_name")) {
                    String uName = sharedPreferences.getString(key, "Not Found");
                    Toast.makeText(getApplicationContext(), uName, Toast.LENGTH_SHORT).show();

                }
            }
        };

        //  Register with DefaultSharedPreferences File
        SharedPreferences settingPref = PreferenceManager.getDefaultSharedPreferences(this);
        settingPref.registerOnSharedPreferenceChangeListener(mPrefListener);

    }

    public void signOut(View view) {

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);

        startActivity(intent);
        finish();
    }
}