package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyTag";
    private ScrollView mScrollView;
    private TextView nLog;
    private ProgressBar mProgressBar;
    private ExecutorService mExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mExecutor = Executors.newFixedThreadPool(5);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mExecutor.shutdown();

    }

    public void runCode(View view) {

        for (int i = 0; i < 10; i++) {
            Works works = new Works(i + 1);
            mExecutor.execute(works);
        }
    }


    public void clearCode(View view) {
        nLog.setText("");
        scrollTextToEnd();
    }

    private void scrollTextToEnd() {
        mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

//    public void log(String message) {
//        Log.i(TAG, message);
//        nLog.append(message + "\n");
//        scrollTextToEnd();
//    }


//    public void displayProgressBar(boolean display) {
//        if (display) {
//            mProgressBar.setVisibility(View.VISIBLE);
//        } else {
//            mProgressBar.setVisibility(View.INVISIBLE);
//        }
//    }

    private void initView() {
        mScrollView = findViewById(R.id.scroll_view);
        nLog = findViewById(R.id.txt_view);
        mProgressBar = findViewById(R.id.pro_bar);
    }
}