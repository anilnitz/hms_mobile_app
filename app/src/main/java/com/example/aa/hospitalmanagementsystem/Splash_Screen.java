package com.example.aa.hospitalmanagementsystem;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash_Screen extends AppCompatActivity {

    ImageView imageView;
    Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        imageView= (ImageView) findViewById(R.id.imageView);
        //animation= AnimationUtils.loadAnimation(this,R.anim.first);
        // animation= AnimationUtils.loadAnimation(this,R.anim.second);
        //animation= AnimationUtils.loadAnimation(this,R.anim.third);
        //animation= AnimationUtils.loadAnimation(this,R.anim.fourth);
        animation= AnimationUtils.loadAnimation(this,R.anim.fifth);

        imageView.setAnimation(animation);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent intent=new Intent(Splash_Screen.this,Home.class);
                startActivity(intent);
                finish();

            }
        },5000);

    }
}