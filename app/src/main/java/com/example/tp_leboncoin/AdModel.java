package com.example.tp_leboncoin;

public class AdModel {
    private String title;
    private String address;
    private String ext_path_image;
    private String int_path_image;
    private String telephone_number;
    // Constructor
    public AdModel(String title, String address, String ext_path_image, String int_path_image, String telephone_number) {
        this.title = title;
        this.address = address;
        this.int_path_image = int_path_image;
        this.ext_path_image = ext_path_image;
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

    public String getExternalPathImage() {
        return ext_path_image;
    }
    public void setExternalPathImage(String ext_path_image) {
        this.ext_path_image = ext_path_image;
    }
    public String getInternalPathImage() {
        return int_path_image;
    }
    public void setInternalPathImage(String int_path_image) {
        this.int_path_image = int_path_image;
    }
    public String getTelephone_number() {
        return this.telephone_number;
    }
    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }
}
