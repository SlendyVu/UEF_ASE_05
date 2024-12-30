package com.example.restaurant_kitchen;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class EmployeesDatabase {

    private SQLiteHelper dbHelper;

    public EmployeesDatabase(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void addEmployee(String name, String shift) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("shift", shift);
        db.insert(SQLiteHelper.TABLE_EMPLOYEES, null, values);
        db.close();
    }

    public List<String> getAllEmployees() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<String> employees = new ArrayList<>();
        Cursor cursor = db.query(SQLiteHelper.TABLE_EMPLOYEES, null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));//tý sửa
                @SuppressLint("Range") String shift = cursor.getString(cursor.getColumnIndex("shift"));//tý sửa
                employees.add("Tên: " + name + ", Ca làm: " + shift);
            }
            cursor.close();
        }

        db.close();
        return employees;
    }

    public List<String> getEmployeesByShift(String shift) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<String> shiftReport = new ArrayList<>();
        Cursor cursor = db.query(SQLiteHelper.TABLE_EMPLOYEES,
                null,
                "shift = ?",
                new String[]{shift},
                null,
                null,
                null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));//tý sửa
                shiftReport.add(name);
            }
            cursor.close();
        }

        db.close();
        return shiftReport;
    }
}
