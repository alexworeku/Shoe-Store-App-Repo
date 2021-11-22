package com.example.shoestoreapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.shoestoreapp.BrandListForAdminFragment;
import com.example.shoestoreapp.ShoesListForAdminFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==1){
            return new BrandListForAdminFragment();
        }
        return new ShoesListForAdminFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
