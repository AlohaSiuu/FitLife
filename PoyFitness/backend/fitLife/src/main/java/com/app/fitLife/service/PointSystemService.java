package com.app.fitLife.service;



import com.app.fitLife.model.PointSystem;

import java.util.List;

public interface PointSystemService {

    List<PointSystem> getAll();
    PointSystem getPointSystemById(Long id);
    PointSystem updatePointSystem(Long id, PointSystem pointSystem);
    void deletePointSystem(Long id);
    PointSystem addUPointSystem(PointSystem pointSystem);
}
