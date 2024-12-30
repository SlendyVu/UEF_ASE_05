package com.example.restaurant_kitchen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder> {

    private List<Bill> billList;

    public BillAdapter(List<Bill> billList) {
        this.billList = billList;
    }

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill, parent, false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        Bill bill = billList.get(position);
        holder.tvTableNumber.setText("Số bàn: " + bill.getTableNumber());
        holder.tvTotalAmount.setText("Tổng tiền: " + bill.getTotalAmount() + " VNĐ");
        holder.tvDate.setText("Ngày: " + bill.getDate());
        holder.tvPaymentMethod.setText("Phương thức: " + bill.getPaymentMethod());
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }

    static class BillViewHolder extends RecyclerView.ViewHolder {

        TextView tvTableNumber, tvTotalAmount, tvDate, tvPaymentMethod;

        public BillViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTableNumber = itemView.findViewById(R.id.tvTableNumber);
            tvTotalAmount = itemView.findViewById(R.id.tvTotalAmount);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvPaymentMethod = itemView.findViewById(R.id.tvPaymentMethod);
        }
    }
}

