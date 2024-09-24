package com.example.dadu;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

public class Maininterface extends AppCompatActivity {

        private ImageView langimg;
        private ImageView pendingimg;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_maininterface);

        langimg=findViewById(R.id.Imagepro);
        pendingimg=findViewById(R.id.pendingpro);

        langimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to open the LanguageActivity
                Intent intent = new Intent(Maininterface.this , LanguageActivity.class);
                startActivity(intent);
            }
        });

        pendingimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to open the LanguageActivity
                Intent intent = new Intent(Maininterface.this , PendingOrdersActivity.class);
                startActivity(intent);
            }
        });

    }
}