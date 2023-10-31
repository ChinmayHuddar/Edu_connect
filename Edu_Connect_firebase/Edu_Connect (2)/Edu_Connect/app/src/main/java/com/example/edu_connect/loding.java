package com.example.edu_connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class loding extends AppCompatActivity {
    //variables
    ImageView bg;
    TextView text;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loding);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bg = findViewById(R.id.background);
        text = findViewById(R.id.t1);
        lottieAnimationView = findViewById(R.id.anime);

        //setting time for trasnition and animation
        text.animate().translationY(-2500).setDuration(1000).setStartDelay(3000);
        bg.animate().translationY(-2500).setDuration(1000).setStartDelay(3000);
        lottieAnimationView.animate().translationY(1600).setDuration(2000).setStartDelay(2000);

        // we requires Handler to handle splash screen activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(loding.this,login.class));
                finish();
            }
        },4000);//<-reqiuires triggering point for the next activity



    }
}