package com.example.restaurant_kitchen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TableServiceActivity extends AppCompatActivity {

    private EditText tableNumberInput, dishNameInput;
    private Button addOrderButton, viewOrdersButton;
    private TextView ordersDisplay;

    private OrdersDatabase ordersDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_service);

        tableNumberInput = findViewById(R.id.tableNumberInput);
        dishNameInput = findViewById(R.id.dishNameInput);
        addOrderButton = findViewById(R.id.addOrderButton);
        viewOrdersButton = findViewById(R.id.viewOrdersButton);
        ordersDisplay = findViewById(R.id.ordersDisplay);

        ordersDatabase = new OrdersDatabase(this);

        addOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tableNumber = Integer.parseInt(tableNumberInput.getText().toString());
                String dishName = dishNameInput.getText().toString();
                ordersDatabase.addOrder(tableNumber, dishName, "Pending");
                Toast.makeText(TableServiceActivity.this, "Order Added!", Toast.LENGTH_SHORT).show();
            }
        });

        viewOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> orders = ordersDatabase.getOrders();
                ordersDisplay.setText("");
                for (String order : orders) {
                    ordersDisplay.append(order + "\n");
                }
            }
        });
    }
}
