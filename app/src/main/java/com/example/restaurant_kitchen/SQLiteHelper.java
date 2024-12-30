package com.example.restaurant_kitchen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "RestaurantDB";
    private static final int DATABASE_VERSION = 2;

    // Table Names
    public static final String TABLE_ORDERS = "Orders";
    public static final String TABLE_INVOICES = "Invoices";
    public static final String TABLE_INVENTORY = "Inventory";
    public static final String TABLE_EMPLOYEES = "Employees";

    // SQL Create Tables
    private static final String CREATE_TABLE_ORDERS = "CREATE TABLE " + TABLE_ORDERS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "table_number INTEGER, " +
            "dish_name TEXT, " +
            "status TEXT" +
            ");";

    private static final String CREATE_TABLE_INVOICES = "CREATE TABLE " + TABLE_INVOICES + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "payment_method TEXT, " +
            "total_amount REAL, " +
            "date TEXT" +
            ");";

    private static final String CREATE_TABLE_INVENTORY = "CREATE TABLE " + TABLE_INVENTORY + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "ingredient TEXT, " +
            "quantity INTEGER, " +
            "date TEXT" +
            ");";

    private static final String CREATE_TABLE_EMPLOYEES = "CREATE TABLE " + TABLE_EMPLOYEES + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, " +
            "shift TEXT" +
            ");";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ORDERS);
        db.execSQL(CREATE_TABLE_INVOICES);
        db.execSQL(CREATE_TABLE_INVENTORY);
        db.execSQL(CREATE_TABLE_EMPLOYEES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVOICES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        onCreate(db);
    }
}
