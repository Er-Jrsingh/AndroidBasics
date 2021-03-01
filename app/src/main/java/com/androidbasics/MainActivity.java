package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mShowSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowSize = findViewById(R.id.textView);

    }

    public void showSize(View view) {
        ScreenUtils screenUtils = new ScreenUtils(this);
        mShowSize.setText(String.format("Width: %s , Height: %s", screenUtils.getHorizontalWidth(), screenUtils.getVerticalHeight()));
    }
}