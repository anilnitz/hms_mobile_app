package com.example.aa.hospitalmanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class Add_doctor extends AppCompatActivity {

    Spinner sp;
    String course[];
    EditText e2,e3,e4,e5,e6,e7,e8;
    CheckBox c1;
    Button btn1,btn2;


    String url="http://anil1.techsunware.org/loginapp/adddoctor.php";
    String name,address,fee,conno,email,password,spe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        e2= (EditText) findViewById(R.id.edit2);
        e3= (EditText) findViewById(R.id.edit3);
        e4= (EditText) findViewById(R.id.edit4);
        e5= (EditText) findViewById(R.id.edit5);
        e6= (EditText) findViewById(R.id.edit6);
        e7= (EditText) findViewById(R.id.edit7);
        e8= (EditText) findViewById(R.id.edit8);
        c1= (CheckBox) findViewById(R.id.checkBox);
        btn1= (Button) findViewById(R.id.btn1);
        btn2= (Button) findViewById(R.id.btn2);
        sp= (Spinner) findViewById(R.id.spinners);

        course=getResources().getStringArray(R.array.jadu);
        ArrayAdapter<String> adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,course);
        sp.setAdapter(adapter);

        btn1.setEnabled(false);


        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btn1.setEnabled(isChecked);
            }
        });



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //String name =.getText().toString();
                String pp = e2.getText().toString();
                String qq = e3.getText().toString();
                String rr = e4.getText().toString();
                String ss = e5.getText().toString();
                String tt = e6.getText().toString();
                String uu = e7.getText().toString();
                String vv = e8.getText().toString();
                String item=sp.getSelectedItem().toString();
                if (pp.equals("")) {
                    e2.setError("Please Enter Name");
                }
                else if (qq.equals("")) {
                    e3.setError("Please Enter City");
                }
                else if (rr.equals("")) {
                    e4.setError("Please Enter City");
                }
                else if (ss.equals("")) {
                    e5.setError("Please Enter Email");
                }
                else if (tt.equals("")) {
                    e6.setError("Please Enter Password");
                }

                else if(uu.equals(""))
                {
                    e7.setError("Please Enter the Password");
                }
                else if(!vv.equals(uu))
                {
                    e8.setError("Password do not match");
                }


                else
                {
                    spe=sp.getSelectedItem().toString();
                     /*userName = e1.getText().toString();*/
                    name = e2.getText().toString();
                /*email = e2.getText().toString();*/
                    address = e3.getText().toString();
                /*pass = e3.getText().toString();*/
                     fee= e4.getText().toString();
                    conno= e5.getText().toString();

                    email=e6.getText().toString();
                    password=e7.getText().toString();



                    new Dataprocess().execute();

                }








            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Add_doctor.this,Admin_dashboard.class);
                startActivity(intent);
            }
        });




    }
    class Dataprocess extends AsyncTask<String,String,String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {

            pd = new ProgressDialog(Add_doctor.this);
            pd.setMessage("Uploading...");
            pd.show();

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {


            try {

                ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
                /*params.add(new BasicNameValuePair("userName", userName));*/
                params.add(new BasicNameValuePair("spe", spe));
                params.add(new BasicNameValuePair("name", name));
              /*  params.add(new BasicNameValuePair("email", email));*/
                params.add(new BasicNameValuePair("address", address));
              /*  params.add(new BasicNameValuePair("password", pass));*/
                params.add(new BasicNameValuePair("fee", fee));
                params.add(new BasicNameValuePair("conno", conno));
                params.add(new BasicNameValuePair("email", email));
                params.add(new BasicNameValuePair("password", password));


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
            e2.setText("");
            e3.setText("");
            e4.setText("");
            e5.setText("");
            e6.setText("");
            e7.setText("");
            e8.setText("");
            Toast.makeText(Add_doctor.this,"Successfully registered",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Add_doctor.this,Admin_dashboard.class);
            startActivity(intent);
            super.onPostExecute(s);
        }

    }
}
