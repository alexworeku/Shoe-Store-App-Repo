package com.example.shoestoreapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoestoreapp.Adapter.ShoeAdapterForAdmin;
import com.example.shoestoreapp.Controller.ShoeController;
import com.example.shoestoreapp.Models.Shoe;
import com.example.shoestoreapp.Repository.ShoeRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoesListForAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoesListForAdminFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private  RecyclerView rv_shoesListForAdmin;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShoesListForAdminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShoesListForAdminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoesListForAdminFragment newInstance(String param1, String param2) {
        ShoesListForAdminFragment fragment = new ShoesListForAdminFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_shoes_list_for_admin, container, false);
        FloatingActionButton fab_openShoeForm=rootView.findViewById(R.id.fab_openShoeForm);
        fab_openShoeForm.setOnClickListener(v->{
            startActivity(new Intent(getContext(),AddShoeFormActivity.class));
        });


         rv_shoesListForAdmin = rootView.findViewById(R.id.rv_shoesListForAdmin);


        rv_shoesListForAdmin.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));


        // Inflate the layout for this fragment
        return rootView;
    }
private  void reloadData(){
    List<Shoe> shoes = new ShoeController(new ShoeRepository(getContext())).getAllShoes();
   ShoeAdapterForAdmin shoeAdapterForAdmin=new ShoeAdapterForAdmin(shoes,getContext());
    rv_shoesListForAdmin.setAdapter(shoeAdapterForAdmin);
}

    @Override
    public void onResume() {
        super.onResume();
        reloadData();
    }
}
