package com.example.restaurant_kitchen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DailyReportActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ReportAdapter reportAdapter;
    private InventoryDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_report);

        recyclerView = findViewById(R.id.recycler_view);
        database = new InventoryDatabase(this);

        List<Report> reportList = database.getDailyReport();

        reportAdapter = new ReportAdapter(reportList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(reportAdapter);
    }
}
