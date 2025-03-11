package com.app.fitLife.controller;

import com.app.fitLife.model.PointSystem;
import com.app.fitLife.service.PointSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("point-system")
@PreAuthorize("isAuthenticated()")
public class PointSystemController {

    @Autowired
    private PointSystemService pointSystemService;

    @GetMapping
    public ResponseEntity<List<PointSystem>> getAll(){
        return ResponseEntity.ok(pointSystemService.getAll());
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PointSystem> getPointSystemById(@PathVariable Long id){
        PointSystem pointSystem = pointSystemService.getPointSystemById(id);
        if (pointSystem == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pointSystem);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PointSystem> updatePointSystem(@PathVariable Long id, @RequestBody PointSystem pointSystem){
        PointSystem pointSystemExist = pointSystemService.getPointSystemById(id);
        if (pointSystemExist == null){
            return ResponseEntity.notFound().build();
        }

        pointSystemExist.setUserEntity(pointSystem.getUserEntity());
        pointSystemExist.setAccumulated_points(pointSystem.getAccumulated_points());
        pointSystemExist.setAvailable_rewards(pointSystem.getAvailable_rewards());

        return ResponseEntity.ok(pointSystemService.updatePointSystem(id, pointSystemExist));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PointSystem> addPointSystem(@RequestBody PointSystem pointSystem){
       return ResponseEntity.ok(pointSystemService.addUPointSystem(pointSystem));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSystemPoint(@PathVariable Long id){
        PointSystem pointSystem = pointSystemService.getPointSystemById(id);
        if (pointSystem == null){
            return ResponseEntity.notFound().build();
        }
        pointSystemService.deletePointSystem(id);
        return ResponseEntity.noContent().build();
    }

}
