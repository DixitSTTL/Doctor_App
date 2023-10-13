package com.app.doctorapp.models;

public class PrescripeModel {

    String name;
    String description;
    int qty;


    public PrescripeModel(String name, String description, int qty) {
        this.name = name;
        this.description = description;
        this.qty = qty;
    }

    public PrescripeModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
