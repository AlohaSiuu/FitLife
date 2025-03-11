package com.app.fitLife.service.impl;

import com.app.fitLife.model.FoodMorning;
import com.app.fitLife.repository.FoodMorningRepository;
import com.app.fitLife.service.FoodMorningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodMorningServiceImpl implements FoodMorningService {

    @Autowired
    private FoodMorningRepository foodMorningRepository;

    @Override
    public List<FoodMorning> getAll() {
        return foodMorningRepository.findAll();
    }

    @Override
    public FoodMorning getFoodMorningById(Long id) {
        return foodMorningRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FoodMorning not found with ID:" + id));

    }

    @Override
    public FoodMorning updateFoodMorning(Long id, FoodMorning foodMorning) {
        FoodMorning foodMorningExist = foodMorningRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FoodMorning not found with ID:" + id));

        foodMorningExist.setName(foodMorning.getName());
        foodMorningExist.setDiet(foodMorning.getDiet());
        foodMorningExist.setCalories(foodMorning.getCalories());
        foodMorningExist.setProducts(foodMorning.getProducts());

        return foodMorningRepository.save(foodMorningExist);    }

    @Override
    public void deleteFoodMorning(Long id) {
        foodMorningRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FoodMorning not found with ID:" + id));

        foodMorningRepository.deleteById(id);
    }

    @Override
    public FoodMorning addFoodMorning(FoodMorning foodMorning) {
        return foodMorningRepository.save(foodMorning);
    }
}
