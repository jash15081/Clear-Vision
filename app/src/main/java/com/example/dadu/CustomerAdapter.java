package com.example.dadu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private ArrayList<Customer> customerList;

    public CustomerAdapter(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        Customer customer = customerList.get(position);
        holder.nameTextView.setText(customer.getName());
        holder.detailsTextView.setText(customer.getSpecsDetails());
        holder.dateTextView.setText(customer.getPurchaseDate());
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public void updateList(ArrayList<Customer> filteredList) {
        customerList = filteredList;
        notifyDataSetChanged();
    }

    public static class CustomerViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, detailsTextView, dateTextView;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            detailsTextView = itemView.findViewById(R.id.detailsTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}
