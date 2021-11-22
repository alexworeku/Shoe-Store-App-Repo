package com.example.shoestoreapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoestoreapp.Adapter.BrandAdapterForAdmin;
import com.example.shoestoreapp.Controller.BrandController;
import com.example.shoestoreapp.Models.Brand;
import com.example.shoestoreapp.Repository.BrandRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BrandListForAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BrandListForAdminFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView rv_brands;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BrandListForAdminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryListForAdminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BrandListForAdminFragment newInstance(String param1, String param2) {
        BrandListForAdminFragment fragment = new BrandListForAdminFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_brand_list_for_admin, container, false);
        FloatingActionButton fabOpenBrandForm = rootView.findViewById(R.id.fab_openBrandForm);
        fabOpenBrandForm.setOnClickListener(view -> {
            startActivity(new Intent(getContext(), AddBrandFormActivity.class));
        });
        rv_brands = rootView.findViewById(R.id.rv_brandListForAdmin);
        rv_brands.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        reloadData();
    }

    private void reloadData() {
        List<Brand> brandList = new BrandController(new BrandRepository(getContext())).getAllBrands();
        BrandAdapterForAdmin adapter = new BrandAdapterForAdmin(brandList, getContext());
        rv_brands.setAdapter(adapter);

    }
}
