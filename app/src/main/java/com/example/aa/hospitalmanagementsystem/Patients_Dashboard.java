package com.example.aa.hospitalmanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Patients_Dashboard extends AppCompatActivity {
    Button btn1,btn2,btn3,btn4;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients__dashboard);
        btn1= (Button) findViewById(R.id.btn1);
        btn2= (Button) findViewById(R.id.btn2);
        btn3= (Button) findViewById(R.id.btn3);
        btn4=(Button) findViewById(R.id.btn4);



        pref = getSharedPreferences("jadu", Context.MODE_PRIVATE);
        editor=pref.edit();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.clear();
                editor.commit();

                Intent i1=new Intent(Patients_Dashboard.this,Home.class);
                startActivity(i1);
                finish();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(Patients_Dashboard.this,Patients_profile.class);
                startActivity(i2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(Patients_Dashboard.this,Patients_Appointment_List.class);
                startActivity(i3);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4=new Intent(Patients_Dashboard.this,Book_appointment.class);
                startActivity(i4);
            }
        });
    }
}
