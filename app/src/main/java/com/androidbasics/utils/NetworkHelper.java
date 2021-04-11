package com.androidbasics.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

//          Check Internet is Connected Or Not(Network Status)
public class NetworkHelper {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            return info != null && info.isConnectedOrConnecting();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
