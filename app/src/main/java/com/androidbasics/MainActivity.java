package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mShowSize;
    private boolean mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowSize = findViewById(R.id.textView);

    }

    public void showSize(View view) {
        ScreenUtils screenUtils = new ScreenUtils(this);
        mShowSize.setText(String.format("Width: %s , Height: %s", screenUtils.getHorizontalWidth(), screenUtils.getVerticalHeight()));

        if (screenUtils.getHorizontalWidth()>=620){
            mFragment=true;
            Toast.makeText(this,"Yup We Can Use 2 Fragments"+mFragment,Toast.LENGTH_LONG).show();
        }
        else {
            mFragment=false;
            Toast.makeText(this,"No We Can't Use 2 Fragments"+mFragment ,Toast.LENGTH_LONG).show();
        }
    }

}