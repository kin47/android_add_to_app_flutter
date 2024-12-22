package com.example.sqlitedemoapp.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedemoapp.R;
import com.example.sqlitedemoapp.model.Item;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private List<Item> items;
    private ItemListener listener;

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setListener(ItemListener listener) {
        this.listener = listener;
    }

    public Item getItem(int position) {
        return items.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(inflater);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.name.setText(items.get(position).getName());
        holder.category.setText(items.get(position).getCategory());
        holder.price.setText("Giá: " + items.get(position).getPrice());
        holder.date.setText("Ngày: " + items.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, category, price, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            category = itemView.findViewById(R.id.item_category);
            price = itemView.findViewById(R.id.item_price);
            date = itemView.findViewById(R.id.item_date);
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(v, getAdapterPosition());
                }
            });
        }
    }

    public interface ItemListener {
        void onItemClick(View view, int position);
    }
}
