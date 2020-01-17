package com.example.aa.hospitalmanagementsystem;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Admin_appointment_list extends AppCompatActivity {
    ListView listView;

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    String url;
    String jsonData;
    // Define tags

    public static  final String TAG_RESULT = "result";
    public static  final String TAG_ID = "id";
    public static  final String TAG_USERNAME = "doctorSpecialization";
    public static  final String TAG_EMAIL = "consultancyFees";
    public static  final String TAG_PASSWORD = "appointmentTime";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_appointment_list);
        listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<String>();
        url = "https://anil1.techsunware.org/loginapp/getdata.php";
        new Admin_appointment_list.DataProcess().execute();
    }
    class DataProcess extends AsyncTask<String,String,String> {

        ProgressDialog pd;


        @Override
        protected void onPreExecute() {

            pd = new ProgressDialog(Admin_appointment_list.this);
            pd.setMessage("Dowloading...");
            pd.show();

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-type","application/json");

            InputStream inputStream = null;
            String result = null;

            try{

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity entity = httpResponse.getEntity();

                inputStream = entity.getContent();

                //json is UTF-8 by default

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream,"UTF-8"),8);
                StringBuilder sb = new StringBuilder();
                String line  = null;

                while((line = reader.readLine()) != null){

                    sb.append(line+"\n");
                }
                result = sb.toString();
            }catch (Exception e){


            } finally {

                try{
                    if(inputStream != null)
                        inputStream.close();

                }catch (Exception e){}
            }
            return result;
        }


        @Override
        protected void onPostExecute(String s) {

            jsonData = s;
            showRecord();
            pd.dismiss();
            super.onPostExecute(s);
        }
    }

    public void showRecord() {

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            arrayList.add("Id" +"\t\t\t" +"Type" + "\t\t\t" + "UserId" + "\t\t\t" + "AppintDate");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);
                String id = obj.getString("id");
                String userName = obj.getString("doctorSpecialization");
                String email = obj.getString("consultancyFees");
                String AppointDate = obj.getString("appointmentTime");

                arrayList.add(id +"\t\t\t" +userName + "\t\t\t" + email + "\t\t\t" + AppointDate);
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
            }

            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();



        }catch (Exception e){}
    }
}
