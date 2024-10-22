package com.example.dadu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Name of the SharedPreferences file
    private static final String SHARED_PREF_NAME = "UserPrefs";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_LOGGED_IN = "logged_in";

    private Button loginButton;
    private EditText emailEditText, passwordEditText;
    private Button signupButton;
    private TextView forgotButton;
    private CheckBox rememberMeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        emailEditText = findViewById(R.id.EditText1);
        passwordEditText = findViewById(R.id.EditText2);
        loginButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.signup_button);
        rememberMeCheckBox = findViewById(R.id.CheckBox1);
        forgotButton = findViewById(R.id.forgot_password);

        // Access SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        // Check if user is already logged in
        boolean isLoggedIn = sharedPreferences.getBoolean(KEY_LOGGED_IN, false);
//        if (isLoggedIn) {
//            // Auto-login and skip the login screen if user is remembered
//            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
//            startActivity(intent);
//            finish();
//            return;
//        }

        // Handle "Forgot Password" button click
        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ForgotMyPassword.class);
                startActivity(intent);
            }
        });

        // Handle login button click
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validate input fields
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Fetch registered email and password from SharedPreferences
                String registeredEmail = sharedPreferences.getString(KEY_EMAIL, "");
                String registeredPassword = sharedPreferences.getString(KEY_PASSWORD, "");

                // Check if login credentials are correct
                if (email.equals(registeredEmail) && password.equals(registeredPassword)) {
                    // Save login state if "Remember Me" is checked
                    if (rememberMeCheckBox.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(KEY_LOGGED_IN, true); // Remember the user
                        editor.apply();
                    }

                    // Show success message and navigate to the SplashActivity
                    Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                    startActivity(intent);
                    finish(); // Close login activity
                } else {
                    // Show error message if credentials don't match
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
