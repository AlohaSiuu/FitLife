package com.app.fitLife.service;


import com.app.fitLife.model.Routines;

import java.util.List;

public interface RoutinesService {

    List<Routines> getAll();
    Routines getRoutineById(Long id);
    Routines updateRoutine(Long id, Routines routines);
    void deleteRoutine(Long id);
    Routines addURoutine(Routines routines);
}
