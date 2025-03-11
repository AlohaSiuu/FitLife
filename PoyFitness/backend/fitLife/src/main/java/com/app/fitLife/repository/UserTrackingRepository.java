package com.app.fitLife.repository;

import com.app.fitLife.model.UserTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTrackingRepository extends JpaRepository<UserTracking, Long> {
}
