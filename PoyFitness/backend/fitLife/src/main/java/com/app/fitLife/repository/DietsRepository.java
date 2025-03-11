package com.app.fitLife.repository;

import com.app.fitLife.model.Diets;
import com.app.fitLife.utils.TypeDiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DietsRepository extends JpaRepository<Diets, Long> {
    List<Diets> findByType(TypeDiet type);
}
