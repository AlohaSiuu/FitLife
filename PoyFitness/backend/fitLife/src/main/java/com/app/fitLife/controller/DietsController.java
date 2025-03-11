package com.app.fitLife.controller;

import com.app.fitLife.model.Diets;
import com.app.fitLife.service.DietsService;
import com.app.fitLife.service.impl.DietsServiceimpl;
import com.app.fitLife.utils.TypeDiet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("diets")
@PreAuthorize("isAuthenticated()")
public class DietsController {

    @Autowired
    private DietsService dietsService;


    @GetMapping
    public ResponseEntity<List<Diets>> getAllDiets() {
        return ResponseEntity.ok(dietsService.getAll());
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Diets> getDietById(@PathVariable Long id) {
        return ResponseEntity.ok(dietsService.getDietById(id));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Diets> addDiet(@RequestBody Diets diet) {
        return ResponseEntity.ok(dietsService.addDiet(diet));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Diets> updateDiet(@PathVariable Long id, @RequestBody Diets diet) {
        return ResponseEntity.ok(dietsService.updateDiet(id, diet));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteDiet(@PathVariable Long id) {
        dietsService.deleteDiet(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Diets>> getDietsByType(@PathVariable TypeDiet type) {
        return ResponseEntity.ok(dietsService.findByType(type));
    }
}
