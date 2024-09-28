package com.example.dadu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import android.widget.CheckBox;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private Button loginButton;
    private EditText emailEditText, passwordEditText;
    private Button signupButton;

    private CheckBox rememberMeCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.card_view), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailEditText = findViewById(R.id.EditText1);
        passwordEditText = findViewById(R.id.EditText2);
        loginButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.signup_button);
        rememberMeCheckBox = findViewById(R.id.CheckBox1);

//        login information remember
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("logged_in", false);

        if (isLoggedIn) {
            Intent intent = new Intent(MainActivity.this, Maininterface.class);
            startActivity(intent);
            finish();
            return;
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();



                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                String registeredEmail = sharedPreferences.getString("email", "");
                String registeredPassword = sharedPreferences.getString("password", "");

                if (email.equals(registeredEmail) && password.equals(registeredPassword)) {
                    if (rememberMeCheckBox.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("logged_in", true);
                        editor.apply();
                    }
                    Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this , SplashActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Set click listener for the Sign Up button
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