package com.androidbasics;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class DownloadHandler extends Handler {

    private static final String TAG = "MyTag";
    private final MainActivity mActivity;

    public DownloadHandler(MainActivity activity) {

        this.mActivity =activity;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {

        downloadSong(msg.obj.toString());

    }

    private void downloadSong(final String songName) {

        Log.d(TAG, "downloadSong: "+ songName +" Download Starting...");

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivity.log("Download Finished "+songName);
                mActivity.displayProgressBar(false);
            }
        });

/*

//         Below Code Is Similar As Above Code , Actually Above Code Is Similar As Below Code Implicitly

        Handler handler=new Handler(Looper.getMainLooper());
        handler.post(new Runnable(){
            @Override
            public void run() {

                mMainActivity.log("Download Complete");
                mMainActivity.displayProgressBar(false);

            }});
*/

        Log.d(TAG, "downloadSong: " + songName +" Download Completed...");

    }
}
