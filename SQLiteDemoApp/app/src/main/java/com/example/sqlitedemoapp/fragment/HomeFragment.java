package com.example.sqlitedemoapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sqlitedemoapp.AddActivity;
import com.example.sqlitedemoapp.R;
import com.example.sqlitedemoapp.UpdateDeleteActivity;
import com.example.sqlitedemoapp.adapter.RecyclerViewAdapter;
import com.example.sqlitedemoapp.db.SQLiteHelper;
import com.example.sqlitedemoapp.model.Item;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment implements RecyclerViewAdapter.ItemListener {
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private TextView tvTotal;
    private SQLiteHelper db;
    private String today;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView_home);
        adapter = new RecyclerViewAdapter();
        db = new SQLiteHelper(getContext());

        today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        tvTotal = view.findViewById(R.id.total_money_today);
        tvTotal.setText("Tổng tiền: " + db.getTotalByDate(today) + " VND");

        List<Item> items = db.getByDate(today);
        adapter.setItems(items);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Item item = adapter.getItem(position);
        Intent intent = new Intent(getContext(), UpdateDeleteActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        tvTotal.setText("Tổng tiền: " + db.getTotalByDate(today) + " VND");
        List<Item> items = db.getByDate(today);
        adapter.setItems(items);
        recyclerView.setAdapter(adapter);
    }
}