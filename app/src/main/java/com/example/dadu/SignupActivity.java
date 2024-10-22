package com.example.dadu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dadu.fragments.DatePickerFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, retypePasswordEditText, birthdateEditText, hintEditText;
    private Button signupButton;
    private TextView haveAnAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        emailEditText = findViewById(R.id.EditText1);
        passwordEditText = findViewById(R.id.EditText2);
        retypePasswordEditText = findViewById(R.id.signup_retype_password);
        birthdateEditText = findViewById(R.id.signup_birthdate);
        hintEditText = findViewById(R.id.Edithint);
        signupButton = findViewById(R.id.signup_button);
        haveAnAccount = findViewById(R.id.haveanAccount);

        // Date picker
        birthdateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerDialog = new DatePickerFragment(birthdateEditText);
                datePickerDialog.show(getSupportFragmentManager(), "Date picker dialog");
            }
        });

        // Navigate to login
        haveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Sign Up button click listener
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String retypePassword = retypePasswordEditText.getText().toString().trim();
                String birthdate = birthdateEditText.getText().toString().trim();
                String hint = hintEditText.getText().toString().trim();

                // Input validation
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignupActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignupActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(retypePassword)) {
                    Toast.makeText(SignupActivity.this, "Please re-type your password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(birthdate)) {
                    Toast.makeText(SignupActivity.this, "Please enter your birthdate", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(retypePassword)) {
                    Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidPassword(password)) {
                    Toast.makeText(SignupActivity.this, "Invalid password! Must contain a symbol, capital letter, and a number.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(hint)) {
                    Toast.makeText(SignupActivity.this, "Please enter a hint", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Save user data in SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email", email);
                editor.putString("password", password);
                editor.putString("birthdate", birthdate);
                editor.putString("hint", hint);
                editor.apply();

                Toast.makeText(SignupActivity.this, "Sign Up successful!", Toast.LENGTH_SHORT).show();

                // Redirect to MainActivity or another activity
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Password validation method
    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*[A-Z])(?=.*[a-z]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
