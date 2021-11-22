package com.example.shoestoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoestoreapp.Controller.BrandController;
import com.example.shoestoreapp.Controller.ShoeController;
import com.example.shoestoreapp.Helper.ImageConverter;
import com.example.shoestoreapp.Models.Brand;
import com.example.shoestoreapp.Models.Shoe;
import com.example.shoestoreapp.Repository.BrandRepository;
import com.example.shoestoreapp.Repository.ShoeRepository;

import java.io.IOException;

public class AddShoeFormActivity extends AppCompatActivity {
    private final int pickImageCode = 1;
    private byte[] selectedImage;
    private EditText et_shoeName, et_shoePrice, et_shoeQuantity, et_shoeDesc,et_shoeStorePhoneNumber,et_shoeStoreLocation;
    private Spinner sp_shoeBrands;
    private ShoeController shoeController;
    private BrandController brandController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shoe_form);

        Button btn_browseShoeImage = findViewById(R.id.btn_browseShoeImage);
        Button btn_registerShoe = findViewById(R.id.btn_registerShoe);
        ImageButton btn_backFromShoeForm = findViewById(R.id.ib_backFromShoeForm);
        et_shoeName = findViewById(R.id.et_shoeName);
        et_shoePrice = findViewById(R.id.et_shoePrice);
        et_shoeQuantity = findViewById(R.id.et_shoeQuantity);
        et_shoeDesc = findViewById(R.id.et_shoeDesc);
        et_shoeStorePhoneNumber=findViewById(R.id.et_shoeStorePhoneNumber);
        et_shoeStoreLocation=findViewById(R.id.et_shoeStoreLocation);
        sp_shoeBrands = findViewById(R.id.sp_shoeBrands);


        shoeController = new ShoeController(new ShoeRepository(AddShoeFormActivity.this));
        brandController = new BrandController(new BrandRepository(getApplicationContext()));
        ArrayAdapter<Brand> adapter = new ArrayAdapter<Brand>(this, R.layout.brand_item_for_dropdown, brandController.getAllBrands());

        sp_shoeBrands.setAdapter(adapter);
        btn_backFromShoeForm.setOnClickListener(v -> finish());
        btn_registerShoe.setOnClickListener(v -> {
            if (isFormValid()) {
                Shoe newShoe = new Shoe(et_shoeName.getText().toString(),
                        Double.parseDouble(et_shoePrice.getText().toString()),
                        Integer.parseInt(et_shoeQuantity.getText().toString()),
                        sp_shoeBrands.getSelectedItem().toString(),
                        et_shoeDesc.getText().toString(),
                        selectedImage,
                        et_shoeStorePhoneNumber.getText().toString(),
                        et_shoeStoreLocation.getText().toString()
                );
                boolean isSuccessful = shoeController.addNewShoe(newShoe);
                if (isSuccessful) {
                    Toast.makeText(getApplicationContext(), "Record added successfully!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Failed to insert", Toast.LENGTH_SHORT).show();

                }
            }


        });
        btn_browseShoeImage.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(intent, pickImageCode);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pickImageCode && resultCode == RESULT_OK) {
            try {
                selectedImage = ImageConverter.fromBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData()));

            } catch (IOException ex) {
                Log.w("Image Picker", ex.getStackTrace().toString());
            }
        }
    }

    private boolean isFormValid() {

        if (TextUtils.isEmpty(et_shoeName.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter shoe name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(et_shoePrice.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter shoe price", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(et_shoeQuantity.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter shoe Quantity", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (sp_shoeBrands.getSelectedItem() == null) {

            Toast.makeText(getApplicationContext(), "Please select a Brand, Add a brand if its empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(et_shoeDesc.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter shoe Description", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(et_shoeStorePhoneNumber.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter store phone number", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(et_shoeStoreLocation.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter store location", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (selectedImage == null || selectedImage.length == 0) {
            Toast.makeText(getApplicationContext(), "Please select shoe Image", Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;
    }
}
