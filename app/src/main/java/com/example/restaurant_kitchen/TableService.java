package com.example.restaurant_kitchen;

import java.util.ArrayList;
import java.util.List;

public class TableService {

    private List<String> orders;

    public TableService() {
        orders = new ArrayList<>();
    }

    // Thêm món vào đơn hàng
    public void addOrder(int tableNumber, String dishName) {
        String order = "Table " + tableNumber + ": " + dishName;
        orders.add(order);
        // Cập nhật trạng thái món đã được thêm
        System.out.println("Order added: " + order);
    }

    // Thông báo cho nhân viên khi món đã sẵn sàng
    public void notifyStaff(int tableNumber) {
        // Thông báo đơn hàng đã hoàn thành
        System.out.println("Notifying staff for table " + tableNumber + " that the order is ready.");
    }

    // Lấy danh sách đơn hàng
    public List<String> getOrders() {
        return orders;
    }
}