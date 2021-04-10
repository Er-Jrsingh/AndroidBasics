package com.androidbasics;

import android.app.IntentService;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

//          Create Intent Service for Network Request

public class MyIntentService extends IntentService {
    public static final String SERVICE_PAYLOAD = "service_payload";
    public static final String SERVICE_MESSAGE = "service_message";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sendMessageToUi("Hola Dummo");
    }

    private void sendMessageToUi(String data) {
        Intent intent = new Intent(SERVICE_MESSAGE);                            //  SERVICE_MESSAGE is intent filter used to filter the service intent for Local broadcast receiver
        intent.putExtra(SERVICE_PAYLOAD, data);                                   //  SERVICE_PAYLOAD is intent extra key used for getting data from the intent
        LocalBroadcastManager.getInstance(this)
                .sendBroadcast(intent);
    }
}