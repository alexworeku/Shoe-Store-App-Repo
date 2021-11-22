package com.example.shoestoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoestoreapp.Adapter.BrandAdapter;
import com.example.shoestoreapp.Adapter.ShoeAdapter;
import com.example.shoestoreapp.Controller.AdminController;
import com.example.shoestoreapp.Controller.BrandController;
import com.example.shoestoreapp.Controller.ShoeController;
import com.example.shoestoreapp.Repository.AdminRepository;
import com.example.shoestoreapp.Repository.BrandRepository;
import com.example.shoestoreapp.Repository.ShoeRepository;


public class MainActivity extends AppCompatActivity {
    private RecyclerView rv_brands, rv_shoes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText et_Search = findViewById(R.id.et_search);
        ImageButton btnSearch = findViewById(R.id.ib_search);
        ImageButton btnRefresh = findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(view -> {
            reloadData();
        });
        rv_brands = findViewById(R.id.rv_brands);
        rv_shoes = findViewById(R.id.rv_shoesList);
        rv_brands.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rv_shoes.setLayoutManager(new GridLayoutManager(this, 2));

        View nv_admin = findViewById(R.id.nv_item_admin);
        View nv_cart = findViewById(R.id.nv_item_cart);
        nv_admin.setOnClickListener((v) -> {
            AdminController adminController = new AdminController(new AdminRepository(getApplicationContext()));
            if (adminController.getAdmin().isLoggedIn()) {
                Intent intent = new Intent(getApplicationContext(), AdminDashboardActivity.class);
                startActivity(intent);

            } else {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }

        });
        nv_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), CartActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        btnSearch.setOnClickListener(view -> {
            filterByShoeName(et_Search.getText().toString());
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadData();

    }

    private void reloadData() {
        BrandController brandController = new BrandController(new BrandRepository(getApplicationContext()));
        ShoeController shoeController = new ShoeController(new ShoeRepository(getApplicationContext()));

        BrandAdapter brandAdapter = new BrandAdapter(brandController.getAllBrands(), this);
        ShoeAdapter shoeAdapter = new ShoeAdapter(shoeController.getAllShoes(), getApplicationContext());

        rv_shoes.setAdapter(shoeAdapter);
        rv_brands.setAdapter(brandAdapter);

    }

    public void filterByBrand(String brandName) {
        ShoeController shoeController = new ShoeController(new ShoeRepository(getApplicationContext()));
        ShoeAdapter shoeAdapter = new ShoeAdapter(shoeController.getShoesByBrand(brandName), getApplicationContext());

        rv_shoes.setAdapter(shoeAdapter);

    }

    private void filterByShoeName(String shoeName) {
        ShoeController shoeController = new ShoeController(new ShoeRepository(getApplicationContext()));
        ShoeAdapter shoeAdapter = new ShoeAdapter(shoeController.getShoesByName(shoeName), getApplicationContext());

        rv_shoes.setAdapter(shoeAdapter);

    }


}
