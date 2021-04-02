package com.androidbasics;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.androidbasics.constants.Constants;
import com.androidbasics.services.foreground.ForegroundMusicService;
import com.androidbasics.services.foreground.MyForegroundService;

//          Create & Bind to Bound Service in Android & Its Lifecycle
//          Play Music in Bound Service (ignore lifecycle Package)
//          Use One Service as Bound & Started Service in Android
//          Create Foreground Service (with Notification) (ignore playmusic,lifecycle Package)
//          Control Foreground Service from Notification(ignore playmusic,lifecycle,foreground.MyForegroundService Package)

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyTag";
    public static final String MESSAGE_KEY = "message_key";
    private TextView nLog;
    private ScrollView mScrollView;
    private ForegroundMusicService mForegroundMusicService;
    private boolean mBound = false;
    private Button mPlayBtn;

    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        //        Called When Connection Is Established Between Service  & Activity
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            ForegroundMusicService.MyServiceBinder mServiceBinder = (ForegroundMusicService.MyServiceBinder) iBinder;
            mForegroundMusicService = mServiceBinder.getService();
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
        nLog.setText(R.string.header);
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra(MESSAGE_KEY);
            if (result == "done") {
                mPlayBtn.setText(R.string.play);
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity.this, ForegroundMusicService.class);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);

        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mReceiver, new IntentFilter(ForegroundMusicService.MUSIC_COMPLETE));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(mReceiver);
    }

    public void playBtn(View view) {
        if (mBound) {
            if (mForegroundMusicService.isPlaying()) {
                mForegroundMusicService.pause();
                mPlayBtn.setText(R.string.play);
            } else {
                Intent intent = new Intent(MainActivity.this, ForegroundMusicService.class);
                intent.setAction(Constants.MUSIC_SERVICE_ACTION_START);
                startService(intent);
                mForegroundMusicService.play();
                mPlayBtn.setText(R.string.pause);
            }
        }
    }

    public void runCode(View view) {
        //          Create Foreground Service (with Notification) (ignore playmusic,lifecycle Package)
        log("Music Downloading Buddy");
        Intent intent = new Intent(MainActivity.this, MyForegroundService.class);
        startService(intent);

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
        mPlayBtn = findViewById(R.id.play_btn);
    }
}