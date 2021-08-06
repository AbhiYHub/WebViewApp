package com.androstock.webviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        topAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_to_right_anim);
        bottomAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left_anim);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    imageView.setAnimation(topAnim);
                    textView.setAnimation(bottomAnim);
                    sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        myThread.start();
    }
}