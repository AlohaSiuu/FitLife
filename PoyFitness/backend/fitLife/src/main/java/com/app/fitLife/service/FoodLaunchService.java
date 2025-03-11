package com.app.fitLife.service;

import com.app.fitLife.model.FoodLaunch;

import java.util.List;

public interface FoodLaunchService {
    List<FoodLaunch> getAll();
    FoodLaunch getFoodLaunchById(Long id);
    FoodLaunch updateFoodLaunch(Long id, FoodLaunch foodLaunch);
    void deleteFoodLaunch(Long id);
    FoodLaunch addFoodLaunch(FoodLaunch foodLaunch);
}
