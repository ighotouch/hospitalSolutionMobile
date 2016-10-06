package com.adroits.smartmedcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.adroits.smartmedcare.application.ElearnApplication;
import com.adroits.smartmedcare.utils.rest.model.user.User;
import com.adroits.smartmedcare.utils.rest.service.UserService;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    TextView signInBtn;
    TextView registerBtn;

    @Inject
    Retrofit retrofit;
Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signInBtn = (TextView) findViewById(R.id.signInBtn);
        registerBtn = (TextView) findViewById(R.id.registerBtn);
realm = Realm.getDefaultInstance();
        ElearnApplication.getComponent().inject(this);
Log.d("database: ",realm.getPath());
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
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will4
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_course_catelog) {
            Intent intent = new Intent(MainActivity.this, CourseCatelogActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);


    }

}
