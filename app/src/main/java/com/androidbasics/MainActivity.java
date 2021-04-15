package com.androidbasics;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidbasics.model.MyWebService;
import com.androidbasics.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//      Simple GET request with Retrofit, @GET Request

public class MainActivity extends AppCompatActivity {

    private TextView nLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void runCode(View view) {
        MyWebService myWebService = MyWebService.retrofit.create(MyWebService.class);
        Call<List<Post>> call = myWebService.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    for (Post post : response.body()) {
                        showPost(post);
                    }
                }
            }

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