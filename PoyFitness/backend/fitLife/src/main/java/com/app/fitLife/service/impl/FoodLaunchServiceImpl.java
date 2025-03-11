package com.app.fitLife.service.impl;

import com.app.fitLife.model.FoodLaunch;
import com.app.fitLife.repository.FoodLaunchRepository;
import com.app.fitLife.service.FoodLaunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodLaunchServiceImpl implements FoodLaunchService {

    @Autowired
    private FoodLaunchRepository foodLaunchRepository;

    @Override
    public List<FoodLaunch> getAll() {
        return foodLaunchRepository.findAll();
    }

    @Override
    public FoodLaunch getFoodLaunchById(Long id) {
        return foodLaunchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FoodLaunch not found with ID:" + id));

    }

    @Override
    public FoodLaunch updateFoodLaunch(Long id, FoodLaunch foodLaunch) {
        FoodLaunch foodLaunchExist = foodLaunchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FoodLaunch not found with ID:" + id));

        foodLaunchExist.setName(foodLaunch.getName());
        foodLaunchExist.setDiet(foodLaunch.getDiet());
        foodLaunchExist.setCalories(foodLaunch.getCalories());
        foodLaunchExist.setProducts(foodLaunch.getProducts());

        return foodLaunchRepository.save(foodLaunchExist);
    }

    @Override
    public void deleteFoodLaunch(Long id) {
        foodLaunchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FoodLaunch not found with ID:" + id));

        foodLaunchRepository.deleteById(id);
    }

    @Override
    public FoodLaunch addFoodLaunch(FoodLaunch foodLaunch) {
        return foodLaunchRepository.save(foodLaunch);
    }
}
