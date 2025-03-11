package com.app.fitLife.repository;

import com.app.fitLife.model.FoodLaunch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodLaunchRepository extends JpaRepository<FoodLaunch, Long> {
}
