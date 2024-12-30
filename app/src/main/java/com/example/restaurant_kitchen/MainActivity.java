package com.example.restaurant_kitchen;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button btnTableService, btnPaymentBilling, btnInventoryManagement, btnHumanResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnTableService = findViewById(R.id.btnTableService);
        btnPaymentBilling = findViewById(R.id.btnPaymentBilling);
        btnInventoryManagement = findViewById(R.id.btnInventoryManagement);
        btnHumanResources = findViewById(R.id.btnHumanResources);

        btnTableService.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TableServiceActivity.class);
            startActivity(intent);
        });

        btnPaymentBilling.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PaymentAndBillingActivity.class);
            startActivity(intent);
        });

        btnInventoryManagement.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InventoryManagementActivity.class);
            startActivity(intent);
        });

        btnHumanResources.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HumanResourcesActivity.class);
            startActivity(intent);
        });
    }
}