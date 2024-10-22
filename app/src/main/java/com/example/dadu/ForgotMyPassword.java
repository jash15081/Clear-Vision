package com.example.dadu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotMyPassword extends AppCompatActivity {

    private EditText emailEditText, hintEditText;
    private Button verifyHintButton;
    private static final String TAG = "ForgotMyPassword"; // For logging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_my_password);

        emailEditText = findViewById(R.id.EditText1);
        hintEditText = findViewById(R.id.hintEditText);
        verifyHintButton = findViewById(R.id.verifyHintButton);

        // Set up click listener for verifying the hint
        verifyHintButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String enteredHint = hintEditText.getText().toString().trim();

            // Validate input fields
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(ForgotMyPassword.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(enteredHint)) {
                Toast.makeText(ForgotMyPassword.this, "Please enter your hint", Toast.LENGTH_SHORT).show();
                return;
            }

            // Retrieve stored hint from SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            String storedHint = sharedPreferences.getString("hint", "");

            // Log the stored hint for debugging
            Log.d(TAG, "Stored Hint: " + storedHint);
            Log.d(TAG, "Entered Hint: " + enteredHint);


            if (enteredHint.equalsIgnoreCase(storedHint)) { // Using equalsIgnoreCase for case-insensitive match

                Intent intent = new Intent(ForgotMyPassword.this, ResetPassword.class);
                intent.putExtra("email", email);
                startActivity(intent);
            } else {

                Toast.makeText(ForgotMyPassword.this, "The hint you entered is wrong.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
