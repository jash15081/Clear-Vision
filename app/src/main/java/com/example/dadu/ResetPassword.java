package com.example.dadu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class ResetPassword extends AppCompatActivity {

    private EditText newPasswordEditText, confirmPasswordEditText;
    private MaterialButton submitButton;

    // SharedPreferences to store user data
    SharedPreferences sharedPreferences;
    static final String SHARED_PREF_NAME = "user_pref";
    private static final String KEY_PASSWORD = "password"; // Key to store password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // Initializing views
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        submitButton = findViewById(R.id.submit_button);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        // Set up the submit button click listener
        submitButton.setOnClickListener(v -> {
            String newPassword = newPasswordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            // Validate input fields
            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(ResetPassword.this, "Please enter both fields", Toast.LENGTH_SHORT).show();
            } else if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(ResetPassword.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                // Save the new password in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_PASSWORD, newPassword);  // Update the password
                editor.apply();  // Apply changes to SharedPreferences

                // Notify the user and navigate back to the main activity
                Toast.makeText(ResetPassword.this, "Password successfully reset", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ResetPassword.this, MainActivity.class);
                startActivity(intent);
                finish();  // Close ResetPassword activity
            }
        });
    }
}
