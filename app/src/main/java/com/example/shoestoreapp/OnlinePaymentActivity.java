package com.example.shoestoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.shoestoreapp.Controller.CartController;
import com.example.shoestoreapp.Repository.CartRepository;

public class OnlinePaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_payment);
        WebView wvOnlinePayment = findViewById(R.id.wv_onlinePayment);
        wvOnlinePayment.setWebViewClient(new WebViewClient());
        WebSettings settings = wvOnlinePayment.getSettings();
        settings.setJavaScriptEnabled(true);
        checkOutAllItems();
        wvOnlinePayment.loadUrl("https://ib.myamole.com/bwinternet");
    }

    private void checkOutAllItems() {
        CartController controller = new CartController(new CartRepository(getApplicationContext()));
        controller.checkOutCartItems();
    }
}
