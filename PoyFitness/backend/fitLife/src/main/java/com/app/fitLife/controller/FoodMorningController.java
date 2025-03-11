package com.app.fitLife.controller;


import com.app.fitLife.model.FoodMorning;
import com.app.fitLife.service.FoodMorningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("food-morning")
@PreAuthorize("isAuthenticated()")
public class FoodMorningController {

    @Autowired
    private FoodMorningService foodMorningService;

    @GetMapping
    public ResponseEntity<List<FoodMorning>> getAll(){
        return ResponseEntity.ok(foodMorningService.getAll());
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FoodMorning> getFoodMorninById(@PathVariable Long id){
        FoodMorning foodMorning = foodMorningService.getFoodMorningById(id);

        return foodMorning != null ? ResponseEntity.ok(foodMorning) : ResponseEntity.notFound().build();
    }
}
