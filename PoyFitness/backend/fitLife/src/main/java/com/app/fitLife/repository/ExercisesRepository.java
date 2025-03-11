package com.app.fitLife.repository;

import com.app.fitLife.model.Diets;
import com.app.fitLife.model.Exercises;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercisesRepository extends JpaRepository<Exercises, Long> {
}
