package com.androidbasics;

import android.os.Looper;

public class DownloadThread extends Thread {
    public DownloadHandler mHandler;

    @Override
    public void run() {
        super.run();

        Looper.prepare();
        mHandler=new DownloadHandler();
        Looper.loop();
    }
}
