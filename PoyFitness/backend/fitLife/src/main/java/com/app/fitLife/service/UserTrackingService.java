package com.app.fitLife.service;


import com.app.fitLife.model.UserTracking;

import java.util.List;

public interface UserTrackingService {

    List<UserTracking> getAll();
    UserTracking getUserTrackingById(Long id);
    UserTracking updateUserTracking(Long id, UserTracking userTracking);
    void deleteUserTracking(Long id);
    UserTracking addUserTracking(UserTracking userTracking);
}
