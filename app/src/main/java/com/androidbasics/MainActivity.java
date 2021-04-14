package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidbasics.model.CityItem;
import com.androidbasics.utility.NetworkHelper;
import com.androidbasics.network.MyIntentService;
import com.androidbasics.utility.RequestPackage;

//      Make GET Request with Parameters using "OKHttp Library"
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyTag";
    private TextView mLog;
    private boolean isNetworkOk;

    //     10.0.2.2 is used to refers to local machine's localhost
//    using localhost or 127.0.0.1  refers to emulator s localhost
//    Using PC ip When Run On Real Device
//    Real Url :- https://jsonplaceholder.typicode.com/posts/1/comments
//    public static final String WEB_URL = "https://jsonplaceholder.typicode.com/posts/1/comments";

    public static final String JSON_URL_GET = "http://192.168.43.54/hamara_pyara_bharat_okhttp/json/itemsfeed.php";

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.hasExtra(MyIntentService.SERVICE_PAYLOAD)) {
                CityItem[] cityItems = (CityItem[]) intent.getParcelableArrayExtra(MyIntentService.SERVICE_PAYLOAD);
                for (CityItem item : cityItems) {
                    logOutput(item.getCityname());
                }
            } else if (intent.hasExtra(MyIntentService.SERVICE_EXCEPTION)) {
                String message = intent.getStringExtra(MyIntentService.SERVICE_EXCEPTION);

                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                logOutput(message);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        isNetworkOk = NetworkHelper.isNetworkAvailable(this);
    }

    public void runCode(View view) {
        if (isNetworkOk) {

            RequestPackage requestPackage = new RequestPackage();
            requestPackage.setEndPoint(JSON_URL_GET);
            requestPackage.setMethod("GET");
            requestPackage.setParams("state", "Chhattisgarh");

            Intent intent = new Intent(MainActivity.this, MyIntentService.class);
            intent.putExtra(MyIntentService.SERVICE_REQUEST_PACKAGE, requestPackage);
            startService(intent);

        } else {
            Toast.makeText(this, "Network Not Available ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver, new IntentFilter(MyIntentService.SERVICE_MESSAGE));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(mReceiver);
    }

    private void logOutput(String data) {
        Log.d(TAG, "logOutput : " + data);
        mLog.append("\n" + data);
    }

    private void initViews() {
        mLog = findViewById(R.id.txt_vw);
    }
}