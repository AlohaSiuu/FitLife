package com.app.fitLife.service;

import com.app.fitLife.model.Exercises;

import java.util.List;

public interface ExercisesService {
    List<Exercises> getAll();
    Exercises getExerciseById(Long id);
    Exercises updateExercise(Long id, Exercises exercises);
    void deleteExercise(Long id);
    Exercises addExercise(Exercises exercises);
}
