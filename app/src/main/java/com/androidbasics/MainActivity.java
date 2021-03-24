package com.androidbasics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyTag";
    private static final String MESSAGE_KEY = "message_key";
    private ScrollView mScrollView;
    private TextView nLog;
    private ProgressBar mProgressBar;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        nLog.setText(R.string.dummy_txt);

        mHandler=new Handler(getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {

                String data= msg.getData().getString(MESSAGE_KEY);

                displayProgressBar(false);

                Toast.makeText(getApplicationContext(),"HandleMessage : "+ data,Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void runCode(View view) {

//      Code To Send Message To UI Thread
        sendData();

/*

//        Below Code For Creating Custom Thread

        log("Running Code");
        displayProgressBar(true);

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"run: Download Starting...");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.d(TAG,"run: Download Finished..."+Thread.currentThread().getName());

//                displayProgressBar(false);        // Commented Because Except UI Thread Any Thread Cannot Access View
            }
        };

        Thread thread=new Thread(myRunnable);
//        thread.run();        // Called At UI Thread
        thread.setName("Download Thread");
        thread.start();
*/

/*

//        Below Whole Code For Handling Main(UI) Thread Message Queue Using Handler

        log("Running Code");
        displayProgressBar(true);

//        Try To Solve Blocking Call Success

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this," Hola! myRunnable Runs",Toast.LENGTH_LONG).show();
                displayProgressBar(false);
            }
        };

        Handler handler=new Handler();
//        postDelayed Release UI Thread To Do Their Work & After 4 sec Execute  myRunnable
//        postDelayed  delay execution of work packet for specified time and then executes it, that's why we delayed our task without UI Block
        handler.postDelayed(myRunnable,4000);

*/


/*
//         Try To Solve Blocking Call  but failed

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

//      Blocking Call   Because it call In UI Thread

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Handler handler=new Handler();

//  post method puts work packet at the end of the work queue.But does not delays its execution that's why we again faces UI BLOCKING

        handler.post(runnable);
*/



        /*      Blocking Call     */
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    private void sendData(){



        log("Running Code");
        displayProgressBar(true);

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"run: Download Starting...");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /*  we use This Because We Cannot use Views in Background Thread so that we notify after work completed    */

                Message msg=new Message();
                Bundle bundle=new Bundle();
                bundle.putString(MESSAGE_KEY,"Download Finished");
                msg.setData(bundle);
                mHandler.sendMessage(msg);
            }
        };

        Thread thread=new Thread(myRunnable);
//        thread.run();        // Called At UI Thread
        thread.setName("Download Thread");
        thread.start();

    }

    private void log(String message) {
        Log.i(TAG, message);
        nLog.append(message + "\n");
        scrollTextToEnd();
    }

    public void clearCode(View view) {
        nLog.setText("");
        scrollTextToEnd();
    }

    private void scrollTextToEnd() {
        mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

    }

    public void displayProgressBar(boolean display) {
        if (display) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void initView() {
        mScrollView = findViewById(R.id.scroll_view);
        nLog = findViewById(R.id.txt_view);
        mProgressBar = findViewById(R.id.pro_bar);
    }
}