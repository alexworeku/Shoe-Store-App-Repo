package com.example.shoestoreapp.Controller;

import com.example.shoestoreapp.Models.Shoe;
import com.example.shoestoreapp.Repository.IShoeRepository;

import java.util.List;

public class ShoeController {
    private IShoeRepository shoeRepository;

    public ShoeController(IShoeRepository repository) {
        shoeRepository = repository;
    }

    public Shoe getShoeById(int shoeId) {
        return shoeRepository.get(shoeId);
    }

    public List<Shoe> getAllShoes() {
        return shoeRepository.getAll();
    }

    public List<Shoe> getShoesByBrand(String brandName) {
        return shoeRepository.getByBrand(brandName);
    }
    public List<Shoe> getShoesByName(String shoeName) {
        return shoeRepository.findWith(shoeName);
    }

    public boolean addNewShoe(Shoe shoe) {
        return shoeRepository.add(shoe);
    }

    public boolean removeShoeById(int shoeId) {
        return shoeRepository.remove(shoeId);
    }

}
