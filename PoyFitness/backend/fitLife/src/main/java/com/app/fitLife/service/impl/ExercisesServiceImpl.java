package com.app.fitLife.service.impl;

import com.app.fitLife.model.Exercises;
import com.app.fitLife.repository.ExercisesRepository;
import com.app.fitLife.service.ExercisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercisesServiceImpl implements ExercisesService {

    @Autowired
    private ExercisesRepository exercisesRepository;


    @Override
    public List<Exercises> getAll() {
        return exercisesRepository.findAll();
    }

    @Override
    public Exercises getExerciseById(Long id) {
        return exercisesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found with ID:" + id));
    }

    @Override
    public Exercises updateExercise(Long id, Exercises exercises) {
        Exercises exercisesExist = exercisesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diet not found with ID:" + id));

        exercisesExist.setName(exercises.getName());
        exercisesExist.setDescription(exercises.getDescription());
        exercisesExist.setType(exercises.getType());
        exercisesExist.setVideo(exercises.getVideo());
        exercisesExist.setRoutine(exercises.getRoutine());

        return exercisesRepository.save(exercisesExist);
    }

    @Override
    public void deleteExercise(Long id) {
        exercisesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found with ID:" + id));
        exercisesRepository.deleteById(id);
    }

    @Override
    public Exercises addExercise(Exercises exercises) {
        return exercisesRepository.save(exercises);
    }
}
