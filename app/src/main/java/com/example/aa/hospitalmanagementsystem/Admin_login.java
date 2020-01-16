package com.example.aa.hospitalmanagementsystem;

import android.content.Intent;
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

public class Admin_login extends AppCompatActivity {


    Button login_btn;
    EditText email_et,pass_et;
    String url="http://anil1.techsunware.org/loginapp/adminlogin.php";
    String first,second;
    View progess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        progess = findViewById(R.id.progess);
        email_et= (EditText) findViewById(R.id.email_et);
        pass_et= (EditText) findViewById(R.id.pass_et);
        login_btn= (Button) findViewById(R.id.login_btn);



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



    }


    private void callNetwork(final String f, final String s)
    {
        progess.setVisibility(View.VISIBLE);

        RequestQueue requestQueue= Volley.newRequestQueue(Admin_login.this);
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
                    Intent intent = new Intent(Admin_login.this,Admin_dashboard.class);
                    startActivity(intent);
                    finish();
                }else {
                    progess.setVisibility(View.GONE);
                    Toast.makeText(Admin_login.this, "Id or Password is Wrong", Toast.LENGTH_SHORT).show();
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










