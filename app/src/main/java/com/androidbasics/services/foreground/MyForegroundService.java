package com.androidbasics.services.foreground;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.androidbasics.R;

//          Create Foreground Service (with Notification) (ignore playmusic,lifecycle Package)
public class MyForegroundService extends Service {

    private static final String TAG = "MyTag";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        showNotification();
        
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"run : Starting Download");

                int i=0;
                while (i < 10){
                    Log.d(TAG, "run : Progress is : "+(i+1));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
                Log.d(TAG,"run : Download Completed ");

                stopForeground(true);
                stopSelf();
            }
        });
        thread.start();
        return START_STICKY;
    }

    private void showNotification() {
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this,"ChannelId");
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("This Is Service Notification")
                .setContentTitle("Song Downloading");
        Notification notification=builder.build();
        startForeground(1001,notification);
        Log.d(TAG,"Notification Called");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy : Called ");
    }
}

