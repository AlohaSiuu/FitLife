package com.app.fitLife.controller;

import com.app.fitLife.model.FoodLaunch;
import com.app.fitLife.service.FoodLaunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food-launch")
@PreAuthorize("isAuthenticated()")
public class FoodLaunchController {

    @Autowired
    private FoodLaunchService foodLaunchService;

    @GetMapping
    public ResponseEntity<List<FoodLaunch>> getAll(){
        return ResponseEntity.ok(foodLaunchService.getAll());
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FoodLaunch> getFoodLaunchById(@PathVariable Long id){
        FoodLaunch foodLaunch = foodLaunchService.getFoodLaunchById(id);
        return foodLaunch != null ? ResponseEntity.ok(foodLaunch) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FoodLaunch> updateFoodLaunch(@PathVariable Long id, @RequestBody FoodLaunch foodLaunch){
        FoodLaunch foodLaunchExist = foodLaunchService.getFoodLaunchById(id);
        if (foodLaunchExist == null){
            return ResponseEntity.notFound().build();
        }

        foodLaunchExist.setName(foodLaunch.getName());
        foodLaunchExist.setCalories(foodLaunch.getCalories());
        foodLaunchExist.setDiet(foodLaunch.getDiet());
        foodLaunchExist.setProducts(foodLaunch.getProducts());

        return ResponseEntity.ok(foodLaunchService.updateFoodLaunch(id, foodLaunchExist));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FoodLaunch> addFoodLaunch(@RequestBody FoodLaunch foodLaunch){
        return ResponseEntity.ok(foodLaunchService.addFoodLaunch(foodLaunch));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteFoodLaunch(@PathVariable Long id){
        FoodLaunch foodLaunch = foodLaunchService.getFoodLaunchById(id);
        if (foodLaunch == null){
            return ResponseEntity.notFound().build();
        }

        foodLaunchService.deleteFoodLaunch(id);
        return ResponseEntity.noContent().build();
    }
}
