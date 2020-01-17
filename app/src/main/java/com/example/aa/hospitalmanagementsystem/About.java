package com.example.aa.hospitalmanagementsystem;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.item1)
        {
            Intent i1=new Intent(About.this,Home.class);
            startActivity(i1);
        }
        else if(id==R.id.item2)
        {
            Toast.makeText(this, "Welcome to About Us", Toast.LENGTH_SHORT).show();
        }

        else if(id==R.id.item3)
        {
            Intent intent=new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);
        }





        return super.onOptionsItemSelected(item);
    }
}

