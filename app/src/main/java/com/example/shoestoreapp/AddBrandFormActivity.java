package com.example.shoestoreapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.shoestoreapp.Controller.BrandController;
import com.example.shoestoreapp.Helper.ImageConverter;
import com.example.shoestoreapp.Models.Brand;
import com.example.shoestoreapp.Repository.BrandRepository;

import java.io.IOException;

public class AddBrandFormActivity extends AppCompatActivity {
    private final int pickImageCode = 1;
    private byte[] selectedImage;
    private EditText et_brandName;
    private BrandController brandController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_brand_form);
        et_brandName = findViewById(R.id.et_brandName);
        Button btnBrowseBrandImage = findViewById(R.id.btn_browseBrandImage);
        Button btnRegisterBrand = findViewById(R.id.btn_registerBrand);
       ImageButton btnBack=findViewById(R.id.ib_backFromBrandForm);
        brandController=new BrandController(new BrandRepository(getApplicationContext()));
        btnBrowseBrandImage.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(intent, pickImageCode);

        });
        btnBack.setOnClickListener(view ->finish());

        btnRegisterBrand.setOnClickListener(view -> {
            if (isFormValid()) {
                if (isFormValid()) {
                    Brand brand =new Brand(et_brandName.getText().toString(),selectedImage);
                    boolean isSuccessful = brandController.addNewBrand(brand);
                    if (isSuccessful) {
                        Toast.makeText(getApplicationContext(), "Record added successfully!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to insert", Toast.LENGTH_SHORT).show();

                    }
                }
            }

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

        if (TextUtils.isEmpty(et_brandName.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter brand name", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (selectedImage == null || selectedImage.length == 0) {
            Toast.makeText(getApplicationContext(), "Please select brand image", Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;
    }

}
