package com.example.dadu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CustomerProfileActivity extends AppCompatActivity {

    private TextView customerName, customerEmail, customerMobile, customerAddress,
            customerID, loyaltyPoints, ledgerID, outstandingBalance,
            eyeCapacityLeft, eyeCapacityRight;

    private ImageButton cancelbtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize views
        customerName = findViewById(R.id.nameTextView);
        customerEmail = findViewById(R.id.emailTextView);
        customerMobile = findViewById(R.id.mobileTextView);
        customerAddress = findViewById(R.id.addressTextView);
        customerID = findViewById(R.id.customerIDTextView);
        loyaltyPoints = findViewById(R.id.loyaltyPointsTextView);
        ledgerID = findViewById(R.id.ledgerIDTextView);
        outstandingBalance = findViewById(R.id.outstandingBalanceTextView);
        eyeCapacityLeft = findViewById(R.id.leftEyeCapacityTextView);
        eyeCapacityRight = findViewById(R.id.rightEyeCapacityTextView);
        cancelbtn = findViewById(R.id.cancel_button4);
        // Load customer data
        loadCustomerData();

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerProfileActivity.this,Maininterface.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private void loadCustomerData() {
        // Assume we're using SharedPreferences to store customer info, you can replace this with your database query
        SharedPreferences sharedPreferences = getSharedPreferences("CustomerData", MODE_PRIVATE);

        // Load the customer info based on the logged-in email
        String email = sharedPreferences.getString("loggedInEmail", "");

        // Fetch data (this should be replaced with a database query in real implementation)
        if (email.equals("abc@example.com")) {
            customerName.setText("Kunj Puniwala");
            customerEmail.setText("abc@example.com");
            customerMobile.setText("+98124625602");
            customerAddress.setText("12 Street, Surat");
            customerID.setText("CUST1289");
            loyaltyPoints.setText("100");
            ledgerID.setText("LED5678");
            outstandingBalance.setText("250");
            eyeCapacityLeft.setText("Left Eye: 1.5");
            eyeCapacityRight.setText("Right Eye: 1.7");
        } else if (email.equals("xyz@example.com")) {
            customerName.setText("Jeet Gandhi");
            customerEmail.setText("xyz@example.com");
            customerMobile.setText("+9876543210");
            customerAddress.setText("456 Avenue, Baroda");
            customerID.setText("CUST5678");
            loyaltyPoints.setText("200");
            ledgerID.setText("LED1234");
            outstandingBalance.setText("500");
            eyeCapacityLeft.setText("Left Eye: 2.0");
            eyeCapacityRight.setText("Right Eye: 2.2");
        } else {

            customerName.setText("Unknown Customer");
            customerEmail.setText(email);
            customerMobile.setText("Not available");
            customerAddress.setText("Not available");
            customerID.setText("Not available");
            loyaltyPoints.setText("Not available");
            ledgerID.setText("Not available");
            outstandingBalance.setText("Not available");
            eyeCapacityLeft.setText("Not available");
            eyeCapacityRight.setText("Not available");
        }
    }
}
