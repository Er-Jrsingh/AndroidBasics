package com.androidbasics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ResultReceiver;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.androidbasics.services.MyDownloadService;

//     Send Data from Service to UI using Broadcast Receiver(Better Approach)


public class DownloadHandler extends Handler {

    private static final String TAG = "MyTag";
    public static final String SERVICE_MESSAGE = "serviceMessage";
    private MyDownloadService mDownloadService;
    //    private ResultReceiver mReceiver;
    private Context mContext;

    @Override
    public void handleMessage(@NonNull Message msg) {

        downloadSong(msg.obj.toString());

//        How to Stop Started Service with Stop Self & Stop Self Result
//        stopSelf() = Works same as MainActivity stopService(),Shutdown Service Completely even not completed processes
//        stopSelf(int startId) = Don't Redeliver If startId Containing Process Finished
//        stopSelfResult(int startId) = Works Same as stopSelf(int startId) & It Have Return Type Extra

        boolean result = mDownloadService.stopSelfResult(msg.arg1);

        Log.d(TAG, "handleMessage : Service Stop Result : " + result + " startId : " + msg.arg1);

        //     Send Data from Service to UI using Broadcast Receiver
        sendMessageToUi(msg.obj.toString());

/*
//     Update UI/Main Thread from Service using Result Receiver

        Bundle bundle = new Bundle();
        bundle.putString(MainActivity.MESSAGE_KEY, msg.obj.toString());
        mReceiver.send(MainActivity.RESULT_OK,bundle);
*/

    }

    private void sendMessageToUi(String s) {
        Intent intent = new Intent(SERVICE_MESSAGE);
        intent.putExtra(MainActivity.MESSAGE_KEY,s);

        //Local broadcast Reciever

        LocalBroadcastManager.getInstance(mContext)
                .sendBroadcast(intent);

    }

    private void downloadSong(String song) {
        Log.d(TAG, "downloadSong : " + song + " Download Started...");

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "downloadSong : " + song + " Download Finished ");

    }

    //     Stop Started Service with Stop Self & Stop Self Result

    public void setDownloadService(MyDownloadService mDownloadService) {
        this.mDownloadService = mDownloadService;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    /*
   public void setReceiver(ResultReceiver mReceiver) {
        this.mReceiver = mReceiver;
    }
*/
}
