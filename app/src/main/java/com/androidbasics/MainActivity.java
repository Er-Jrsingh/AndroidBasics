package com.androidbasics;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.androidbasics.services.lifecycle.MusicPlayerService;

//          Create & Bind to Bound Service in Android & Its Lifecycle

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyTag";
    private TextView nLog;
    private ScrollView mScrollView;
    private MusicPlayerService mMusicPlayerService;
    private boolean mBound = false;


    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        //        Called When Connection Is Established Between Service  & Activity
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            MusicPlayerService.MyServiceBinder mServiceBinder = (MusicPlayerService.MyServiceBinder) iBinder;
            mMusicPlayerService = mServiceBinder.getService();
            mBound = true;

            Log.d(TAG, "onServiceConnected : ");
        }

        //        Called Only When Connection Is Interrupt Between Service  & Activity Unexpectedly
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;

            Log.d(TAG, "onServiceDisconnected : ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity.this, MusicPlayerService.class);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }

    public void runCode(View view) {
        log(mMusicPlayerService.getValue());
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

    public void log(String message) {
        Log.i(TAG, message);
        nLog.append("\n" + message + "\n");
        scrollTextToEnd();
    }

    private void initView() {
        mScrollView = findViewById(R.id.scroll_view);
        nLog = findViewById(R.id.txt_view);
    }
}