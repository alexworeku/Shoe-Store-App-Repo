package com.example.shoestoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoestoreapp.Controller.AdminController;
import com.example.shoestoreapp.Models.Admin;
import com.example.shoestoreapp.Repository.AdminRepository;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_login = findViewById(R.id.btn_login);
        EditText et_userName = findViewById(R.id.et_username);
        EditText et_password = findViewById(R.id.et_password);

        btn_login.setOnClickListener((v) -> {
            AdminController adminController = new AdminController(new AdminRepository(getApplicationContext()));
            Admin admin = adminController.getAdmin();
            if (admin.getUsername().equals(et_userName.getText().toString()) && admin.getPassword().equals(et_password.getText().toString())) {
                Intent intent = new Intent(getApplicationContext(), AdminDashboardActivity.class);
                startActivity(intent);
                boolean statusChanged = adminController.changeLoginStatusTo(true);
                if (statusChanged) {
                    Toast.makeText(getApplicationContext(), "Welcome back Admin", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
