package com.example.aa.hospitalmanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import static com.example.aa.hospitalmanagementsystem.R.id.spinners;

public class Doctors_profile extends AppCompatActivity {

    Spinner sp;
    EditText edit0,edit1, edit2, edit3, edit4, edit5;
    String course[];
    View progess;
    Button btn;
    String ids;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String url = "http://anil1.techsunware.org/loginapp/Doctor_profile_update.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_profile);
        sp = (Spinner) findViewById(spinners);
        edit0= (EditText) findViewById(R.id.edit0);
        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
        edit3 = (EditText) findViewById(R.id.edit3);
        edit4 = (EditText) findViewById(R.id.edit4);
        edit5 = (EditText) findViewById(R.id.edit5);
        btn= (Button) findViewById(R.id.btn);
        progess = findViewById(R.id.progess);
        pref = getSharedPreferences("jadu", Context.MODE_PRIVATE);
        editor = pref.edit();
        course = getResources().getStringArray(R.array.jadu);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, course);
        sp.setAdapter(adapter);

        String i = pref.getString("id_key", null);
        String n = pref.getString("name_key", null);
        String p = pref.getString("specilization_key", null);
        String q = pref.getString("address_key", null);
        String r = pref.getString("docFees_key", null);
        String s = pref.getString("contactno_key", null);
        String t = pref.getString("docEmail_key", null);
        edit0.setText(i);
        /*spinners.setText(p);*/

        edit1.setText(n);
        edit2.setText(q);
        edit3.setText(r);
        edit4.setText(s);
        edit5.setText(t);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ids=edit0.getText().toString();
                String spi= sp.getSelectedItem().toString();
                String name = edit1.getText().toString();
                String add = edit2.getText().toString();
                String fee = edit3.getText().toString();
                String con = edit4.getText().toString();
                String email = edit5.getText().toString();
                callNetwork(ids, name, add, fee, con, email);
            }
        });


    }

    private void callNetwork(final String ids, final String fulls, final String adds, final String fee, final String con, final String emails) {
        progess.setVisibility(View.VISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(Doctors_profile.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsobject = null;
                String res = "";
                try {
                    jsobject = new JSONObject(response);
                    res = jsobject.getString("response");
                    if (res.equalsIgnoreCase("ok")) {
                        progess.setVisibility(View.GONE);
                        Intent intent = new Intent(Doctors_profile.this, Doctor_dashboard.class);
                        startActivity(intent);
                        finish();
                    } else {
                        progess.setVisibility(View.GONE);
                        Toast.makeText(Doctors_profile.this, "Id or Password is Wrong", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println("<< " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progess.setVisibility(View.GONE);
                System.out.println("<< " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> mymap = new HashMap<>();
                mymap.put("id", ids);

                mymap.put("fullname", fulls);
                mymap.put("address", adds);
                mymap.put("fees", fee);
                mymap.put("cont", con);
                mymap.put("email", emails);
                return mymap;
            }
        };
        requestQueue.add(stringRequest);
    }

}