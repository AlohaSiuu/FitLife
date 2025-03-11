package com.app.fitLife.service.impl;

import com.app.fitLife.model.PointSystem;
import com.app.fitLife.repository.PointSystemRepository;
import com.app.fitLife.service.PointSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointSystemImpl implements PointSystemService {

    @Autowired
    private PointSystemRepository pointSystemRepository;

    @Override
    public List<PointSystem> getAll() {
        return pointSystemRepository.findAll();
    }

    @Override
    public PointSystem getPointSystemById(Long id) {
        return pointSystemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PointSystem not found with ID:" + id));
    }

    @Override
    public PointSystem updatePointSystem(Long id, PointSystem pointSystem) {
        PointSystem pointSystemExist = pointSystemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PointSystem not found with ID:" + id));

        pointSystemExist.setAccumulated_points(pointSystem.getAccumulated_points());
        pointSystemExist.setAvailable_rewards(pointSystem.getAvailable_rewards());

        return pointSystemRepository.save(pointSystemExist);
    }

    @Override
    public void deletePointSystem(Long id) {
        pointSystemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PointSystem not found with ID:" + id));

        pointSystemRepository.deleteById(id);
    }

    @Override
    public PointSystem addUPointSystem(PointSystem pointSystem) {
        return null;
    }
}
