package com.example.aa.hospitalmanagementsystem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Doctor_dashboard extends AppCompatActivity {
    Button btn1,btn2,btn3;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);
        btn1= (Button) findViewById(R.id.btn1);
        btn2=(Button) findViewById(R.id.btn2);
        btn3=(Button) findViewById(R.id.btn3);

        pref = getSharedPreferences("jadu", Context.MODE_PRIVATE);
        editor=pref.edit();
        final ProgressDialog abc=new ProgressDialog(this);
        abc.setMessage("Loading...");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abc.show();
                editor.clear();
                editor.commit();

                Intent i1=new Intent(Doctor_dashboard.this,Home.class);
                startActivity(i1);
                finish();
                abc.dismiss();

            }
        });



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(Doctor_dashboard.this,Doctors_profile.class);
                startActivity(i2);


            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(Doctor_dashboard.this,Doctor_Appointment_list.class);
                startActivity(i3);

            }
        });
    }
}
