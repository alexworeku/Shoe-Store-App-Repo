package com.example.shoestoreapp.Models;

import com.example.shoestoreapp.Models.Shoe;

public class CartItem {
    private int id;
    private int quantity;
    private int shoeId;
    private Shoe shoe;

    public CartItem(int id, int quantity, Shoe shoe) {
        this.id = id;
        this.quantity = quantity;
        this.shoeId = shoe.getId();
        this.shoe = shoe;
    }

    public CartItem(int quantity, int shoeId) {
        this.quantity = quantity;
        this.shoeId = shoeId;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return quantity * shoe.getPrice();
    }

    public Shoe getShoe() {
        return shoe;
    }

    public int getShoeId() {
        return shoeId;
    }

    public void incQuantity() {
        quantity++;
    }

    public void decQuantity() {
        quantity--;
    }

}