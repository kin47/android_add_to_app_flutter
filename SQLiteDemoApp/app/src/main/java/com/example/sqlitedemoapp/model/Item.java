package com.example.sqlitedemoapp.model;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable {
    private int id;
    private String name;
    private String category;
    private double price;
    private String date;

    public Item(int id, String name, String category, double price, String date) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.date = date;
    }

    public Item(String name, String category, double price, String date) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
