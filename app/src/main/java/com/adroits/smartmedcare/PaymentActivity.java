package com.adroits.smartmedcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.adroits.smartmedcare.utils.ClearableEditText;


public class PaymentActivity extends AppCompatActivity {
    ClearableEditText cardNumberTextBox = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Reg Payment");



    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PaymentActivity.this, ConsoleActivity.class);
        PaymentActivity.this.startActivity(intent);
    }
}
