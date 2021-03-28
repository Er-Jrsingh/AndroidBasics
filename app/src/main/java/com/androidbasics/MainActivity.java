package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.androidbasics.services.MyDownloadService;

//    Code(For Nougat(Api 25 & Lower) For Started Service (onBind Return Null),By Default It Run On UI Thread So That Screen Face Drop Frames(Screen Freeze)
//     Here We Stop The Service At clearCode Method(Clear Code Button) Of MainActivity
//     Here We Set up Worker Thread, Handler & Looper in Started Service(followed Production Ready Code Approach)
//     use Async Task in Started Service



public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyTag";
    public static final String MESSAGE_KEY = "message_key";
    private TextView nLog;
    private ScrollView mScrollView;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void runCode(View view) {

        log("Song Downloading Start... ");
        displayProgressBar(true);

//        Send Intent To MyDownloadService.onStartCommand

        for (String song : Playlist.songs) {

            Intent intent = new Intent(MainActivity.this, MyDownloadService.class);
            intent.putExtra(MESSAGE_KEY, song);
            startService(intent);

        }
    }


    public void clearCode(View view) {

        Intent intent=new Intent(MainActivity.this,MyDownloadService.class);
        stopService(intent);

        nLog.setText("");
        scrollTextToEnd();
    }

    public void displayProgressBar(boolean display) {
        if (display) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void scrollTextToEnd() {
        mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    public void log(String message) {
        Log.i(TAG, message);
        nLog.append("\n" + message + "\n");
        scrollTextToEnd();
    }

    private void initView() {
        mScrollView = findViewById(R.id.scroll_view);
        nLog = findViewById(R.id.txt_view);
        mProgressBar = findViewById(R.id.pro_bar);
    }
}