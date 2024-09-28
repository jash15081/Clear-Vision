package com.example.dadu;

import static com.example.dadu.R.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText retypePasswordEditText;
    private EditText birthdateEditText;
    private EditText nameEditText;
    private EditText mobileNumberEditText;
    private EditText addressEditText;
    private EditText customerIDEditText;
    private EditText loyaltyPointsEditText;
    private EditText ledgerIDEditText;
    private EditText outstandingBalanceEditText;
    private EditText leftEyeCapacityEditText;
    private EditText rightEyeCapacityEditText;
    private Button signupButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.card_view), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        emailEditText = findViewById(id.EditText1);
        passwordEditText = findViewById(id.EditText2);
        retypePasswordEditText = findViewById(id.signup_retype_password);
        birthdateEditText = findViewById(id.signup_birthdate);

        signupButton = findViewById(id.signup_button);

        // Set click listener for the Sign Up button
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String retypePassword = retypePasswordEditText.getText().toString().trim();
                String birthdate = birthdateEditText.getText().toString().trim();
                String name = nameEditText.getText().toString().trim();
                String mobileNumber = mobileNumberEditText.getText().toString().trim();
                String address = addressEditText.getText().toString().trim();
                String customerID = customerIDEditText.getText().toString().trim();
                String loyaltyPoints = loyaltyPointsEditText.getText().toString().trim();
                String ledgerID = ledgerIDEditText.getText().toString().trim();
                String outstandingBalance = outstandingBalanceEditText.getText().toString().trim();
                String leftEyeCapacity = leftEyeCapacityEditText.getText().toString().trim();
                String rightEyeCapacity = rightEyeCapacityEditText.getText().toString().trim();

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
                    Toast.makeText(SignupActivity.this, "Invalid password! It must contain at least one symbol, one capital letter, and one number.", Toast.LENGTH_LONG).show();
                    return;
                }

                // Save customer data to SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email", email);
                editor.putString("password", password);
                editor.putString("birthdate", birthdate);
                editor.putString("name", name);
                editor.putString("mobile_number", mobileNumber);
                editor.putString("address", address);
                editor.putString("customer_id", customerID);
                editor.putString("loyalty_points", loyaltyPoints);
                editor.putString("ledger_id", ledgerID);
                editor.putString("outstanding_balance", outstandingBalance);
                editor.putString("left_eye_capacity", leftEyeCapacity);
                editor.putString("right_eye_capacity", rightEyeCapacity);
                editor.apply();

                Toast.makeText(SignupActivity.this, "Sign Up successful!", Toast.LENGTH_SHORT).show();

                // Redirect to MainActivity or CustomerProfileActivity
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean isValidPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            return false;
        }

        String regex = "^(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*[A-Z])(?=.*[a-z]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
