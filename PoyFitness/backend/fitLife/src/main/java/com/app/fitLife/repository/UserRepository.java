package com.app.fitLife.repository;

import com.app.fitLife.model.UserEntity;
import com.app.fitLife.utils.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByEmail(String email);
    Optional<UserEntity> findByUsername(String name);


}
