package com.example.shoestoreapp.Models;

import java.io.Serializable;

public class Shoe implements Serializable {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String brand;
    private String description;
    private byte[] image;
    private String storePhoneNumber;
    private String storeLocation;


    public Shoe(
            int id,
            String name,
            double price,
            int quantity,
            String brand,
            String description,
            byte[] image,
            String phone,
            String location) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;

        this.description = description;
        this.image = image;
        storePhoneNumber = phone;
        storeLocation = location;

    }

    public Shoe(String name,
                double price,
                int quantity,
                String brand,
                String description,
                byte[] image,
                String phone,
                String location
    ) {

        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
        this.description = description;
        this.image = image;
        storePhoneNumber = phone;
        storeLocation = location;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getBrand() {
        return brand;
    }


    public String getDescription() {
        return description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getStorePhoneNumber() {
        return storePhoneNumber;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

}
