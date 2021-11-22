package com.example.shoestoreapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageHelper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoestoreapp.Controller.CartController;
import com.example.shoestoreapp.Controller.ShoeController;
import com.example.shoestoreapp.Helper.ImageConverter;
import com.example.shoestoreapp.Models.CartItem;
import com.example.shoestoreapp.Models.Shoe;
import com.example.shoestoreapp.Repository.CartRepository;
import com.example.shoestoreapp.Repository.ShoeRepository;

public class ShoeDetailActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_detail);

        TextView tv_shoeName = findViewById(R.id.tv_shoeNameForDetail);
        TextView tv_price = findViewById(R.id.tv_shoePriceForDetail);
        TextView tv_description = findViewById(R.id.tv_shoeDiscForDetail);
        ImageView iv_shoeImage = findViewById(R.id.iv_shoeImageForDetail);
        TextView tv_shoeStorePhoneNumber = findViewById(R.id.tv_storePhoneNumber);

        ImageButton btnBack = findViewById(R.id.ib_backFromShoeDetail);
        ImageButton btnCallStore = findViewById(R.id.btn_callStore);
        ImageButton btnOpenStoreLocationOnMap = findViewById(R.id.btn_openStoreMap);
        Button btnAddToCart = findViewById(R.id.btn_addToCart);

        btnBack.setOnClickListener((v) -> {

            finish();
        });

        int selectedShoeId = (int) getIntent().getExtras().get("selectedShoeId");
        Shoe selectedShoe = new ShoeController(new ShoeRepository(getApplicationContext())).getShoeById(selectedShoeId);

        tv_shoeName.setText(selectedShoe.getName());
        tv_price.setText("$" + selectedShoe.getPrice());
        tv_description.setText(selectedShoe.getDescription());
        iv_shoeImage.setImageBitmap(ImageConverter.toBitmap(selectedShoe.getImage()));
        tv_shoeStorePhoneNumber.setText(selectedShoe.getStorePhoneNumber());
        btnCallStore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel: " + selectedShoe.getStorePhoneNumber()));
            startActivity(intent);
        });
        btnOpenStoreLocationOnMap.setOnClickListener(view -> {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo: " + selectedShoe.getStoreLocation()));
            Intent chooserIntent = Intent.createChooser(intent, "Choose map");

            startActivity(chooserIntent);
        });

        btnAddToCart.setOnClickListener(view -> {
            CartController cartController = new CartController(new CartRepository(getApplicationContext()));
          boolean isSuccessful=  cartController.addItemToCart(new CartItem(1, selectedShoe.getId()));
            if (isSuccessful) {
                Toast.makeText(getApplicationContext(), "Added to cart !", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Failed !", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
