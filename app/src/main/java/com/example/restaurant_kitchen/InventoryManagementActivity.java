package com.example.restaurant_kitchen;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InventoryManagementActivity extends AppCompatActivity {

    private InventoryDatabase databaseHelper;
    private EditText ingredientInput, amountInput;
    private RecyclerView inventoryRecyclerView;
    private InventoryAdapter inventoryAdapter;
    private List<InventoryItem> inventoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_management);

        // Initialize database and UI elements
        databaseHelper = new InventoryDatabase(this);
        ingredientInput = findViewById(R.id.ingredientInput);
        amountInput = findViewById(R.id.amountInput);
        inventoryRecyclerView = findViewById(R.id.inventoryRecyclerView);
        Button addInventoryButton = findViewById(R.id.addInventoryButton);
        Button dailyReportButton = findViewById(R.id.dailyReportButton);

        // RecyclerView setup
        inventoryList = new ArrayList<>();
        inventoryAdapter = new InventoryAdapter(inventoryList);
        inventoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        inventoryRecyclerView.setAdapter(inventoryAdapter);

        // Load inventory data
        loadInventory();

        // Add inventory button
        addInventoryButton.setOnClickListener(v -> addInventoryItem());

        // Daily report button
        dailyReportButton.setOnClickListener(v -> {
            Intent intent = new Intent(InventoryManagementActivity.this, DailyReportActivity.class);
            startActivity(intent);
        });
    }

    private void loadInventory() {
        inventoryList.clear();
        inventoryList.addAll(databaseHelper.getAllInventoryItems());
        inventoryAdapter.notifyDataSetChanged();
    }

    private void addInventoryItem() {
        String ingredientName = ingredientInput.getText().toString();
        String amountStr = amountInput.getText().toString();

        if (TextUtils.isEmpty(ingredientName) || TextUtils.isEmpty(amountStr)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        int amount = Integer.parseInt(amountStr);

        boolean isAdded = databaseHelper.addInventoryItem(ingredientName, amount);
        if (isAdded) {
            Toast.makeText(this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            loadInventory();
        } else {
            Toast.makeText(this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
        }

        ingredientInput.setText("");
        amountInput.setText("");
    }
}
