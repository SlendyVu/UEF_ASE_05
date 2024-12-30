package com.example.restaurant_kitchen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.annotation.SuppressLint;
import java.util.ArrayList;
import java.util.List;

public class OrdersDatabase {

    private SQLiteHelper dbHelper;

    public OrdersDatabase(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void addOrder(int tableNumber, String dishName, String status) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("table_number", tableNumber);
        values.put("dish_name", dishName);
        values.put("status", status);
        db.insert(SQLiteHelper.TABLE_ORDERS, null, values);
        db.close();
    }

    public List<String> getOrders() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<String> orders = new ArrayList<>();
        Cursor cursor = db.query(SQLiteHelper.TABLE_ORDERS, null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int tableNumber = cursor.getInt(cursor.getColumnIndex("table_number"));
                String dishName = cursor.getString(cursor.getColumnIndex("dish_name"));
                String status = cursor.getString(cursor.getColumnIndex("status"));
                orders.add("Table " + tableNumber + ": " + dishName + " (" + status + ")");
            }
            cursor.close();
        }

        db.close();
        return orders;
    }
}
