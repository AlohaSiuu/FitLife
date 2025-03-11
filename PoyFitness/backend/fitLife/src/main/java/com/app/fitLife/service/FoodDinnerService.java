package com.app.fitLife.service;

import com.app.fitLife.model.FoodDinner;

import java.util.List;

public interface FoodDinnerService {

    List<FoodDinner> getAll();
    FoodDinner getFoodDinnerById(Long id);
    FoodDinner updateFoodDinner(Long id, FoodDinner foodDinner);
    void deleteFoodDinner(long id);
    FoodDinner addFoodDinner(FoodDinner foodDinner);
}
