package com.androidbasics;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

//Job Scheduler API, Introduction & Creating a Job Service with Worker Thread

public class MainActivity extends AppCompatActivity {

    private static final int JOB_ID = 1001;
    private static final String TAG = "MyTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //      this method Schedules the Job
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void scheduleService(View view) {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        ComponentName componentName = new ComponentName(this, MyDownloadJob.class);
        JobInfo jobInfo = new JobInfo.Builder(JOB_ID, componentName)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)     // NETWORK_TYPE_UNMETERED mean wifi
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setMinimumLatency(0)
                .setPersisted(true)
                .build();

        int result = jobScheduler.schedule(jobInfo);

        if (result == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "scheduleService : Job Scheduled");
        } else {
            Log.d(TAG, "scheduleService : Job Not Scheduled");
        }
    }

    //    This method cancel the scheduled job
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cancelService(View view) {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(JOB_ID);
        Log.d(TAG, "cancelService : Job Cancelled ");
    }
}