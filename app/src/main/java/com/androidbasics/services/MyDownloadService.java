package com.androidbasics.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.androidbasics.DownloadThread;
import com.androidbasics.MainActivity;

//    This Is Started Service Because onBind Return Null,By Default It Always Run On UI Thread So That Screen Face Drop Frames(Screen Freeze),After Close App It Still Run On Background
//     Here We Create Separate Background Thread So Screen Not Blocked,After Close App It Still Run On Background for Always So We Must Have To Write Stop Code
//     Here We Implement The Lifecycle Of Service,Here We Can See That onDestroy & onBind Not Called Even after Closing App
//     Here We Stop The Service Using stopService() At clearCode Method(Clear Code Button) Of MainActivity,Here We can see that onDestroy is Called on Clear Button Pressed & Still downloadSong : Download Finished Called Because Bg Thread is still Running
//     Here We Set up Worker Thread, Handler & Looper in Started Service(followed Production Ready Code Approach)


public class MyDownloadService extends Service {

    private static final String TAG = "MyTag";
    private DownloadThread mDownloadThread;

    public MyDownloadService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate : " + " onCreate Called");

        mDownloadThread=new DownloadThread();
        mDownloadThread.start();

        /*  Trick to prevent null Handler But it is Not Best Approach so use AsyncTask    */
        while (mDownloadThread.mHandler==null){}

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "onStartCommand : " + " onStartCommand Called");

        Log.d(TAG, "Thread Name : " + Thread.currentThread().getName());

        final String songName = intent.getStringExtra(MainActivity.MESSAGE_KEY);

        Message message=Message.obtain();
        message.obj=songName;
        mDownloadThread.mHandler.sendMessage(message);


/*

//        Creating New Background Thread

        final String songName = intent.getStringExtra(MainActivity.MESSAGE_KEY);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                downloadSong(songName);

            }
        });

        thread.start();

        String songName=intent.getStringExtra(MainActivity.MESSAGE_KEY);
        downloadSong(songName);
*/

        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {

        Log.d(TAG, "onBind : " + " onBind Called");

        return null;
    }

/*

    private void downloadSong(String song) {
        Log.d(TAG, "downloadSong : " + song + " Download Started...");

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "downloadSong : " + song + " Download Finished ");
    }

*/

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy : " + " onDestroy Called");
    }
}