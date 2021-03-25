package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*  AsyncTask Code Is Similar As ThreadConcept Branch Code Implicitly  */
    /*  AsyncTask Is Bound With Specific(Creator) Activity & Only Its Creator Activity Can Use It So That We Define it as Inner Class   */
    /*  This Code Have Some Bugs Like Memory Leak , Destroy Screen On Orientation Change but Task Still Run On Background(Because It Contain MainActivity Reference Implicitly ) and Memory Leak Occurs  */

    public static final String TAG="MyTag";
    public static final String MESSAGE_KEY="message_key";
    private ScrollView mScrollView;
    private TextView nLog;
    private ProgressBar mProgressBar;
    private MyAsyncTask mAsyncTask;
    private boolean mTaskRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
    }

    public void runCode(View view) {

//        log("Running Code..");
//        displayProgressBar(true);

        /*   Cancel a Running Async Task & Handle return Data   */

        if (mTaskRunning && mAsyncTask != null){

            mAsyncTask.cancel(true);
            mTaskRunning=false;

        }else{

        mAsyncTask = new MyAsyncTask();
        mAsyncTask.execute("Red","Green","Orange","Yellow","White");
        mTaskRunning=true;

        }

//        MyAsyncTask myAsyncTask=new MyAsyncTask();
//        myAsyncTask.execute("Red","Green","Orange","Yellow","White");
 /*
        myAsyncTask Can Execute Only one time Below Code Not Valid and Crash At RunTime But We Can Create Another MyAsyncTask obj and again call execute method
        myAsyncTask.execute("Red","Green","Orange","Yellow","White"),Every task Execute In Sequence mean myAsyncTask then myAsyncTask2 and so on
*/
//        MyAsyncTask myAsyncTask2=new MyAsyncTask();
//        myAsyncTask2.execute("Blue","Purple","Violate","Aqua","Brawn");

    }

    public void log(String message) {
        Log.i(TAG, message);
        nLog.append("\n"+message + "\n");
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

    private void initializeViews() {

        mScrollView=findViewById(R.id.scroll_view);
        nLog=findViewById(R.id.txt_view);
        mProgressBar=findViewById(R.id.pro_bar);
    }

    @SuppressLint("StaticFieldLeak")
    class MyAsyncTask extends AsyncTask<String,String,String> {

        /*  doInBackground runs in separate Background Thread & We Write All Operations Here & It Cant Access Views(UI) Directly     */

        @Override
        protected String doInBackground(String... strings) {

            for (String value:strings){

                if (isCancelled()){
                    publishProgress("Task Is Canceled..!!");
                    break;
                }

                Log.d(TAG,"doInBackground : "+value);

//        Send Message To UI Thread(This Value send to onProgressUpdate)
                publishProgress(value);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Download Completed... ";        /*  send to onPostExecute & onCancelled(String s)  */
        }

//        Below Methods Run On UI Thread
        @Override
        protected void onProgressUpdate(String... values) {

            log(values[0]);

        }

        @Override
        protected void onPostExecute(String s) {
            log(s);
        }

        @Override
        protected void onCancelled() {
            log("Cancelled..!!");
        }

        //        @Override
//        protected void onCancelled(String s) {
//            log(s);
//        }
    }
}