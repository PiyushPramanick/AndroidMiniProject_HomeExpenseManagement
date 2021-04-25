package com.example.homeexpensemanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences s =getSharedPreferences("MyPREFS", Context.MODE_PRIVATE);
        String Name=s.getString("name","");
        if(Name.isEmpty()) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);


                }
            }, 5000);
        }
        else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), Upload_Data.class);

                    startActivity(i);


                }
            }, 5000);
        }


    }
}