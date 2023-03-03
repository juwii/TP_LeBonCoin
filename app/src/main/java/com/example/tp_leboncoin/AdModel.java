package com.example.tp_leboncoin;

public class AdModel {
    private String title;
    private String address;
    private int image;
    // Constructor
    public AdModel(String title, String address, int image) {
        this.title = title;
        this.address = address;
        this.image = image;
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
// ...
}
