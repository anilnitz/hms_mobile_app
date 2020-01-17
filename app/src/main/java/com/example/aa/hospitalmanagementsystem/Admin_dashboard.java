package com.example.aa.hospitalmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_dashboard extends AppCompatActivity {
    Button b3,btn9,btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        b3= (Button) findViewById(R.id.btn3);
        btn9=(Button) findViewById(R.id.btn9);
        btn6=(Button) findViewById(R.id.btn6);

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(Admin_dashboard.this,Home.class);
                startActivity(i2);
                finish();
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(Admin_dashboard.this,Admin_appointment_list.class);
                startActivity(i2);
                finish();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_dashboard.this,Add_doctor.class);
                startActivity(intent);
            }
        });
    }
}
