package com.example.restaurant_kitchen;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "RestaurantDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_INVOICES = "Invoices";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_PAYMENT_METHOD = "paymentMethod";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableInvoices = "CREATE TABLE " + TABLE_INVOICES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_AMOUNT + " REAL, " +
                COLUMN_PAYMENT_METHOD + " TEXT)";
        db.execSQL(createTableInvoices);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVOICES);
        onCreate(db);
    }

    // Hàm chèn hóa đơn mới vào bảng Invoices
    public boolean insertInvoice(double amount, String paymentMethod) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_AMOUNT, amount);
        contentValues.put(COLUMN_PAYMENT_METHOD, paymentMethod);

        long result = db.insert(TABLE_INVOICES, null, contentValues);
        return result != -1; // Trả về true nếu chèn thành công
    }
}
