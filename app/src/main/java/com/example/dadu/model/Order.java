package com.example.eyespecsapp.models;

public class Order {
    private String orderDate;
    private boolean isOrderCompleted;
    private boolean isPaymentPending;
    private int eyeCapacity;

    public Order(String orderDate, boolean isOrderCompleted, boolean isPaymentPending, int eyeCapacity) {
        this.orderDate = orderDate;
        this.isOrderCompleted = isOrderCompleted;
        this.isPaymentPending = isPaymentPending;
        this.eyeCapacity = eyeCapacity;
    }

    // Getters and Setters
    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }

    public boolean isOrderCompleted() { return isOrderCompleted; }
    public void setOrderCompleted(boolean orderCompleted) { isOrderCompleted = orderCompleted; }

    public boolean isPaymentPending() { return isPaymentPending; }
    public void setPaymentPending(boolean paymentPending) { this.isPaymentPending = paymentPending; }

    public int getEyeCapacity() { return eyeCapacity; }
    public void setEyeCapacity(int eyeCapacity) { this.eyeCapacity = eyeCapacity; }
}
