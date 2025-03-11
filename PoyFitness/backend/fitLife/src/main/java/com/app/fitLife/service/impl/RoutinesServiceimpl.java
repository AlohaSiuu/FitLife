package com.app.fitLife.service.impl;

import com.app.fitLife.model.Routines;
import com.app.fitLife.repository.RoutinesRepository;
import com.app.fitLife.service.RoutinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutinesServiceimpl implements RoutinesService {

    @Autowired
    private RoutinesRepository routinesRepository;

    @Override
    public List<Routines> getAll() {
        return routinesRepository.findAll();
    }

    @Override
    public Routines getRoutineById(Long id) {
        return routinesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rountine not found with ID:" + id));
    }

    @Override
    public Routines updateRoutine(Long id, Routines routines) {
        Routines routinesExist = routinesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rountine not found with ID:" + id));

        routinesExist.setName(routines.getName());
        routinesExist.setDescription(routines.getDescription());
        routinesExist.setExercises(routines.getExercises());
        routinesExist.setDifficulty_level(routines.getDifficulty_level());

        return routinesRepository.save(routinesExist);
    }

    @Override
    public void deleteRoutine(Long id) {
        routinesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rountine not found with ID:" + id));
        routinesRepository.deleteById(id);

    }

    @Override
    public Routines addURoutine(Routines routines) {
        return routinesRepository.save(routines);
    }
}
