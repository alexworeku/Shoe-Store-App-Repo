package com.example.shoestoreapp.Repository;

import com.example.shoestoreapp.Models.Brand;
import com.example.shoestoreapp.Models.Shoe;

import java.util.List;

public interface IShoeRepository {
    boolean add(Shoe shoe);

    Shoe get(int id);

    List<Shoe> getAll();

    boolean remove(int id);

    boolean update(Shoe shoe);


    List<Shoe> getByBrand(String brandName);

    List<Shoe> findWith(String name);
}