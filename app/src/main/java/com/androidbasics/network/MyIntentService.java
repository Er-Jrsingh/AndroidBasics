package com.androidbasics.network;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.androidbasics.model.CityItem;
import com.androidbasics.utils.HttpHelper;
import com.google.gson.Gson;

//          Create Intent Service for Network Request
//          Download JSON on Android with GET Request from Internet
//          Create POJO/Java Model Class from JSON
//          Authenticate REST API with Username & Password, HTTP Basic Auth
//          Show Downloaded JSON Data(POJO Objects) in Recycler View

public class MyIntentService extends IntentService {
    public static final String SERVICE_PAYLOAD = "service_payload";
    public static final String SERVICE_MESSAGE = "service_message";
    public static final String SERVICE_EXCEPTION = "service_exception";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Uri uri = intent.getData();
        String data;
        try {
//            data = HttpHelper.downloadUrl(uri.toString(), "root", "root");
            data = HttpHelper.downloadUrl(uri.toString());
        } catch (Exception e) {
            e.printStackTrace();
//            data = e.getMessage();
            sendMessageToUi(e);
            return;
        }
//       Convert JSON into POJO/Java Objects using GSON Library
        Gson gson = new Gson();
        CityItem[] cityItems = gson.fromJson(data, CityItem[].class);
        sendMessageToUi(cityItems);
    }

    private void sendMessageToUi(Exception e) {
        Intent intent = new Intent(SERVICE_MESSAGE);
        intent.putExtra(SERVICE_EXCEPTION, e.getMessage());
        LocalBroadcastManager.getInstance(this)
                .sendBroadcast(intent);
    }

    private void sendMessageToUi(CityItem[] data) {
        Intent intent = new Intent(SERVICE_MESSAGE);                            //  SERVICE_MESSAGE is intent filter used to filter the service intent for Local broadcast receiver
        intent.putExtra(SERVICE_PAYLOAD, data);                                   //  SERVICE_PAYLOAD is intent extra key used for getting data from the intent
        LocalBroadcastManager.getInstance(this)
                .sendBroadcast(intent);
    }
}