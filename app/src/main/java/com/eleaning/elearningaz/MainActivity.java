package com.eleaning.elearningaz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.eleaning.elearningaz.application.ElearnApplication;
import com.eleaning.elearningaz.utils.rest.model.user.User;
import com.eleaning.elearningaz.utils.rest.service.UserService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ElearnApplication.getComponent().inject(this);

        UserService userService =
                retrofit.create(UserService.class);


        Call<List<User>> call = userService.getUsersList();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d("success message",  response.message());
                Log.d("success ",  response.raw().toString());
                Log.d("success ",  call.request().toString());


                for(User eachUser : response.body()){
                    Log.d("success ",  eachUser.toString());
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("failure ", String.valueOf(call.isExecuted()));
            }
        });
    }
}
