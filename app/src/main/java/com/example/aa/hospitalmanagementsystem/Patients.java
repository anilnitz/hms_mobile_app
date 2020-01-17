package com.example.aa.hospitalmanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Patients extends AppCompatActivity {
    Button login_btn,reg_btn;
    EditText email_et,pass_et;
    String url="http://anil1.techsunware.org/loginapp/login.php";
    String first,second;
    View progess;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);
        progess = findViewById(R.id.progess);
        email_et= (EditText) findViewById(R.id.email_et);
        pass_et= (EditText) findViewById(R.id.pass_et);
        login_btn= (Button) findViewById(R.id.login_btn);
        reg_btn=(Button) findViewById(R.id.reg_btn);


        pref = getSharedPreferences("jadu", Context.MODE_PRIVATE);
        editor=pref.edit();


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = email_et.getText().toString();
                String pp = pass_et.getText().toString();
                if (name.equals("")) {

                    email_et.setError("Please Enter Email");
                }
                else if (pp.equals("")) {
                    pass_et.setError("Please Enter Password");
                }
                first = email_et.getText().toString();
                second = pass_et.getText().toString();
                callNetwork(first,second);

            }
        });

       reg_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              Intent i=new Intent(Patients.this,Patients_signup.class);
                startActivity(i);
            }
       });

    }


    private void callNetwork(final String f, final String s)
    {
        progess.setVisibility(View.VISIBLE);
        RequestQueue requestQueue= Volley.newRequestQueue(Patients.this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsobject= null;
                String res="";
                try {
                    jsobject = new JSONObject(response);
                  res = jsobject.getString("response");


                    if (res.equalsIgnoreCase("ok")){
                        progess.setVisibility(View.GONE);
                        String Id=jsobject.getString("Id");
                        String Name=jsobject.getString("Name");
                        String Address=jsobject.getString("Address");
                        String City=jsobject.getString("City");
                        String Gender=jsobject.getString("Gender");
                        String Email=jsobject.getString("Email");
                        editor.putString("id_key",Id);
                        editor.putString("name_key",Name);
                        editor.putString("address_key",Address);
                        editor.putString("city_key",City);
                        editor.putString("gender_key",Gender);
                        editor.putString("email_key",Email);

                        editor.commit();

                      //  Toast.makeText(Patients.this, ""+Name, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Patients.this,Patients_Dashboard.class);
                   startActivity(intent);
                    finish();
                    }else {
                        progess.setVisibility(View.GONE);
                        Toast.makeText(Patients.this, "Id or Password is Wrong", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
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
                mymap.put("user_name",f);
                mymap.put("user_password",s);
                return mymap;
            }
        };
        requestQueue.add(stringRequest);
    }



}










