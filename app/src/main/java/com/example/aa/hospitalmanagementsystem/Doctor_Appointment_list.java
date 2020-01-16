package com.example.aa.hospitalmanagementsystem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.aa.hospitalmanagementsystem.R.drawable.abc;
import static com.example.aa.hospitalmanagementsystem.R.id.progess;
import static com.example.aa.hospitalmanagementsystem.R.id.recycler;
import static com.example.aa.hospitalmanagementsystem.R.id.spinners;

public class Doctor_Appointment_list extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String url = "https://anil1.techsunware.org/loginapp/getdatas.php";
    RecyclerView recyclerView;
    /*ProgressBar progess;*/
ArrayList<Doctor_appointment_Model> doctor_appointment_lists;

    ListView listView;


    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment_list);
        /*progess = (ProgressBar) findViewById(R.id.progess);*/
        doctor_appointment_lists = new ArrayList<>();
        recyclerView= (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
   recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<String>();


        pref = getSharedPreferences("jadu", Context.MODE_PRIVATE);
        editor = pref.edit();

        String i = pref.getString("id_key", null);

        callNetwork(i);
    }

    private void callNetwork(final String ids) {
        /*progess.setVisibility(View.VISIBLE);*/
       final ProgressDialog abc=new ProgressDialog(this);
        abc.setMessage("Loading....");
        abc.show();
        RequestQueue requestQueue = Volley.newRequestQueue(Doctor_Appointment_list.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsobject = null;
                String res = "";
                abc.dismiss();
                try {
                    jsobject = new JSONObject(response);
                    res = jsobject.getString("result");
                    if (res.equalsIgnoreCase("ok")) {

                        JSONArray jsonArray = jsobject.getJSONArray("data");


                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject obj = jsonArray.getJSONObject(i);

                                String Type = obj.getString("Type");
                                String userId = obj.getString("userId");
                                String appointmentDate = obj.getString("appointmentDate");

                                doctor_appointment_lists.add(new Doctor_appointment_Model(Type,userId,appointmentDate));


                               // adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
                            }
                        /*progess.setVisibility(View.GONE);*/
setAdapter();
//                            listView.setAdapter(adapter);
//                            adapter.notifyDataSetChanged();



                        /*progess.setVisibility(View.GONE);*/

                    } else {
                        /*progess.setVisibility(View.GONE);*/
                        /*progess.setVisibility(View.GONE);*/
                        /*Toast.makeText(Doctors_profile.this, "Id or Password is Wrong", Toast.LENGTH_SHORT).show();*/
                    }
                } catch (JSONException e) {
                    abc.dismiss();
                   /* progess.setVisibility(View.GONE);*/
                    e.printStackTrace();
                }
                System.out.println("<< " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /*progess.setVisibility(View.GONE);*/
                abc.dismiss();
                System.out.println("<< " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> mymap = new HashMap<>();
                mymap.put("id", ids);


                return mymap;
            }
        };
        requestQueue.add(stringRequest);
    }

    private  void  setAdapter(){
        AppintmentList_Adapter appintmentList_adapter = new AppintmentList_Adapter(this,doctor_appointment_lists){

        };
        recyclerView.setAdapter(appintmentList_adapter);
    }

}