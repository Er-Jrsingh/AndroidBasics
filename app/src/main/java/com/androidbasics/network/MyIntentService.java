package com.androidbasics.network;

import android.app.IntentService;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.androidbasics.model.CityItem;
import com.androidbasics.utility.HttpHelper;
import com.androidbasics.utility.RequestPackage;
import com.google.gson.Gson;

public class MyIntentService extends IntentService {
    public static final String SERVICE_PAYLOAD = "service_payload";
    public static final String SERVICE_MESSAGE = "service_message";
    public static final String SERVICE_EXCEPTION = "service_exception";
    public static final String SERVICE_REQUEST_PACKAGE = "service_request_package";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        RequestPackage requestPackage = intent.getParcelableExtra(SERVICE_REQUEST_PACKAGE);
        String data;
        try {
            data = HttpHelper.downloadUrl(requestPackage);
        } catch (Exception e) {
            e.printStackTrace();
            sendMessageToUi(e);
            return;
        }

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
        Intent intent = new Intent(SERVICE_MESSAGE);
        intent.putExtra(SERVICE_PAYLOAD, data);
        LocalBroadcastManager.getInstance(this)
                .sendBroadcast(intent);
    }
}