package com.example.restaurant_kitchen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class InventoryDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "restaurant_kitchen.db";
    private static final int DATABASE_VERSION = 1;

    // Table name and column names
    private static final String TABLE_INVENTORY = "inventory";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AMOUNT = "amount";

    public InventoryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create inventory table
        String CREATE_INVENTORY_TABLE = "CREATE TABLE " + TABLE_INVENTORY + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_AMOUNT + " INTEGER NOT NULL)";
        db.execSQL(CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);
        // Create new table
        onCreate(db);
    }

    /**
     * Add a new inventory item to the database
     */
    public boolean addInventoryItem(String name, int amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AMOUNT, amount);

        long result = db.insert(TABLE_INVENTORY, null, values);
        db.close();
        return result != -1;
    }

    /**
     * Get all inventory items from the database
     */
    public List<InventoryItem> getAllInventoryItems() {
        List<InventoryItem> inventoryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_INVENTORY, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                int amount = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AMOUNT));
                inventoryList.add(new InventoryItem(name, amount));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return inventoryList;
    }

    /**
     * Delete all items in the inventory table
     */
    public void clearInventory() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_INVENTORY);
        db.close();
    }

    /**
     * Delete a specific inventory item by name
     */
    public boolean deleteInventoryItem(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_INVENTORY, COLUMN_NAME + " = ?", new String[]{name});
        db.close();
        return rowsDeleted > 0;
    }

    /**
     * Update the amount of an existing inventory item
     */
    public boolean updateInventoryItem(String name, int newAmount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_AMOUNT, newAmount);

        int rowsUpdated = db.update(TABLE_INVENTORY, values, COLUMN_NAME + " = ?", new String[]{name});
        db.close();
        return rowsUpdated > 0;
    }

    /**
     * Check if an inventory item exists
     */
    public boolean isItemExists(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_INVENTORY + " WHERE " + COLUMN_NAME + " = ?", new String[]{name});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }
    public List<Report> getDailyReport() {
        List<Report> reportList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT name, SUM(amount) as total_used FROM inventory GROUP BY name";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                int totalUsed = cursor.getInt(cursor.getColumnIndexOrThrow("total_used"));
                reportList.add(new Report(name, totalUsed));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return reportList;
    }

}
