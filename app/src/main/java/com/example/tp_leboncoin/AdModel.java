package com.example.tp_leboncoin;

public class AdModel {
    private String title;
    private String address;
    private int image;
    private String telephone_number;
    // Constructor
    public AdModel(String title, String address, int image, String telephone_number) {
        this.title = title;
        this.address = address;
        this.image = image;
        this.telephone_number = telephone_number;
    }
    // Getter and Setter
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String title) {
        this.address = address;
    }

    public int getImage() {
        return image;
    }
    public void setImage(String title) {
        this.image = image;
    }

    public String getTelephone_number() {
        return this.telephone_number;
    }
    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }

// ...
}
