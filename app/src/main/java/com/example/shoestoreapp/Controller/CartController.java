package com.example.shoestoreapp.Controller;

import com.example.shoestoreapp.Models.CartItem;
import com.example.shoestoreapp.Repository.ICartRepository;
import com.example.shoestoreapp.Repository.IShoeRepository;

import java.util.List;

public class CartController {
    private ICartRepository cartRepository;

    public CartController(ICartRepository cartRepository) {
        this.cartRepository = cartRepository;

    }

    public boolean addItemToCart(CartItem item) {
        return cartRepository.add(item);
    }

    public boolean removeItemFromCart(int itemId) {
        return cartRepository.remove(itemId);
    }

    public boolean removeAllItemsFromCart() {
        return cartRepository.removeAll();
    }

    public List<CartItem> getAllCartItems() {
        return cartRepository.getAll();
    }

    public boolean increaseCartItemQuantity(CartItem cartItem) {
        cartItem.incQuantity();
        return cartRepository.update(cartItem.getId(), cartItem.getQuantity());
    }

    public boolean decreaseCartItemQuantity(CartItem cartItem) {
        cartItem.decQuantity();
        return cartRepository.update(cartItem.getId(), cartItem.getQuantity());
    }

    public boolean checkOutCartItems() {
        return cartRepository.checkOut();
    }
}
