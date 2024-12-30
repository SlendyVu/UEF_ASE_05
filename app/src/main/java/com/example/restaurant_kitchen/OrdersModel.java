package com.example.restaurant_kitchen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersModel {
    private SQLiteHelper dbHelper;

    public OrdersModel(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        dbHelper = new SQLiteHelper(context);
    //    OrdersModel ordersModel = new OrdersModel(OrderActivity.this);

    }


    // Hàm thêm order
    public void addOrder(int tableNumber, String dishName, String status) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("table_number", tableNumber);
        values.put("dish_name", dishName);
        values.put("status", status);

        long result = db.insert("Orders", null, values);
        if (result == -1) {
            System.out.println("Error inserting order into database");
        } else {
            System.out.println("Order saved successfully with ID: " + result);
        }
        db.close();
    }


    // Hàm lấy danh sách order
    public List<Map<String, String>> getOrders() {
        List<Map<String, String>> orders = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Orders", null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Map<String, String> order = new HashMap<>();
                order.put("id", String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("id"))));
                order.put("table_number", String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("table_number"))));
                order.put("dish_name", cursor.getString(cursor.getColumnIndexOrThrow("dish_name")));
                order.put("status", cursor.getString(cursor.getColumnIndexOrThrow("status")));
                orders.add(order);
            }
            cursor.close();
        }

        db.close();

        // Ghi log danh sách order để kiểm tra
        System.out.println("Orders retrieved: " + orders.toString());
        return orders;
    }
}
