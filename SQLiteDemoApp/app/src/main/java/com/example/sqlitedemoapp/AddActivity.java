package com.example.sqlitedemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sqlitedemoapp.db.SQLiteHelper;
import com.example.sqlitedemoapp.model.Item;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    SQLiteHelper db;
    EditText etName, etPrice, etDate;
    Spinner spinnerCategory;
    Button btnAdd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
    }

    private void initView() {
        etName = findViewById(R.id.add_name);
        etPrice = findViewById(R.id.add_price);
        etDate = findViewById(R.id.add_date);
        spinnerCategory = findViewById(R.id.add_category_spinner);
        btnAdd = findViewById(R.id.add_button);
        btnBack = findViewById(R.id.back_button_add_activity);
        db = new SQLiteHelper(this);

        btnBack.setOnClickListener(v -> finish());

        etDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
            int currentMonth = calendar.get(Calendar.MONTH);
            int currentYear = calendar.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this, (view, year, month, dayOfMonth) -> {
                String day = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                String monthStr = month < 9 ? "0" + (month + 1) : String.valueOf(month + 1);
                etDate.setText(year + "-" + monthStr + "-" + day);
            }, currentYear, currentMonth, currentDay);
            datePickerDialog.show();
        });

        spinnerCategory.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.category)));

        btnAdd.setOnClickListener(v -> {
            if(etName.getText().toString().isEmpty() || etPrice.getText().toString().isEmpty() || etDate.getText().toString().isEmpty()) {
                return;
            }
            db.addItem(new Item(
                    etName.getText().toString(),
                    spinnerCategory.getSelectedItem().toString(),
                    Double.parseDouble(etPrice.getText().toString()),
                    etDate.getText().toString()
            ));

            finish();
        });
    }
}