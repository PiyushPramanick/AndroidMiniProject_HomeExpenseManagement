package com.example.homeexpensemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Upload_Data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__data);
        EditText E1 =(EditText)findViewById(R.id.editTextNumber);
        EditText E2=(EditText)findViewById(R.id.editTextTextPersonName2) ;
        Button b1=(Button)findViewById(R.id.button2);
        SharedPreferences s =getSharedPreferences("MyPREFS", Context.MODE_PRIVATE);
        String Name=s.getString("name","");
        String currentD = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cost=E1.getText().toString();
                String comment=E2.getText().toString();
                if(cost.isEmpty() && comment.isEmpty()){
                    Toast.makeText(Upload_Data.this,"No place should be left blank",Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog.Builder builder=new AlertDialog.Builder(Upload_Data.this);
                    builder.setMessage("Are you sure you want to upload the DATA you have entered \n \n"+"Money Spent- "+cost+"\n"+"Details- "+comment+"\n"+"Date- "+currentD).setPositiveButton("Upload", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            HashMap<String,Object> map=new HashMap<>();
                            map.put("Date",currentD);
                            map.put("Money Spent",cost);
                            map.put("Detail",comment);
                            map.put("Spent By",Name);
                            FirebaseDatabase.getInstance().getReference().child("Expenditure").push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Log.i("DataBase Message","Data has been sent");
                                    Toast.makeText(Upload_Data.this, "Data Uploaded Successfully "+cost+"  "+comment+"  "+currentD+"  "+Name, Toast.LENGTH_LONG).show();
                                    E1.setText("");
                                    E2.setText("");

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.i("DataBase Message","Data has not been sent "+e.toString());
                                    Toast.makeText(Upload_Data.this,"Data has Not been Uploaded",Toast.LENGTH_LONG).show();
                                }
                            });

                        }
                    }).setNegativeButton("Cancel",null);

                    AlertDialog alert=builder.create();
                    alert.show();


                }
                }
        });


    }
@Override
public void onBackPressed(){
 AlertDialog.Builder builder=new AlertDialog.Builder(Upload_Data.this);
 builder.setTitle("Really Exit ?");
 builder.setMessage("Are you sure you want to exit").setPositiveButton("OK", new DialogInterface.OnClickListener() {
     @Override
     public void onClick(DialogInterface dialog, int which) {
        Upload_Data.super.onBackPressed();
     }
 }).setNegativeButton("Cancel",null).setCancelable(false);
 AlertDialog alert=builder.create();
 alert.show();
}
}