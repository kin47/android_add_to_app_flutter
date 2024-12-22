package com.example.sqlitedemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sqlitedemoapp.db.SQLiteHelper;
import com.example.sqlitedemoapp.model.Item;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity {
    SQLiteHelper db;
    EditText etName, etPrice, etDate;
    Spinner spinnerCategory;
    Button btnUpdate, btnDelete, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
    }

    public void initView() {
        db = new SQLiteHelper(this);

        etName = findViewById(R.id.update_name);
        etPrice = findViewById(R.id.update_price);
        etDate = findViewById(R.id.update_date);
        spinnerCategory = findViewById(R.id.update_category_spinner);
        btnUpdate = findViewById(R.id.update_button);
        btnDelete = findViewById(R.id.delete_button);
        btnBack = findViewById(R.id.back_button);

        Intent intent = getIntent();
        Item item = (Item) intent.getSerializableExtra("item");

        etName.setText(item.getName());
        etPrice.setText(String.valueOf(item.getPrice()));
        etDate.setText(item.getDate());

        btnBack.setOnClickListener(v -> finish());

        etDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
            int currentMonth = calendar.get(Calendar.MONTH);
            int currentYear = calendar.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateDeleteActivity.this, (view, year, month, dayOfMonth) -> {
                String day = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                String monthStr = month < 9 ? "0" + (month + 1) : String.valueOf(month + 1);
                etDate.setText(year + "-" + monthStr + "-" + day);
            }, currentYear, currentMonth, currentDay);
            datePickerDialog.show();
        });

        spinnerCategory.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.category)));

        btnUpdate.setOnClickListener(v -> {
            if (etName.getText().toString().isEmpty() || etPrice.getText().toString().isEmpty() || etDate.getText().toString().isEmpty()) {
                return;
            }
            db.updateItem(new Item(
                    item.getId(),
                    etName.getText().toString(),
                    spinnerCategory.getSelectedItem().toString(),
                    Double.parseDouble(etPrice.getText().toString()),
                    etDate.getText().toString()
            ));

            finish();
        });

        btnDelete.setOnClickListener(v -> {
            db.deleteItem(item.getId());
            finish();
        });
    }
}