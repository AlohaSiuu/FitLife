package com.app.fitLife.service;


import com.app.fitLife.model.UserEntity;
import com.app.fitLife.utils.RoleEnum;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<UserEntity> getAll();
    UserEntity getUserById(Long id);
    UserEntity updateUser(Long id, UserEntity userEntity);
    void deleteUser(Long id);
    UserEntity addUser(UserEntity userEntity);
    Optional<UserEntity> findUserByEmail(String email);


}
