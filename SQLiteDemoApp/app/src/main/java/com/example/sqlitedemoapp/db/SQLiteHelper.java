package com.example.sqlitedemoapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqlitedemoapp.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ChiTieu.db";
    public static final int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE item (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, category TEXT, price REAL, date TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String order = "date DESC";
        Cursor rs = db.query("item", null, null, null, null, null, order);
        while ((rs != null) && rs.moveToNext()) {
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String category = rs.getString(2);
            double price = rs.getDouble(3);
            String date = rs.getString(4);
            Item item = new Item(id, name, category, price, date);
            items.add(item);
        }
        return items;
    }

    public List<Item> getByDate(String dateSearched) {
        List<Item> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selection = "date like ?";
        String[] whereArgs = new String[]{dateSearched};
        Cursor rs = db.query("item", null, selection, whereArgs, null, null, null);
        while ((rs != null) && rs.moveToNext()) {
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String category = rs.getString(2);
            double price = rs.getDouble(3);
            String date = rs.getString(4);
            Item item = new Item(id, name, category, price, date);
            items.add(item);
        }
        return items;
    }

    public List<Item> getByTitle(String title) {
        List<Item> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selection = "name like ?";
        String[] whereArgs = new String[]{"%" + title + "%"};
        Cursor rs = db.query("item", null, selection, whereArgs, null, null, null);
        while ((rs != null) && rs.moveToNext()) {
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String category = rs.getString(2);
            double price = rs.getDouble(3);
            String date = rs.getString(4);
            Item item = new Item(id, name, category, price, date);
            items.add(item);
        }
        return items;
    }

    public List<Item> getFromDateToDate(String fromDate, String toDate) {
        List<Item> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selection = "date between ? and ?";
        String[] whereArgs = new String[]{fromDate, toDate};
        Cursor rs = db.query("item", null, selection, whereArgs, null, null, null);
        while ((rs != null) && rs.moveToNext()) {
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String category = rs.getString(2);
            double price = rs.getDouble(3);
            String date = rs.getString(4);
            Item item = new Item(id, name, category, price, date);
            items.add(item);
        }
        return items;
    }

    public int getTotalByDate(String dateSearched) {
        int total = 0;
        SQLiteDatabase db = getReadableDatabase();
        String[] whereArgs = new String[]{dateSearched};
        Cursor rs = db.rawQuery("SELECT SUM(price) FROM item WHERE date like ?", whereArgs);
        if ((rs != null) && rs.moveToNext()) {
            total = rs.getInt(0);
        }
        return total;
    }

    public int getTotal() {
        int total = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor rs = db.rawQuery("SELECT SUM(price) FROM item",null);
        if ((rs != null) && rs.moveToNext()) {
            total = rs.getInt(0);
        }
        return total;
    }

    public void addItem(Item item) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO item (name, category, price, date) VALUES (?, ?, ?, ?)";
        db.execSQL(sql, new String[]{item.getName(), item.getCategory(), String.valueOf(item.getPrice()), item.getDate()});
    }

    public void updateItem(Item item) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE item SET name = ?, category = ?, price = ?, date = ? WHERE id = ?";
        db.execSQL(sql, new String[]{item.getName(), item.getCategory(), String.valueOf(item.getPrice()), item.getDate(), String.valueOf(item.getId())});
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM item WHERE id = ?";
        db.execSQL(sql, new String[]{String.valueOf(id)});
    }
}
