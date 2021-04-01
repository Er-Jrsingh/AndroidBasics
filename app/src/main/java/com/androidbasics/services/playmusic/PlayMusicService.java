package com.androidbasics.services.playmusic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.androidbasics.MainActivity;
import com.androidbasics.R;

//           Play Music in Bound Service

public class PlayMusicService extends Service {

    private static final String TAG = "MyTag";
    private final Binder mBinder = new MyServiceBinder();
    public static final String MUSIC_COMPLETE = "music_complete";
    private MediaPlayer mPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = MediaPlayer.create(this, R.raw.ritviz);
//        Help To Behave When Song Play Finish
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent(MUSIC_COMPLETE);
                intent.putExtra(MainActivity.MESSAGE_KEY, "done");
                LocalBroadcastManager.getInstance(getApplicationContext())
                        .sendBroadcast(intent);
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public String getValue() {
        return "Data From Service...";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayer.release();
    }

//      public client methods

    public boolean isPlaying() {
//        isPlaying method is sending data from Bound Service to MainActivity
        return mPlayer.isPlaying();
    }

    public void play() {
        mPlayer.start();
    }

    public void pause() {
//        Play & Pause methods are sending data from MainActivity to Bound Service
        mPlayer.pause();
    }

    public class MyServiceBinder extends Binder {
        public PlayMusicService getService() {
            return PlayMusicService.this;
        }
    }
}
