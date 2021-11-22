package com.example.shoestoreapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoestoreapp.Adapter.CartItemAdapter;
import com.example.shoestoreapp.Controller.CartController;
import com.example.shoestoreapp.Models.CartItem;
import com.example.shoestoreapp.Repository.CartRepository;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView rv_cartItems;
    private TextView tv_totalCartPrice, tv_itemsCount;
    CartController cartController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ImageButton btnBackFromCart = findViewById(R.id.ib_backFromCart);
        Button btnCheckout = findViewById(R.id.btn_checkOut);
        ImageButton btnRemoveAllItems = findViewById(R.id.btnRemoveAllCartItems);
        tv_totalCartPrice = findViewById(R.id.tv_totalPrice);
        tv_itemsCount = findViewById(R.id.tv_cartItemsCount);
        rv_cartItems = findViewById(R.id.rv_cartItemsList);
        rv_cartItems.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        cartController = new CartController(new CartRepository(getApplicationContext()));
        btnBackFromCart.setOnClickListener(view -> {
            finish();
        });
        btnCheckout.setOnClickListener(view -> {
            if (cartController.getAllCartItems().size() > 0) {
                boolean isSuccessful = cartController.checkOutCartItems();
                if (isSuccessful) {
                    Toast.makeText(getApplicationContext(), "Checking out", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),OnlinePaymentActivity.class));
                }
            }
        });
        btnRemoveAllItems.setOnClickListener(view -> {
            boolean isSuccessful = cartController.removeAllItemsFromCart();
            if (isSuccessful) {
                reloadData();
                Toast.makeText(getApplicationContext(), "All cart items are removed!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadData();
    }

    public void reloadData() {
        List<CartItem> cartItems = cartController.getAllCartItems();
        CartItemAdapter adapter = new CartItemAdapter(cartItems, getApplicationContext(), this);
        rv_cartItems.setAdapter(adapter);
        tv_itemsCount.setText(String.format("Total %s items", cartItems.size()));
        tv_totalCartPrice.setText(String.format("$%s", getCartTotalPrice(cartItems)));
    }

    private double getCartTotalPrice(List<CartItem> cartItems) {
        double totalPrice = 0.0;
        for (CartItem item : cartItems) {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }
}
