package com.example.dadu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FindReportsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomerAdapter customerAdapter;
    private ArrayList<Customer> allCustomers; // Original customer list
    private ArrayList<Customer> filteredCustomers;
    private ImageButton imgbtn;// Filtered customer list (for searching)

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_reports);

        // Initialize RecyclerView and search input
        recyclerView = findViewById(R.id.customerRecyclerView);  // Fixed the ID reference to match RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        imgbtn=findViewById(R.id.imageButton4);

        EditText searchInput = findViewById(R.id.searchInput);

        // Initialize customer list
        allCustomers = new ArrayList<>(getCustomerList()); // Get the list of all customers
        filteredCustomers = new ArrayList<>(allCustomers); // Initially show all customers

        // Initialize adapter with the filtered customers list
        customerAdapter = new CustomerAdapter(filteredCustomers);
        recyclerView.setAdapter(customerAdapter);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FindReportsActivity.this,Maininterface.class);
                startActivity(intent);
            }
        });

        // Add a listener to the search input to filter the list dynamically
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString()); // Filter the list when the input changes
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed
            }
        });
    }

    // Method to filter the list of customers based on input
    private void filter(String text) {
        // Clear the filtered list first
        filteredCustomers.clear();

        // If no text, show all customers again
        if (text.isEmpty()) {
            filteredCustomers.addAll(allCustomers);
        } else {
            // Otherwise, search through the customer list
            for (Customer customer : allCustomers) {
                if (customer.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredCustomers.add(customer);  // Add matching customer
                }
            }
        }

        // Notify the adapter that the data has changed
        customerAdapter.notifyDataSetChanged();
    }

    // Method to return a list of customers (replace this with your actual data)
    private List<Customer> getCustomerList() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("MaheshBhai Patel", "Specs - Model X", "2024-08-01"));
        customers.add(new Customer("Jasheshbhai Patel", "Specs - Model Y", "2024-07-15"));
        customers.add(new Customer("Namanbhai patel", "Lenses - Vision Z", "2024-09-05"));
        customers.add(new Customer("Prakshbhai Dube", "Specs - Model P", "2024-08-11"));
        customers.add(new Customer("Dipeshbhai Puniwala", "Specs - Model K", "2024-07-05"));
        customers.add(new Customer("jaimin shah", "Lenses - Vision S", "2024-09-19"));
        customers.add(new Customer("Dhruval Mehta", "Specs - Model U", "2024-08-23"));
        customers.add(new Customer("Jeetbhai Gandhi", "Specs - Model R", "2024-07-22"));
        customers.add(new Customer("Parth Kosamiya", "Lenses - Vision A", "2024-09-30"));

        return customers;
    }
}
