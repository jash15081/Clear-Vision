package com.example.dadu; // Replace with your actual package name

import java.io.Serializable;

public class Customer implements Serializable {
    private String name;
    private String specsDetails;
    private String purchaseDate;

    // Constructor
    public Customer(String name, String specsDetails, String purchaseDate) {
        this.name = name;
        this.specsDetails = specsDetails;
        this.purchaseDate = purchaseDate;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getSpecsDetails() {
        return specsDetails;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    // Setters (Optional, in case you want to modify the data)
    public void setName(String name) {
        this.name = name;
    }

    public void setSpecsDetails(String specsDetails) {
        this.specsDetails = specsDetails;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    // toString method (Optional, for debugging or displaying purposes)
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", specsDetails='" + specsDetails + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                '}';
    }
}
