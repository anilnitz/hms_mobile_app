package com.example.aa.hospitalmanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Patients_profile extends AppCompatActivity {
    ImageView imageView;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText edit0,edit1,edit2,edit3,edit4,edit5;
    Button btn;
    View progess;
    String url1="http://anil1.techsunware.org/loginapp/doc1.png";
    String url="http://anil1.techsunware.org/loginapp/Patients_profile_update.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_profile);
        edit0= (EditText) findViewById(R.id.edit0);
        edit1= (EditText) findViewById(R.id.edit1);
        edit2= (EditText) findViewById(R.id.edit2);
        edit3= (EditText) findViewById(R.id.edit3);
        edit4= (EditText) findViewById(R.id.edit4);
        edit5= (EditText) findViewById(R.id.edit5);
        progess = findViewById(R.id.progess);
        btn= (Button) findViewById(R.id.btn);

        imageView= (ImageView) findViewById(R.id.imageView);
        pref = getSharedPreferences("jadu", Context.MODE_PRIVATE);
        editor=pref.edit();

        String i=pref.getString("id_key",null);
        String n=pref.getString("name_key",null);// null is for defualt value
        String p=pref.getString("address_key",null);
        String q=pref.getString("city_key",null);
        String r=pref.getString("gender_key",null);
        String s=pref.getString("email_key",null);
        /*edit0.setText(i);*/
        edit1.setText(n);
        edit2.setText(p);
        edit3.setText(q);
        edit4.setText(r);
        edit5.setText(s);

        /*Toast.makeText(Patients_profile.this, ""+n, Toast.LENGTH_SHORT).show();
        Toast.makeText(Patients_profile.this, ""+p, Toast.LENGTH_SHORT).show();*/

        Picasso.with(Patients_profile.this).load(url1).into(imageView);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edit0.getText().toString();
                String name = edit1.getText().toString();
                String add = edit2.getText().toString();
                String city = edit3.getText().toString();
                String gen = edit4.getText().toString();
                String email = edit5.getText().toString();

                callNetwork(id,name,add,city,gen,email);
            }
    });



}


    private void callNetwork(final String ids,final String fulls, final String adds,final String citys,final String genders,final String emails)
    {
        progess.setVisibility(View.VISIBLE);
        RequestQueue requestQueue= Volley.newRequestQueue(Patients_profile.this);
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
                    Intent intent = new Intent(Patients_profile.this,Patients_Dashboard.class);
                    startActivity(intent);
                    finish();
                }

                else {
                    progess.setVisibility(View.GONE);
                    Toast.makeText(Patients_profile.this, "Id or Password is Wrong", Toast.LENGTH_SHORT).show();
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
                mymap.put("id",ids);
                mymap.put("fullname",fulls);
                mymap.put("address",adds);
                mymap.put("city",citys);
                mymap.put("gender",genders);
                mymap.put("email",emails);
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










