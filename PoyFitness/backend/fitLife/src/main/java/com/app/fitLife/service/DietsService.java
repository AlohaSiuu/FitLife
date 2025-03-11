package com.app.fitLife.service;

import com.app.fitLife.model.Diets;
import com.app.fitLife.utils.TypeDiet;

import java.util.List;
import java.util.Optional;

public interface DietsService {

    List<Diets> getAll();
    Diets getDietById(Long id);
    Diets updateDiet(Long id, Diets diets);
    void deleteDiet(Long id);
    Diets addDiet(Diets diets);
    List<Diets> findByType(TypeDiet type);

}
