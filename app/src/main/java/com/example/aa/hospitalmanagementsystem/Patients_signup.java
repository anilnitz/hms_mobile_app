package com.example.aa.hospitalmanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Patients_signup extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6;
    Button btn1,btn2;
    String gender;
    CheckBox c1;
    RadioButton r1,r2;
    View progess;
    /*String url ="https://ducats.000webhostapp.com/setdata.php";*/
    String url="http://anil1.techsunware.org/loginapp/register.php";
    String fullname,address,city,email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_signup);
        e1 = (EditText) findViewById(R.id.edit1);
        e2 = (EditText) findViewById(R.id.edit2);
        e3 = (EditText) findViewById(R.id.edit3);
        e4= (EditText) findViewById(R.id.edit4);
        e5= (EditText) findViewById(R.id.edit5);
        e6=(EditText) findViewById(R.id.edit6);
        btn1 = (Button) findViewById(R.id.btn1);
        r1= (RadioButton) findViewById(R.id.radio1);
        r2= (RadioButton) findViewById(R.id.radio2);
        c1= (CheckBox) findViewById(R.id.checkBox);
        btn2 = (Button) findViewById(R.id.btn2);
        progess = findViewById(R.id.progess);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = e1.getText().toString();
                String pp = e2.getText().toString();
                String qq = e3.getText().toString();
                String rr = e4.getText().toString();
                String ss = e5.getText().toString();
                String tt = e6.getText().toString();
                //String uu = e7.getText().toString();
                if (name.equals("")) {

                    e1.setError("Please Enter Name");
                }
                else if (pp.equals("")) {
                    e2.setError("Please Enter Password");
                }
                else if (qq.equals("")) {
                    e3.setError("Please Enter Password");
                }
                else if (rr.equals("")) {
                    e4.setError("Please Enter Password");
                }
                else if (ss.equals("")) {
                    e5.setError("Please Enter Password");
                }
                else if(!tt.equals(ss))
                {
                    e6.setError("Password do not match");
                }
                else {
                    if(r1.isChecked())
                    {
                        gender = r1.getText().toString();
                        gender = "male";
                    }
                    else if(r2.isChecked())
                    {
                        gender = r2.getText().toString();
                        gender = "female";
                    }
                    fullname = e1.getText().toString();
                    address = e2.getText().toString();
                    city = e3.getText().toString();
                    email = e4.getText().toString();
                    password = e5.getText().toString();

                    callNetwork(fullname,address,city,gender,email,password);
                }

            }

        });

     btn2.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent=new Intent(Patients_signup.this,Patients.class);
             startActivity(intent);
             finish();
         }
     });

    }


    private void callNetwork(final String f, final String s,final String t,final String u,final String v,final String w)
    {
        progess.setVisibility(View.VISIBLE);
        RequestQueue requestQueue= Volley.newRequestQueue(Patients_signup.this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsobject= null;
                String res="";
                try {
                    jsobject = new JSONObject(response);
                    res = jsobject.getString("response");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (res.equalsIgnoreCase("ok")){
                    progess.setVisibility(View.GONE);
                    Toast.makeText(Patients_signup.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Patients_signup.this,Patients_Dashboard.class);
                    startActivity(intent);
                    finish();
                }
                else if (res.equalsIgnoreCase("exist")){
                    progess.setVisibility(View.GONE);
                    Toast.makeText(Patients_signup.this, "Email already register", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    progess.setVisibility(View.GONE);
                    Toast.makeText(Patients_signup.this, "Id or Password is Wrong", Toast.LENGTH_SHORT).show();
                }
                System.out.println("<< "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progess.setVisibility(View.GONE);
                System.out.println("<< "+error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> mymap=new HashMap<>();
                mymap.put("fullname",f);
                mymap.put("address",s);
                mymap.put("city",t);
                mymap.put("gender",u);
                mymap.put("email",v);
                mymap.put("password",w);
                return mymap;
            }
        };
        requestQueue.add(stringRequest);
    }




//    class Dataprocess extends AsyncTask<String,String,String> {
//
//        ProgressDialog pd;
//
//        @Override
//        protected void onPreExecute() {
//
//            pd = new ProgressDialog(Patients.this);
//            pd.setMessage("Loding...");
//            pd.show();
//
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//
//            try {
//
//                ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//
//                params.add(new BasicNameValuePair("user_name", first));
//                params.add(new BasicNameValuePair("user_password", second));
//
//                DefaultHttpClient httpClient = new DefaultHttpClient();
//
//                HttpPost httpPost = new HttpPost(url);
//
//                httpPost.setEntity(new UrlEncodedFormEntity(params));
//                httpClient.execute(httpPost);
//
//            }catch (Exception e){}
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//
//            pd.dismiss();
//            e1.setText("");
//            e2.setText("");
//
//           // Toast.makeText(Patients.this,"Successfully registered",Toast.LENGTH_LONG).show();
//
//           // Intent intent = new Intent(Patients.this,Patients_Dashboard.class);
//           // startActivity(intent);
//            super.onPostExecute(s);
//        }
//
//    }
}










