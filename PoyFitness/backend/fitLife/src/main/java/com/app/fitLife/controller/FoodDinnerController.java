package com.app.fitLife.controller;

import com.app.fitLife.model.FoodDinner;
import com.app.fitLife.service.FoodDinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food-dinner")
@PreAuthorize("isAuthenticated()")
public class FoodDinnerController {

    @Autowired
    private FoodDinnerService foodDinnerService;

    @GetMapping
    public ResponseEntity<List<FoodDinner>> getAll(){
        List<FoodDinner> foodDinners = foodDinnerService.getAll();
        return ResponseEntity.ok(foodDinners);
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FoodDinner> getFoodDinnerById(@PathVariable Long id){
        FoodDinner foodDinner = foodDinnerService.getFoodDinnerById(id);
        return foodDinner != null ? ResponseEntity.ok(foodDinner) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FoodDinner> updateFoodDinner(@PathVariable Long id, @RequestBody FoodDinner foodDinner){
        FoodDinner foodDinnerExist = foodDinnerService.getFoodDinnerById(id);
        if (foodDinnerExist == null){
            return ResponseEntity.notFound().build();
        }

        foodDinnerExist.setName(foodDinner.getName());
        foodDinnerExist.setCalories(foodDinner.getCalories());
        foodDinnerExist.setDiet(foodDinner.getDiet());
        foodDinnerExist.setProducts(foodDinner.getProducts());

        return ResponseEntity.ok(foodDinnerService.updateFoodDinner(id, foodDinnerExist));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FoodDinner> addFoodDinner(@RequestBody FoodDinner foodDinner){
        return ResponseEntity.ok(foodDinnerService.addFoodDinner(foodDinner));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteFoodDinner(@PathVariable Long id){
        FoodDinner foodDinner = foodDinnerService.getFoodDinnerById(id);
        if (foodDinner == null){
            return ResponseEntity.notFound().build();
        }

        foodDinnerService.deleteFoodDinner(id);
        return ResponseEntity.noContent().build();
    }

}
