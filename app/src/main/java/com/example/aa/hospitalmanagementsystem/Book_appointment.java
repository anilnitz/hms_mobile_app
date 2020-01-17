package com.example.aa.hospitalmanagementsystem;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Calendar;

import static android.R.attr.format;

public class Book_appointment extends AppCompatActivity {
    Spinner sp;
    String course[];
    EditText edit2,edit3,edit4,edit5,edituser,editdoc;
    Button btn1,btn2;

    String url="http://anil1.techsunware.org/loginapp/bookappointment.php";

    String name,fee,dd,tt,spe,docid,userid,userstatus,doctorstatus;
    SharedPreferences pref;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        edit2= (EditText) findViewById(R.id.edit2);
        edit3= (EditText) findViewById(R.id.edit3);
        edit4= (EditText) findViewById(R.id.edit4);
        edit5= (EditText) findViewById(R.id.edit5);
        btn1= (Button) findViewById(R.id.btn1);
        btn2=(Button) findViewById(R.id.btn2);
        edituser= (EditText) findViewById(R.id.edituser);
        editdoc= (EditText) findViewById(R.id.editdoc);
        sp= (Spinner) findViewById(R.id.spinners);
        pref = getSharedPreferences("jadu", Context.MODE_PRIVATE);
        editor=pref.edit();

        String i=pref.getString("id_key",null);
        edituser.setText(i);




        edit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar ca=Calendar.getInstance();
                DatePickerDialog dp=new DatePickerDialog(Book_appointment.this,date,ca.get(Calendar.YEAR),ca.get(Calendar.MONTH),ca.get(Calendar.DAY_OF_MONTH));

                dp.setCancelable(false);
                dp.show();
          /*new DatePickerDialog(MainActivity.this,null,2018,6,4).show();*/

            }
        });

        edit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar ca=Calendar.getInstance();
                new TimePickerDialog(Book_appointment.this,time,ca.get(Calendar.HOUR_OF_DAY),ca.get(Calendar.MINUTE),false).show();

            }
        });

        course=getResources().getStringArray(R.array.jadu);
        ArrayAdapter<String> adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,course);
        sp.setAdapter(adapter);



                sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        spe=parent.getSelectedItem().toString();
                        if (spe.equals("General Physician")) {
                            edit2.setText("Amrita");
                            edit3.setText("2400");
                            editdoc.setText("6");

                        }
                       else if (spe.equals("Dermatologist")) {
                            edit2.setText("Anuj");
                            edit3.setText("500");
                            editdoc.setText("1");

                        }
                        else if (spe.equals("Homeopath")) {
                            edit2.setText("Sarita Pandey");
                            edit3.setText("600");
                            editdoc.setText("2");

                        }
                        else if (spe.equals("Ayurveda")) {
                            edit2.setText("Sanjeev");
                            edit3.setText("8050");
                            editdoc.setText("5");

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // String name = e1.getText().toString();
                String pp = edit2.getText().toString();
                String qq = edit3.getText().toString();
                String rr = edit4.getText().toString();
                String ss = edit5.getText().toString();


                if (pp.equals("")) {
                    edit2.setError("Please Enter Name");
                }
                else if (qq.equals("")) {
                    edit3.setError("Please select Fees");
                }
                else if (rr.equals("")) {
                    edit4.setError("Please Enter Date");
                }
                else if (ss.equals("")) {
                    edit5.setError("Please Enter Time");
                }

                else
                {
                    userstatus="1";
                    doctorstatus="1";
                    userid=edituser.getText().toString();
                    docid=editdoc.getText().toString();

                    spe=sp.getSelectedItem().toString();

                    name = edit2.getText().toString();

                    fee = edit3.getText().toString();

                    dd = edit4.getText().toString();

                    tt=edit5.getText().toString();

                    new Dataprocess().execute();

                }


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Book_appointment.this,Patients_Dashboard.class);
                startActivity(intent);
                finish();
            }
        });



    }
    DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            edit4.setText(year +"/"+(month+1)+"/"+ dayOfMonth);

        }
    };

    TimePickerDialog.OnTimeSetListener time=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            edit5.setText(hourOfDay+" : "+minute );
        }
    };





    class Dataprocess extends AsyncTask<String,String,String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {

            pd = new ProgressDialog(Book_appointment.this);
            pd.setMessage("Uploading...");
            pd.show();

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {


            try {

                ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("spe",spe));


                params.add(new BasicNameValuePair("name",docid));
                params.add(new BasicNameValuePair("user",userid));

                params.add(new BasicNameValuePair("fee", fee));

                params.add(new BasicNameValuePair("dd", dd));
                params.add(new BasicNameValuePair("tt", tt));
                params.add(new BasicNameValuePair("userstatus", userstatus));
                params.add(new BasicNameValuePair("doctorstatus", doctorstatus));
                DefaultHttpClient httpClient = new DefaultHttpClient();

                HttpPost httpPost = new HttpPost(url);

                httpPost.setEntity(new UrlEncodedFormEntity(params));
                httpClient.execute(httpPost);

            }catch (Exception e){}

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            pd.dismiss();
            edit2.setText("");
            edit3.setText("");
            edit4.setText("");
            edit5.setText("");

            Toast.makeText(Book_appointment.this,"Successfully Booked",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Book_appointment.this,Patients_Dashboard.class);
            startActivity(intent);
            super.onPostExecute(s);
        }

    }
}