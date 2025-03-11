package com.app.fitLife.repository;

import com.app.fitLife.model.Challenges;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengesRepository extends JpaRepository<Challenges, Long> {
}
