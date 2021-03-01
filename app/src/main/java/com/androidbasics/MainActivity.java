package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    //    private TextView mShowSize;
//    private boolean mFragment;
    boolean mUsingFragment;
    TextView tv;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mShowSize = findViewById(R.id.textView);

        tv = findViewById(R.id.showText);
        ViewGroup viewGroup = findViewById(R.id.details_frag_container);
        mUsingFragment = (viewGroup != null);
        if (mUsingFragment) {
            tv.setText("Using Fragment side by side : True");
        } else {
            tv.setText("Using Fragment side by side : False");
        }

        FloatingActionButton fabB = findViewById(R.id.fab);
        fabB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetails();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void showDetails() {

        if (mUsingFragment) {
            DetailsFragment detailsFragment = new DetailsFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.details_frag_container, detailsFragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailsActivity.class);
            startActivity(intent);
        }

    }

//    public void showSize(View view) {
//        ScreenUtils screenUtils = new ScreenUtils(this);
//        mShowSize.setText(String.format("Width: %s , Height: %s", screenUtils.getHorizontalWidth(), screenUtils.getVerticalHeight()));
//
//        if (screenUtils.getHorizontalWidth()>=620){
//            mFragment=true;
//            Toast.makeText(this,"Yup We Can Use 2 Fragments"+mFragment,Toast.LENGTH_LONG).show();
//        }
//        else {
//            mFragment=false;
//            Toast.makeText(this,"No We Can't Use 2 Fragments"+mFragment ,Toast.LENGTH_LONG).show();
//        }
//    }

}