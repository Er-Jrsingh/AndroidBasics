package com.androidbasics.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface MyWebService {
    String BASE_URL = "https://jsonplaceholder.typicode.com/";        //  Absolute url
    String FEED = "posts";        //   Relative url

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //    DAO (Data Access Object)
    @GET(FEED)
    Call<List<Post>> getPost();
}
