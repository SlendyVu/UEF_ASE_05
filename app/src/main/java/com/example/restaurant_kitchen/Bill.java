package com.example.restaurant_kitchen;

public class Bill {
    private String tableNumber;
    private String totalAmount;
    private String date;
    private String paymentMethod;

    public Bill(String tableNumber, String totalAmount, String date, String paymentMethod) {
        this.tableNumber = tableNumber;
        this.totalAmount = totalAmount;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public String getDate() {
        return date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}

