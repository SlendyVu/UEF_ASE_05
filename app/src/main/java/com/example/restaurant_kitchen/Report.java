package com.example.restaurant_kitchen;

public class Report {
    private String itemName;
    private int quantityUsed;

    public Report(String itemName, int quantityUsed) {
        this.itemName = itemName;
        this.quantityUsed = quantityUsed;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantityUsed() {
        return quantityUsed;
    }
}
