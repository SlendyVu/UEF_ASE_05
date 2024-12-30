package com.example.restaurant_kitchen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class InvoicesDatabase {

    private SQLiteHelper dbHelper;

    public InvoicesDatabase(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void addInvoice(String paymentMethod, double totalAmount, String date) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("payment_method", paymentMethod);
        values.put("total_amount", totalAmount);
        values.put("date", date);
        db.insert(SQLiteHelper.TABLE_INVOICES, null, values);
        db.close();
    }

    public List<String> getInvoices() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<String> invoices = new ArrayList<>();
        Cursor cursor = db.query(SQLiteHelper.TABLE_INVOICES, null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String method = cursor.getString(cursor.getColumnIndex("payment_method"));
                double amount = cursor.getDouble(cursor.getColumnIndex("total_amount"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                invoices.add("Method: " + method + ", Amount: $" + amount + ", Date: " + date);
            }
            cursor.close();
        }

        db.close();
        return invoices;
    }
}
