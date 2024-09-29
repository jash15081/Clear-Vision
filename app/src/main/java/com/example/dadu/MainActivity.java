package com.example.dadu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText emailEditText, passwordEditText;
    private Button signupButton;
    private CheckBox rememberMeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.EditText1);
        passwordEditText = findViewById(R.id.EditText2);
        loginButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.signup_button);
        rememberMeCheckBox = findViewById(R.id.CheckBox1);

        // Access SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("logged_in", false);

        // Auto-login if user is already logged in
        if (isLoggedIn) {
            Intent intent = new Intent(MainActivity.this, Maininterface.class);
            startActivity(intent);
            finish();
            return;
        }

        // Handle login button click
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validate input
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Fetch registered credentials from SharedPreferences
                String registeredEmail = sharedPreferences.getString("email", "");
                String registeredPassword = sharedPreferences.getString("password", "");

                // Check if login is successful
                if (email.equals(registeredEmail) && password.equals(registeredPassword)) {
                    if (rememberMeCheckBox.isChecked()) {
                        // Save login state if "Remember Me" is checked
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("logged_in", true);
                        editor.apply();
                    }
                    // Show success message and navigate to the SplashActivity
                    Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                    startActivity(intent);
                    finish(); // Close login activity
                } else {
                    // Show error if credentials don't match
                    Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle signup button click
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SignupActivity
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
