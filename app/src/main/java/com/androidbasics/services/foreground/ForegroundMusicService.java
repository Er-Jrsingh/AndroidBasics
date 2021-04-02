package com.androidbasics.services.foreground;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.androidbasics.MainActivity;
import com.androidbasics.R;
import com.androidbasics.constants.Constants;

//          Control Foreground Service from Notification(ignore playmusic,lifecycle,foreground.MyForegroundService Package)
public class ForegroundMusicService extends Service {

    private static final String TAG = "MyTag";
    private final Binder mBinder = new ForegroundMusicService.MyServiceBinder();
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

                stopForeground(true);
                stopSelf();                 //stopSelf() Stop the service,if it was previously started.This is the same as calling Context.stopService(intent) for this particular service
            }
        });
        Log.d(TAG, "onCreate : ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind : ");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind : ");
        return true;            //  if false  onStart called & onRebind not Called
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind : ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy : ");
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

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        switch (intent.getAction()) {
            case Constants.MUSIC_SERVICE_ACTION_PLAY: {
                play();
                break;
            }
            case Constants.MUSIC_SERVICE_ACTION_PAUSE: {
                pause();
                break;
            }
            case Constants.MUSIC_SERVICE_ACTION_STOP: {
                mPlayer.release();
                stopForeground(true);
                stopSelf();
            }
            case Constants.MUSIC_SERVICE_ACTION_START: {
                showMusicNotification();
                break;
            }
            default: {
                stopSelf();
            }
        }
        showMusicNotification();

        Log.d(TAG, "onStartCommand");
        return START_NOT_STICKY;
    }

    private void showMusicNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "ChannelId");

        Intent pIntent = new Intent(this, ForegroundMusicService.class);
        pIntent.setAction(Constants.MUSIC_SERVICE_ACTION_PLAY);
        PendingIntent playIntent = PendingIntent.getService(this, 100, pIntent, 0);

        Intent psIntent = new Intent(this, ForegroundMusicService.class);
        psIntent.setAction(Constants.MUSIC_SERVICE_ACTION_PAUSE);
        PendingIntent pauseIntent = PendingIntent.getService(this, 100, pIntent, 0);

        Intent sIntent = new Intent(this, ForegroundMusicService.class);
        sIntent.setAction(Constants.MUSIC_SERVICE_ACTION_STOP);
        PendingIntent stopIntent = PendingIntent.getService(this, 100, pIntent, 0);

        builder.setSmallIcon(R.drawable.ico)
                .setContentText("Song Is Playing Buddy")
                .setContentTitle("iMusic")
                .addAction(new NotificationCompat.Action(android.R.drawable.ic_media_play, "Play", playIntent))
                .addAction(new NotificationCompat.Action(android.R.drawable.ic_media_pause, "Pause", pauseIntent))
                .addAction(new NotificationCompat.Action(android.R.drawable.ic_media_ff, "Stop", stopIntent));
        Notification notification = builder.build();
        startForeground(1001, notification);
        Log.d(TAG, "Notification Called");
    }

    public class MyServiceBinder extends Binder {
        public ForegroundMusicService getService() {
            return ForegroundMusicService.this;
        }
    }
}
