package com.app.fitLife.service;


import com.app.fitLife.model.FoodMorning;

import java.util.List;

public interface FoodMorningService {
    List<FoodMorning> getAll();
    FoodMorning getFoodMorningById(Long id);
    FoodMorning updateFoodMorning(Long id, FoodMorning foodMorning);
    void deleteFoodMorning(Long id);
    FoodMorning addFoodMorning(FoodMorning foodMorning);
}
