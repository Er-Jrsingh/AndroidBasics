package com.androidbasics;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidbasics.model.Comment;
import com.androidbasics.model.MyWebService;
import com.androidbasics.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//      Simple GET request with Retrofit, @GET Request
//      URL Manipulation with Retrofit, @Path, @Query, @QueryMap, @Url

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
//        getPosts();     //      Simple GET request with Retrofit, @GET Request
        getComments();      //URL Manipulation with Retrofit, @Path, @Query, @QueryMap, @Url
    }

    private void getComments() {
        Call<List<Comment>> callCom = mWebService.getComments(3, "id", "desc");
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
        nLog.append("User Id : " + post.getUserId() + "\n");
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
