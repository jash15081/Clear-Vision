package com.example.dadu;

public class Order {
    private String orderId;
    private String customerName;
    private String specsDetails;
    private String orderDate;
    private String deliveryDate;
    private String status;
    private double pendingPayment;

    public Order(String orderId, String customerName, String specsDetails, String orderDate, String deliveryDate, String status, double pendingPayment) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.specsDetails = specsDetails;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.pendingPayment = pendingPayment;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getSpecsDetails() {
        return specsDetails;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPendingPayment() {
        return pendingPayment;
    }
    public void setPendingPayment(double pendingPayment) {
        this.pendingPayment = pendingPayment;
    }

}
