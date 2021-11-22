package com.example.shoestoreapp.Controller;

import com.example.shoestoreapp.Models.Brand;
import com.example.shoestoreapp.Repository.IBrandRepository;

import java.util.List;

public class BrandController {
    private IBrandRepository brandRepository;
    public BrandController(IBrandRepository repository){
        brandRepository=repository;
    }
    public List<Brand> getAllBrands(){
        return brandRepository.getAll();
    }
    public  boolean addNewBrand(Brand brand){
       return brandRepository.add(brand);
    }
    public  boolean removeBrandById(int brandId){
        return brandRepository.remove(brandId);
    }
}
