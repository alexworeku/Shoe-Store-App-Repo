package com.example.shoestoreapp.Repository;

import com.example.shoestoreapp.Models.CartItem;

import java.util.List;

public interface ICartRepository {
    boolean add(CartItem cartItem);
    boolean remove(int itemId);
    boolean removeAll();
    boolean update(int itemId,int newValue);
   List<CartItem> getAll();
    boolean checkOut();
}
