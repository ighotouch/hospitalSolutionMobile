package com.adroits.smartmedcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button viewBtn = (Button) findViewById(R.id.viewBtn);
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "Become a Product Manager | Learn the Skills &amp; Get the Job");

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });


//        SimpleDraweeView avatar = (SimpleDraweeView) findViewById(R.id.banner);
//                avatar.setImageURI("https://academy.elearninga-z.com/lms/www/market/lesson/1455808219.jpg");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
