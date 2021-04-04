package com.androidbasics;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

//Job Scheduler API, Introduction & Creating a Job Service with Worker Thread

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyDownloadJob extends JobService {
    private static final String TAG = "MyTag";
    private boolean isJobCancelled = false;
    private boolean mSuccess = false;

    public MyDownloadJob() {
    }

    @Override
    public boolean onStartJob(JobParameters params) {

        Log.d(TAG, "onStartJob : called");
        Log.d(TAG, "onStartJob : Thread Name : " + Thread.currentThread().getName());

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                Log.d(TAG, "run : Download Started...");
                while (i < 10) {
                    if (isJobCancelled)
                        return;

                    Log.d(TAG, "run : Download Progress : " + (i + 1));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
                Log.d(TAG, "run : Download Finished");
                mSuccess = true;
                jobFinished(params, mSuccess);      //Must Call On Working Thread Not In Main Thraed . mSucess(boolean) Tells Job Will ReSchedule Or Not
            }
        }).start();
        return true;        //  true if we using separate background thread
    }

    //    Called when app is closed or intrrupt before calling jobFinished(params, mSuccess)
    @Override
    public boolean onStopJob(JobParameters params) {
        isJobCancelled = true;
        return true;        // tells Job ReScheduled Or Not after app crash
    }
}