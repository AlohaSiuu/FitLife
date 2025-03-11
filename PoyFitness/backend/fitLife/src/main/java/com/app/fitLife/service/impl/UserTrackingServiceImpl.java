package com.app.fitLife.service.impl;

import com.app.fitLife.model.UserTracking;
import com.app.fitLife.repository.UserTrackingRepository;
import com.app.fitLife.service.UserTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTrackingServiceImpl implements UserTrackingService {

    @Autowired
    private UserTrackingRepository userTrackingRepository;

    @Override
    public List<UserTracking> getAll() {
        return userTrackingRepository.findAll();
    }

    @Override
    public UserTracking getUserTrackingById(Long id) {
        return userTrackingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserTracking not found with ID:" + id));

    }

    @Override
    public UserTracking updateUserTracking(Long id, UserTracking userTracking) {
        UserTracking userTrackingExist = userTrackingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserTracking not found with ID:" + id));

        userTrackingExist.setUserEntity(userTracking.getUserEntity());
        userTrackingExist.setBim(userTracking.getBim());
        userTrackingExist.setDate(userTracking.getDate());
        userTrackingExist.setWeight(userTracking.getWeight());
        userTrackingExist.setCalories_burned(userTracking.getCalories_burned());

        return userTrackingRepository.save(userTrackingExist);
    }

    @Override
    public void deleteUserTracking(Long id) {
        userTrackingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserTracking not found with ID:" + id));

        userTrackingRepository.deleteById(id);
    }

    @Override
    public UserTracking addUserTracking(UserTracking userTracking) {
        return userTrackingRepository.save(userTracking);
    }
}
