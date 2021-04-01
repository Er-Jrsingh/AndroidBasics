package com.androidbasics.services.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;

//          Create & Bind to Bound Service in Android & Its Lifecycle

public class MusicPlayerService extends Service {

    private static final String TAG = "MyTag";
    private final Binder mBinder = new MyServiceBinder();

    @Override
    public void onCreate() {
        super.onCreate();
            Log.d(TAG, "onCreate : ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
                Log.d(TAG, "onStartCommand : ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
             Log.d(TAG, "onBind : ");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
                Log.d(TAG, "onUnbind : ");
        return super.onUnbind(intent);
    }

    public String getValue(){
        return "Data From Service...";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy : ");
    }

    public class MyServiceBinder extends Binder {
        public MusicPlayerService getService() {
            return MusicPlayerService.this;
        }
    }
}
