package com.app.fitLife.service.impl;

import com.app.fitLife.model.Diets;
import com.app.fitLife.repository.DietsRepository;
import com.app.fitLife.service.DietsService;
import com.app.fitLife.utils.TypeDiet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietsServiceimpl implements DietsService {

    @Autowired
    private DietsRepository dietsRepository;

    @Override
    public List<Diets> getAll() {
        return dietsRepository.findAll();
    }

    @Override
    public Diets getDietById(Long id) {
        return dietsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diet not found with ID: " + id));
    }

    @Override
    public Diets addDiet(Diets diet) {
        // Asociar la dieta a sus comidas
        if (diet.getFoodMornings() != null) {
            diet.getFoodMornings().forEach(food -> food.setDiet(diet));
        }
        if (diet.getFoodLaunches() != null) {
            diet.getFoodLaunches().forEach(food -> food.setDiet(diet));
        }
        if (diet.getFoodDinners() != null) {
            diet.getFoodDinners().forEach(food -> food.setDiet(diet));
        }

        return dietsRepository.save(diet);
    }

    @Override
    public Diets updateDiet(Long id, Diets updatedDiet) {
        Diets existingDiet = dietsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diet not found with ID: " + id));

        existingDiet.setName(updatedDiet.getName());
        existingDiet.setType(updatedDiet.getType());
        existingDiet.setDescription(updatedDiet.getDescription());
        existingDiet.setTotal_calories(updatedDiet.getTotal_calories());

        // Actualizar listas de comidas
        if (updatedDiet.getFoodMornings() != null) {
            updatedDiet.getFoodMornings().forEach(food -> food.setDiet(existingDiet));
            existingDiet.getFoodMornings().clear();
            existingDiet.getFoodMornings().addAll(updatedDiet.getFoodMornings());
        }

        if (updatedDiet.getFoodLaunches() != null) {
            updatedDiet.getFoodLaunches().forEach(food -> food.setDiet(existingDiet));
            existingDiet.getFoodLaunches().clear();
            existingDiet.getFoodLaunches().addAll(updatedDiet.getFoodLaunches());
        }

        if (updatedDiet.getFoodDinners() != null) {
            updatedDiet.getFoodDinners().forEach(food -> food.setDiet(existingDiet));
            existingDiet.getFoodDinners().clear();
            existingDiet.getFoodDinners().addAll(updatedDiet.getFoodDinners());
        }

        return dietsRepository.save(existingDiet);
    }

    @Override
    public void deleteDiet(Long id) {
        Diets diet = dietsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diet not found with ID: " + id));
        dietsRepository.delete(diet);
    }

    @Override
    public List<Diets> findByType(TypeDiet type) {
        return dietsRepository.findByType(type);
    }
}
