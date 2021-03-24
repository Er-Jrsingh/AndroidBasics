package com.androidbasics;

import android.util.Log;

public class BackgroundThread extends Thread {
    private static final String TAG = "MyTag";
//    private final String songName;

/*
    public BackgroundThread(String songName) {
        this.songName = songName;
    }
*/

    @Override
    public void run() {

//        downloadSong();
        for (String song:PlayList.songs) {
            downloadSong(song);
        }

    }

    private void downloadSong(String songName) {

        Log.d(TAG, "downloadSong: "+ songName +" Download Starting...");

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "downloadSong: " + songName +" Download Completed...");

    }

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
