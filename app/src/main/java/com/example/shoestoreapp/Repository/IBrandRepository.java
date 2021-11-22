package com.example.shoestoreapp.Repository;

import com.example.shoestoreapp.Models.Brand;

import java.util.List;

public interface IBrandRepository {
    boolean add(Brand brand);
    boolean remove(int brandId);
    List<Brand> getAll();

}
