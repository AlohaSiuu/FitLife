package com.app.fitLife.service.impl;

import com.app.fitLife.model.FoodDinner;
import com.app.fitLife.repository.FoodDinnerRepository;
import com.app.fitLife.service.FoodDinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodDinnerServiceImpl implements FoodDinnerService {

    @Autowired
    private FoodDinnerRepository foodDinnerRepository;

    @Override
    public List<FoodDinner> getAll() {
        return foodDinnerRepository.findAll();
    }

    @Override
    public FoodDinner getFoodDinnerById(Long id) {
        return foodDinnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FoodDinner not found with ID:" + id));

    }

    @Override
    public FoodDinner updateFoodDinner(Long id, FoodDinner foodDinner) {
        FoodDinner foodDinnerExist = foodDinnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FoodDinner not found with ID:" + id));

        foodDinnerExist.setName(foodDinner.getName());
        foodDinnerExist.setDiet(foodDinner.getDiet());
        foodDinnerExist.setCalories(foodDinner.getCalories());
        foodDinnerExist.setProducts(foodDinner.getProducts());

        return foodDinnerRepository.save(foodDinnerExist);
    }

    @Override
    public void deleteFoodDinner(long id) {
        foodDinnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FoodDinner not found with ID:" + id));

        foodDinnerRepository.deleteById(id);

    }

    @Override
    public FoodDinner addFoodDinner(FoodDinner foodDinner) {
        return foodDinnerRepository.save(foodDinner);
    }
}
