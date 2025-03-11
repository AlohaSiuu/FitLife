package com.app.fitLife.controller;


import com.app.fitLife.model.Exercises;
import com.app.fitLife.service.ExercisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("exercises")
@PreAuthorize("isAuthenticated()")
public class ExercisesController {

    @Autowired
    private ExercisesService exercisesService;

    @GetMapping
    public ResponseEntity<List<Exercises>> getAll(){
        return ResponseEntity.ok(exercisesService.getAll());
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Exercises> getExerciseById(@PathVariable Long id){
    Exercises exercises = exercisesService.getExerciseById(id);
    return exercises != null ? ResponseEntity.ok(exercises) : ResponseEntity.notFound().build();

    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Exercises> updateExercise (@PathVariable Long id, @RequestBody Exercises exercises){
        Exercises exercisesExist = exercisesService.getExerciseById(id);

        if (exercisesExist == null){
            return ResponseEntity.notFound().build();
        }

        exercisesExist.setName(exercises.getName());
        exercisesExist.setDescription(exercises.getDescription());
        exercisesExist.setType(exercises.getType());
        exercisesExist.setRoutine(exercises.getRoutine());
        exercisesExist.setVideo(exercises.getVideo());

        return ResponseEntity.ok(exercisesService.updateExercise(id, exercisesExist));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Exercises> addExercise(@RequestBody Exercises exercises){
        return ResponseEntity.ok(exercisesService.addExercise(exercises));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id){
        exercisesService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}
