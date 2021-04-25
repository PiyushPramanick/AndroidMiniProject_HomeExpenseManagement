package com.example.homeexpensemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences s=getSharedPreferences("MyPREFS", Context.MODE_PRIVATE);
        EditText name= (EditText)findViewById(R.id.editTextTextPersonName);
        Button b1=(Button)findViewById(R.id.Sub);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=name.getText().toString();

                if(Name.isEmpty()){
                 Toast.makeText(MainActivity.this,"Name Should not be blank",Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences.Editor editor = s.edit();
                    editor.putString("name", Name);
                    editor.commit();
                    Toast.makeText(MainActivity.this, "Name has been successfully saved", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), Upload_Data.class);
                    startActivity(i);
                }
                }
        });


    }
}