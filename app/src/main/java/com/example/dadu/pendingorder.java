package com.example.dadu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dadu.adapter.OrderAdapter;
import com.example.eyespecsapp.models.Order;
import com.example.eyespecsapp.utils.OrderDatabaseHelper;

import java.util.List;

public class pendingorder extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;
    private OrderDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new OrderDatabaseHelper(this);

        // Fetching orders for the single user from SQLite
        orderList = dbHelper.getPendingOrders();

        orderAdapter = new OrderAdapter(orderList, this);
        recyclerView.setAdapter(orderAdapter);
    }
}
