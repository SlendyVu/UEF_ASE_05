package com.example.restaurant_kitchen;

public class InventoryItem {
    private final String name;
    private final int amount;

    public InventoryItem(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
