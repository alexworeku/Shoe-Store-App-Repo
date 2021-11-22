package com.example.shoestoreapp.Models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Brand implements Serializable {
    private int id;
    private String brandName;
    private byte[] brandImage;

    public Brand(int id, String brandName, byte[] brandImage) {
        this.id = id;
        this.brandName = brandName;
        this.brandImage = brandImage;
    }

    public Brand(String brandName, byte[] brandImage) {

        this.brandName = brandName;
        this.brandImage = brandImage;
    }

    public Brand(String brandName) {
        this.brandName = brandName;

    }

    public int getBrandId() {
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    public byte[] getBrandImage() {
        return brandImage;
    }

    @NonNull
    @Override
    public String toString() {
        return brandName;
    }
}
