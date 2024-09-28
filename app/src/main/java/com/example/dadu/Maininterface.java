package com.example.dadu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Maininterface extends AppCompatActivity {

    private ImageView langimg;
    private ImageView pendingimg;
    private ImageView findReportimg;
    private ImageView logoutimg;
    private ImageView profileimg;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maininterface);

        langimg = findViewById(R.id.Imagepro);
        pendingimg = findViewById(R.id.pendingpro);
        findReportimg = findViewById(R.id.findpro);
        logoutimg = findViewById(R.id.logoutpro);
        profileimg=findViewById(R.id.profilepro);


        langimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Maininterface.this, LanguageActivity.class);
                startActivity(intent1);
            }
        });

        pendingimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Maininterface.this, PendingOrdersActivity.class);
                startActivity(intent2);
            }
        });

        findReportimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Maininterface.this, FindReportsActivity.class);
                startActivity(intent3);
            }
        });

        profileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(Maininterface.this , CustomerProfileActivity.class);
                startActivity(intent4);
            }
        });
        logoutimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear login state in SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", false);  // Clear the login flag
                editor.apply();

                // Navigate to MainActivity (login screen)
                Intent intent4 = new Intent(Maininterface.this, MainActivity.class);
                intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);  // Clear back stack
                startActivity(intent4);
                finish();  // Close the current activity
            }
        });
    }
}
