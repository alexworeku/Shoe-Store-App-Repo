package com.example.shoestoreapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.shoestoreapp.Adapter.FragmentAdapter;
import com.example.shoestoreapp.Controller.AdminController;
import com.example.shoestoreapp.Repository.AdminRepository;
import com.google.android.material.tabs.TabLayout;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            AdminController adminController = new AdminController(new AdminRepository(getApplicationContext()));
            boolean isLoggedOut = adminController.changeLoginStatusTo(false);
            if (isLoggedOut) {
                Toast.makeText(getApplicationContext(), "Logging out", Toast.LENGTH_SHORT).show();
                finish();

            } else {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();

            }
        });
        TabLayout tabLayout = findViewById(R.id.tabL_dashboard);
        ViewPager viewPager = findViewById(R.id.pager_dashboard);
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentAdapter adapter = new FragmentAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("Shoes"));
        tabLayout.addTab(tabLayout.newTab().setText("Brands"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        ImageButton btn_backFromDashboard = findViewById(R.id.ib_backFromDashboard);
        btn_backFromDashboard.setOnClickListener(v -> {
            finish();

        });
    }


}
