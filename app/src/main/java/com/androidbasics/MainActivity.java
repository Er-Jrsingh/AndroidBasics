package com.androidbasics;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidbasics.model.Comment;
import com.androidbasics.model.MyWebService;
import com.androidbasics.model.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//      Simple GET request with Retrofit, @GET Request
//      URL Manipulation with Retrofit, @Path, @Query, @QueryMap, @Url
//      POST Request with Retrofit, @Body
//      POST Request with Retrofit, @FormUrlEncoded
//      POST Request with Retrofit, @FieldMap
//      PUT  Request with Retrofit, @PUT

public class MainActivity extends AppCompatActivity {

    private TextView nLog;
    MyWebService mWebService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mWebService = MyWebService.retrofit.create(MyWebService.class);
    }

    public void runCode(View view) {
//        getPosts();                //    Simple GET request with Retrofit, @GET Request
//        getComments();       //    URL Manipulation with Retrofit, @Path, @Query, @QueryMap, @Url
//        createPost();            //   POST Request with Retrofit, @Body, @FormUrlEncoded, @FieldMap
        updatePost();              //  PUT  Request with Retrofit, @PUT

    }

    private void updatePost() {
        Post post = new Post(15, "New Title", "New Text");
        Call<Post> postCall = mWebService.putPost(5, post);
        postCall.enqueue(new Callback<Post>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    nLog.setText(getString(R.string.resp) + response.code());
                    showPost(response.body());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    private void createPost() {
        Post post = new Post(1, "Post Title", "This Is Post Body");
        Map<String, String> postMap = new HashMap<>();
        postMap.put("userId", "33");
        postMap.put("title", "My Post Title");
        postMap.put("body", "This Is My Post Body");

//        Call<Post> postCall = mWebService.createPost(post);                                                                    //@Body
//        Call<Post> postCall = mWebService.createPost(3, "Post Title", "This Is Post Body");                       //@FormUrlEncoded
        Call<Post> postCall = mWebService.createPost(postMap);                                                                 //@FormUrlEncoded
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    nLog.setText(String.valueOf(response.code()));
                    showPost(response.body());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    private void getComments() {
        Map<String, String> params = new HashMap<>();
        params.put("postId", "5");
        params.put("_sort", "email");
        params.put("_order", "desc");
//        Call<List<Comment>> callCom = mWebService.getComments(3, "id", "desc");
//        Call<List<Comment>> callCom = mWebService.getComments(params);
//        Call<List<Comment>> callCom = mWebService.getComments("https://jsonplaceholder.typicode.com/posts/13/comments");
        Call<List<Comment>> callCom = mWebService.getComments(new Integer[]{2, 4, 6, 8}, null, null);
        callCom.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()) {
                    showComments(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }

    private void showComments(List<Comment> body) {
        for (Comment comment : body) {
            nLog.append("Id : " + comment.getId() + "\n");
            nLog.append("PostId : " + comment.getPostId() + "\n");
            nLog.append("User : " + comment.getName() + "\n");
            nLog.append("Email : " + comment.getEmail() + "\n");
            nLog.append("Body : " + comment.getBody() + "\n\n");
        }
    }


    private void getPosts() {
//      Simple GET request with Retrofit, @GET Request Modified
        Call<List<Post>> call = mWebService.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    for (Post post : response.body()) {
                        showPost(post);
                    }
                }
            }

            /*
           //      Simple GET request with Retrofit, @GET Request Old
                          MyWebService myWebService = MyWebService.retrofit.create(MyWebService.class);
                           Call<List<Post>> call = myWebService.getPost();
                           call.enqueue(new Callback<List<Post>>() {
                               @Override
                               public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                                   if (response.isSuccessful()) {
                                       for (Post post : response.body()) {
                                       showPost(post);
                                 }
           }*/
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });
    }

    private void showPost(Post post) {
        nLog.setTextColor(getResources().getColor(R.color.french_blue));
        nLog.append("\nUser Id : " + post.getUserId() + "\n");
        nLog.append("Id : " + post.getId() + "\n");
        nLog.append("Title : " + post.getTitle() + "\n");
        nLog.append("Body : " + post.getText() + "\n\n");
    }

    public void clearCode(View view) {
        nLog.setTextColor(getResources().getColor(R.color.barely_green));
        nLog.setText(R.string.welcome_to_restful_api_using_retrofit);
    }

    private void initView() {
        nLog = findViewById(R.id.tv);
    }
}
