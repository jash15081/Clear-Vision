package com.example.dadu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delay of 5 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the Main Interface Activity
                Intent intent = new Intent(SplashActivity1.this, MainActivity.class);
                startActivity(intent);
                finish();  // Close SplashActivity
            }
        }, 5000);
    }
}
