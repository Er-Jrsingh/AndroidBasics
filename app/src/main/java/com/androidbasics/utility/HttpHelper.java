package com.androidbasics.utility;

import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHelper {

    public static String downloadUrl(RequestPackage requestPackage) throws Exception {

        String address = requestPackage.getEndPoint();
        String encodedParams = requestPackage.getEncodeParams();

        if (requestPackage.getMethod().equals("GET") && encodedParams.length() > 0) {
            address = String.format("%s?%s", address, encodedParams);
        }

//            GET Request With OkHttp
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder()
                .url(address);

        Request request = builder.build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return Objects.requireNonNull(response.body()).string();
        } else {
            throw new Exception("Error : Got Response Code " + response.code());
        }
    }
}