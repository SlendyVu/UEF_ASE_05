package com.example.restaurant_kitchen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    private List<Map<String, String>> orders;
    private Context context;

    public OrdersAdapter(Context context, List<Map<String, String>> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map<String, String> order = orders.get(position);

        // Debug: kiểm tra từng item của danh sách
        System.out.println("Binding order: " + order.toString());

        holder.tvTable.setText("Bàn: " + order.get("table_number"));
        holder.tvDish.setText("Món: " + order.get("dish_name"));
        holder.tvStatus.setText("Trạng thái: " + order.get("status"));
    }


    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void updateData(List<Map<String, String>> newOrders) {
        this.orders = newOrders;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTable, tvDish, tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTable = itemView.findViewById(R.id.tvTable);
            tvDish = itemView.findViewById(R.id.tvDish);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
