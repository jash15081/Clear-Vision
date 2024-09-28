package com.example.dadu;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerProfileActivity extends AppCompatActivity {

    private TextView customerName, customerEmail, customerMobile, customerAddress,
            customerID, loyaltyPoints, ledgerID, outstandingBalance,
            eyeCapacityLeft, eyeCapacityRight;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        // Initialize views
        customerName = findViewById(R.id.customerName);
        customerEmail = findViewById(R.id.customerEmail);
        customerMobile = findViewById(R.id.customerMobile);
        customerAddress = findViewById(R.id.customerAddress);
        customerID = findViewById(R.id.customerID);
        loyaltyPoints = findViewById(R.id.loyaltyPoints);
        ledgerID = findViewById(R.id.ledgerID);
        outstandingBalance = findViewById(R.id.outstandingBalance);
        eyeCapacityLeft = findViewById(R.id.eyeCapacityLeft);
        eyeCapacityRight = findViewById(R.id.eyeCapacityRight);

        // Load customer data
        loadCustomerData();
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
            // Default case for other customers or new logins
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
