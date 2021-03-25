package com.androidbasics;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;

public class AsyncFragment extends Fragment {

    private MyTaskHandler mTaskHandler;
    public static final String TAG="MyTag";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    public void runTask(String...prams){
        MyAsyncTask mTask=new MyAsyncTask();
        mTask.execute(prams);


    }

    public interface MyTaskHandler{
        void handlerTak(String message);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MyTaskHandler){
            mTaskHandler = (MyTaskHandler) context;

        }
    }

    @SuppressLint("StaticFieldLeak")
    class MyAsyncTask extends AsyncTask<String,String,String> {

        /*  doInBackground runs in separate Background Thread & We Write All Operations Here & It Cant Access Views(UI) Directly     */

        @Override
        protected String doInBackground(String... strings) {

            for (String value : strings) {

                if (isCancelled()) {
                    publishProgress("Task Is Canceled..!!");
                    break;
                }

                Log.d(TAG, "doInBackground : " + value);

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

            mTaskHandler.handlerTak(values[0]);

        }

        @Override
        protected void onPostExecute(String s) {
            mTaskHandler.handlerTak(s);
        }

    }
}
