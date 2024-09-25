package com.example.dadu;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;

public class PendingOrdersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PendingOrderAdapter adapter;
    private List<Order> pendingOrders;
    private ImageButton cancelButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_orders);

        recyclerView = findViewById(R.id.rvPendingOrders);
        cancelButton1 = findViewById(R.id.cancel_button);

        cancelButton1.setOnClickListener(v -> finish());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Example data for pending orders
        pendingOrders = new ArrayList<>();
        pendingOrders.add(new Order("123456", "John Doe", "Blue Frame, Anti-glare Lenses", "2024-09-20", "2024-09-30", "Pending Payment", 120.0));
        pendingOrders.add(new Order("789101", "Jane Smith", "Red Frame, UV Protection", "2024-09-22", "2024-10-01", "In Progress", 0));

        adapter = new PendingOrderAdapter(pendingOrders, this);
        recyclerView.setAdapter(adapter);
    }

    // Method to handle payment click
    public void makePayment(Order order) {
        if (order.getPendingPayment() > 0) {
            // Simulate payment processing
            order.setPendingPayment(0);  // Mark as paid
            order.setStatus("Paid"); // Update order status
            Toast.makeText(this, "Payment successful for Order ID: " + order.getOrderId(), Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();  // Refresh the RecyclerView
        } else {
            Toast.makeText(this, "No payment due for Order ID: " + order.getOrderId(), Toast.LENGTH_SHORT).show();
        }
    }

    // Method to handle cancel order click
    public void cancelOrder(Order order) {
        // Simulate backend cancellation process here
        pendingOrders.remove(order);
        adapter.notifyDataSetChanged();  // Refresh the RecyclerView

        Toast.makeText(this, "Order Cancelled: " + order.getOrderId(), Toast.LENGTH_SHORT).show();
        // You can add actual backend call to cancel the order if needed
    }
}
