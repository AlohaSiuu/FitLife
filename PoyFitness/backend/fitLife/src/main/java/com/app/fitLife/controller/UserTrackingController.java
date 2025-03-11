package com.app.fitLife.controller;


import com.app.fitLife.model.UserTracking;
import com.app.fitLife.service.UserTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user-tracking")
@PreAuthorize("isAuthenticated()")
public class UserTrackingController {

    @Autowired
    private UserTrackingService userTrackingService;

    @GetMapping
    public ResponseEntity<List<UserTracking>> getAll(){
        return ResponseEntity.ok(userTrackingService.getAll());
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserTracking> getUserTrackingById(@PathVariable Long id){
        UserTracking userTracking = userTrackingService.getUserTrackingById(id);
        if (userTracking == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userTracking);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserTracking> updateUserTracking(@PathVariable Long id, @RequestBody UserTracking userTracking){
        UserTracking userTrackingExist = userTrackingService.getUserTrackingById(id);
        if (userTrackingExist == null){
            return ResponseEntity.notFound().build();
        }

        userTrackingExist.setUserEntity(userTracking.getUserEntity());
        userTrackingExist.setDate(userTracking.getDate());
        userTrackingExist.setBim(userTracking.getBim());
        userTrackingExist.setWeight(userTracking.getWeight());
        userTrackingExist.setCalories_burned(userTracking.getCalories_burned());

        return ResponseEntity.ok(userTrackingService.updateUserTracking(id, userTrackingExist));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserTracking> addUserTracking(@RequestBody UserTracking userTracking){
        return ResponseEntity.ok(userTrackingService.addUserTracking(userTracking));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUserTracking(@PathVariable Long id){
        UserTracking userTracking = userTrackingService.getUserTrackingById(id);
        if (userTracking == null){
            return ResponseEntity.notFound().build();
        }
        userTrackingService.deleteUserTracking(id);
        return ResponseEntity.noContent().build();
    }
}
