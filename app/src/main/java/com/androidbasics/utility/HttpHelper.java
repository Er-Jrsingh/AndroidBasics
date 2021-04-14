package com.androidbasics.utility;

import java.util.Map;
import java.util.Objects;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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
        Request.Builder requestBuilder = new Request.Builder()
                .url(address);

//            POST Request With OkHttp
        if (requestPackage.getMethod().equals("POST")) {
            MultipartBody.Builder bodyBuilder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
            Map<String, String> params = requestPackage.getParams();
            for (String key : params.keySet()) {
                bodyBuilder.addFormDataPart(key, params.get(key));
                RequestBody requestBody = bodyBuilder.build();
                requestBuilder.method(requestPackage.getMethod(), requestBody);
            }
        }

        Request request = requestBuilder.build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return Objects.requireNonNull(response.body()).string();
        } else {
            throw new Exception("Error : Got Response Code " + response.code());
        }

    }
}