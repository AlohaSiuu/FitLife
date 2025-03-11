package com.app.fitLife.controller;

import com.app.fitLife.model.Routines;
import com.app.fitLife.service.RoutinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("routines")
@PreAuthorize("isAuthenticated()")
public class RoutinesController {

    @Autowired
    private RoutinesService routinesService;

    @GetMapping
    private ResponseEntity<List<Routines>> getAll(){
        return ResponseEntity.ok(routinesService.getAll());
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Routines> getRoutinesByid(@PathVariable Long id){
        Routines routines = routinesService.getRoutineById(id);
        if (routines == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(routines);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Routines> updateRoutines(@PathVariable Long id, @RequestBody Routines routines){
       Routines routinesExist = routinesService.getRoutineById(id);
       if (routinesExist == null){
           return ResponseEntity.notFound().build();
       }

       routinesExist.setName(routines.getName());
       routinesExist.setDescription(routines.getDescription());
       routinesExist.setExercises(routines.getExercises());
       routinesExist.setDifficulty_level(routines.getDifficulty_level());

       return ResponseEntity.ok(routinesService.updateRoutine(id, routinesExist));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Routines> addRoutine(@RequestBody Routines routines){
        return ResponseEntity.ok(routinesService.addURoutine(routines));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteRoutine(@PathVariable Long id){
        Routines routines = routinesService.getRoutineById(id);
        if (routines == null){
            return ResponseEntity.notFound().build();
        }

        routinesService.deleteRoutine(id);
        return ResponseEntity.noContent().build();
    }
}
