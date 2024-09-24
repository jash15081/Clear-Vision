package com.example.dadu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dadu.R;
import com.example.eyespecsapp.models.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orders;
    private Context context;

    public OrderAdapter(List<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.tvOrderDate.setText("Order Date: " + order.getOrderDate());
        holder.cbOrderCompleted.setChecked(order.isOrderCompleted());
        holder.cbPaymentPending.setChecked(order.isPaymentPending());
        holder.tvEyeCapacity.setText("Eye Capacity: " + order.getEyeCapacity());

        // Handle updates to order status
        holder.cbOrderCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
            order.setOrderCompleted(isChecked);
        });

        holder.cbPaymentPending.setOnCheckedChangeListener((buttonView, isChecked) -> {
            order.setPaymentPending(!isChecked); // Payment is completed if unchecked
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    // ViewHolder Class
    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderDate, tvEyeCapacity;
        CheckBox cbOrderCompleted, cbPaymentPending;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderDate = itemView.findViewById(R.id.tv_order_date);
            tvEyeCapacity = itemView.findViewById(R.id.tv_eye_capacity);
            cbOrderCompleted = itemView.findViewById(R.id.cb_order_completed);
            cbPaymentPending = itemView.findViewById(R.id.cb_payment_pending);
        }
    }
}
