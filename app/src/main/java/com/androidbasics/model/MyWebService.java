package com.androidbasics.model;

//      POST Request with Retrofit, @Body, @FormUrlEncoded, @FieldMap
//     PUT,PATCH  Request with Retrofit, @PUT, @PATCH

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    //    URL Manipulation with Retrofit, @Path, @Query, @QueryMap, @Url
    //    Dynamic URL
//    @GET("posts/{id}/comments")
//    Call<List<Comment>> getComments(@Path("id") int userId);

    //    URL Manipulation with Retrofit, @Path, @Query, @QueryMap, @Url
    //    Query Parameter
//    @GET("comments?postId=1")
    @GET("comments")
//    Call<List<Comment>> getComments(@Query("postId") int postId);         // Single Query

    //    Call<List<Comment>> getComments(@Query("postId") int postid,           // Multiple Query
//                                    @Query("_sort") String sortBy,
//                                    @Query("_order") String orderBy
//    );

    Call<List<Comment>> getComments(@Query("postId") Integer[] ids,           // Multiple Variable In Single Query
                                    @Query("_sort") String sortBy,
                                    @Query("_order") String orderBy
    );

//    Call<List<Comment>> getComments(@QueryMap Map<String, String> params    );             // Map Query

    // Direct Url
//    @GET()
//    Call<List<Comment>> getComments(@Url String url);

    //      POST Request with Retrofit, @Body
    @POST("posts")
    Call<Post> createPost(@Body Post post);

    //    POST Request with Retrofit @FormUrlEncoded
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@Field("userId") int uId,
                          @Field("title") String title,
                          @Field("body") String text);

    //    POST Request with Retrofit @FieldMap
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> postMap);

    //  PUT  Request with Retrofit, @PUT
    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id, @Body Post post);

    //  PATCH  Request with Retrofit, @PATCH
    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, @Body Post post);
}
