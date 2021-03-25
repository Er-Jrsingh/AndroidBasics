package com.androidbasics;

import android.util.Log;

public class Works implements Runnable {

    private static final String TAG = "MyTag";

    private int mThreadNo;

    public Works(int mThreadNo) {
        this.mThreadNo = mThreadNo;
    }


    @Override
    public void run() {
        Log.d(TAG,"RUN : " + Thread.currentThread().getName()+" Start,Thread No : "+mThreadNo);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(TAG,"RUN : " + Thread.currentThread().getName()+" End,Thread No : "+mThreadNo);

    }
}
