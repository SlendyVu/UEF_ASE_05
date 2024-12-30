package com.example.restaurant_kitchen;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewBillsActivity extends AppCompatActivity {

    private RecyclerView rvBills;
    private BillAdapter billAdapter;
    private List<Bill> billList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bills);

        rvBills = findViewById(R.id.rvBills);

        // Lấy danh sách hóa đơn từ cơ sở dữ liệu hoặc giả lập ở đây
        billList = getBillListFromDatabase();

        // Thiết lập RecyclerView
        rvBills.setLayoutManager(new LinearLayoutManager(this));
        billAdapter = new BillAdapter(billList);
        rvBills.setAdapter(billAdapter);
    }

    private List<Bill> getBillListFromDatabase() {
        // Giả lập dữ liệu
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill("1", "100,000", "28/12/2024", "Tiền mặt"));
        bills.add(new Bill("2", "200,000", "28/12/2024", "Chuyển khoản"));
        bills.add(new Bill("3", "150,000", "29/12/2024", "Quét mã QR"));
        return bills;
    }
}
