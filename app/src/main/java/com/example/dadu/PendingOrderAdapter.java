package com.example.dadu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PendingOrderAdapter extends RecyclerView.Adapter<PendingOrderAdapter.OrderViewHolder> {

    private List<Order> pendingOrders;
    private PendingOrdersActivity activity;

    public PendingOrderAdapter(List<Order> pendingOrders, PendingOrdersActivity activity) {
        this.pendingOrders = pendingOrders;
        this.activity = activity;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_order_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = pendingOrders.get(position);
        holder.tvOrderID.setText("Order ID: " + order.getOrderId());
        holder.tvSpecsDetails.setText("Specs: " + order.getSpecsDetails());
        holder.tvOrderDate.setText("Order Date: " + order.getOrderDate());
        holder.tvDeliveryDate.setText("Delivery Date: " + order.getDeliveryDate());
        holder.tvStatus.setText("Status: " + order.getStatus());
        holder.tvPendingPayment.setText("Pending Payment: $" + order.getPendingPayment());

        // Set Payment Button visibility based on payment status
        if (order.getPendingPayment() > 0) {
            holder.btnMakePayment.setVisibility(View.VISIBLE);
        } else {
            holder.btnMakePayment.setVisibility(View.GONE);
        }

        // Individual Payment button click listener (not required for all payments button)
        holder.btnMakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.makePayment(order);  // Individual order payment processing
            }
        });

        // Cancel order button click listener
        holder.btnCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.cancelOrder(order);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pendingOrders.size();
    }

    // Method to return all pending orders
    public List<Order> getPendingOrders() {
        return pendingOrders;
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView tvOrderID, tvSpecsDetails, tvOrderDate, tvDeliveryDate, tvStatus, tvPendingPayment;
        Button btnMakePayment, btnCancelOrder;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderID = itemView.findViewById(R.id.tvOrderID);
            tvSpecsDetails = itemView.findViewById(R.id.tvSpecsDetails);
            tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
            tvDeliveryDate = itemView.findViewById(R.id.tvDeliveryDate);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvPendingPayment = itemView.findViewById(R.id.tvPendingPayment);
            btnMakePayment = itemView.findViewById(R.id.btnMakePayment);
            btnCancelOrder = itemView.findViewById(R.id.btnCancelOrder);
        }
    }
}
