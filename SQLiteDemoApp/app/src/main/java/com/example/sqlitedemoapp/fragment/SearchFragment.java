package com.example.sqlitedemoapp.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sqlitedemoapp.R;
import com.example.sqlitedemoapp.adapter.RecyclerViewAdapter;
import com.example.sqlitedemoapp.db.SQLiteHelper;

import java.util.Calendar;

public class SearchFragment extends Fragment {
    EditText edtSearch, fromDateField, toDateField;
    Spinner categorySpinner;
    Button btnSearch;
    TextView totalMoneyField;
    private SQLiteHelper db;
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtSearch = view.findViewById(R.id.search_text_field);
        fromDateField = view.findViewById(R.id.from_date_field);
        toDateField = view.findViewById(R.id.to_date_field);
        categorySpinner = view.findViewById(R.id.search_category_spinner);
        btnSearch = view.findViewById(R.id.search_btn);
        totalMoneyField = view.findViewById(R.id.search_total_price);

        recyclerView = view.findViewById(R.id.search_results);
        adapter = new RecyclerViewAdapter();

        db = new SQLiteHelper(getContext());

        fromDateField.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
            int currentMonth = calendar.get(Calendar.MONTH);
            int currentYear = calendar.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (dateView, year, month, dayOfMonth) -> {
                String day = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                String monthStr = month < 9 ? "0" + (month + 1) : String.valueOf(month + 1);
                fromDateField.setText(year + "-" + monthStr + "-" + day);
            }, currentYear, currentMonth, currentDay);
            datePickerDialog.show();
        });

        toDateField.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
            int currentMonth = calendar.get(Calendar.MONTH);
            int currentYear = calendar.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (dateView, year, month, dayOfMonth) -> {
                String day = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                String monthStr = month < 9 ? "0" + (month + 1) : String.valueOf(month + 1);
                toDateField.setText(year + "-" + monthStr + "-" + day);
            }, currentYear, currentMonth, currentDay);
            datePickerDialog.show();
        });

        btnSearch.setOnClickListener(v -> {
            String title = edtSearch.getText().toString();
            String beginDate = fromDateField.getText().toString();
            String endDate = toDateField.getText().toString();

            if(!title.isEmpty()) {
                adapter.setItems(db.getByTitle(title));
            } else if(!beginDate.isEmpty()) {
                adapter.setItems(db.getFromDateToDate(beginDate, endDate));
            } else {
                adapter.setItems(db.getAll());
            }
            LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        });
    }
}