package com.example.restaurant_kitchen;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

public class PaymentAndBillingActivity extends AppCompatActivity {

    private EditText etTableNumber, etTotalAmount, etDate;
    private Spinner spPaymentMethod;
    private Button btnSaveBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_billing);

        // Ánh xạ các thành phần giao diện
        etTableNumber = findViewById(R.id.etTableNumber);
        etTotalAmount = findViewById(R.id.etTotalAmount);
        etDate = findViewById(R.id.etDate);
        spPaymentMethod = findViewById(R.id.spPaymentMethod);
        btnSaveBill = findViewById(R.id.btnSaveBill);

        // Thiết lập Spinner phương thức thanh toán
        String[] paymentMethods = {"Tiền mặt", "Thẻ tín dụng", "Chuyển khoản"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentMethods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPaymentMethod.setAdapter(adapter);

        // Xử lý khi nhấn nút Lưu hóa đơn
        btnSaveBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBill();
            }
        });
        Button btnViewBills = findViewById(R.id.btnViewBills);
        btnViewBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang màn hình hiển thị danh sách hóa đơn
                Intent intent = new Intent(PaymentAndBillingActivity.this, ViewBillsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveBill() {
        // Lấy dữ liệu từ giao diện
        String tableNumber = etTableNumber.getText().toString().trim();
        String totalAmount = etTotalAmount.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String paymentMethod = spPaymentMethod.getSelectedItem().toString();

        // Kiểm tra dữ liệu
        if (tableNumber.isEmpty() || totalAmount.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lưu dữ liệu vào cơ sở dữ liệu (giả lập)
        boolean isSaved = saveBillToDatabase(tableNumber, totalAmount, date, paymentMethod);

        if (isSaved) {
            Toast.makeText(this, "Hóa đơn đã được lưu!", Toast.LENGTH_SHORT).show();
            // Sau khi lưu, có thể reset các trường hoặc chuyển trang
            etTableNumber.setText("");
            etTotalAmount.setText("");
            etDate.setText("");
        } else {
            Toast.makeText(this, "Lưu hóa đơn thất bại!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean saveBillToDatabase(String tableNumber, String totalAmount, String date, String paymentMethod) {
        // Giả lập lưu vào cơ sở dữ liệu
        // Thay thế bằng logic thực tế để lưu vào SQLite hoặc API
        return true; // Trả về true nếu lưu thành công
    }

}
