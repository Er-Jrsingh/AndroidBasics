package com.androidbasics;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class BackgroundThread extends Thread {

    private static final String TAG = "MyTag";
    public DownloadHandler mHandler;

//    private final String songName;

/*
    public BackgroundThread(String songName) {
        this.songName = songName;
    }
*/

    @Override
    public void run() {

//        It Creates Looper & Message Queue For Current Thread(Here BackgroundThread)
        Looper.prepare();
//         It Creates Handler For Current Thread(Here BackgroundThread)
        mHandler=new DownloadHandler();
//        It Loops The Message Queue  Created By Looper.prepare().if we give multiple task it Gives 1 task at a time to handler
        Looper.loop();
/*

//        downloadSong();
//      We Don't Use This Because Here We directly access the PlayList.songs at Background Thread
        for (String song:PlayList.songs) {
            downloadSong(song);
        }
*/

    }

/*

    private void downloadSong(String songName) {

        Log.d(TAG, "downloadSong: "+ songName +" Download Starting...");

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "downloadSong: " + songName +" Download Completed...");

    }
*/

/*
    private void downloadSong() {

        Log.d(TAG, "downloadSong: "+ songName +" Download Starting...");

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "downloadSong: " + songName +" Download Completed...");

    }
 */
}
