package com.app.fitLife.service.impl;

import com.app.fitLife.model.RoleEntity;
import com.app.fitLife.model.UserEntity;
import com.app.fitLife.repository.RoleRepository;
import com.app.fitLife.repository.UserRepository;
import com.app.fitLife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID:" + id));
    }



    @Override
    public UserEntity updateUser(Long id, UserEntity userEntity) {
        UserEntity userEntityExist = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID:" + id));

        userEntityExist.setUsername(userEntity.getUsername());
        userEntityExist.setAge(userEntity.getAge());
        userEntityExist.setDiets(userEntity.getDiets());
        userEntityExist.setHeight(userEntity.getHeight());
        userEntityExist.setEmail(userEntity.getEmail());

        userEntityExist.setPassword(userEntity.getPassword());
        userEntityExist.setWeight(userEntity.getWeight());
        userEntityExist.setPointSystem(userEntity.getPointSystem());
        userEntityExist.setTrakings(userEntity.getTrakings());

        return userRepository.save(userEntityExist);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID:" + id));
    userEntity.getRoles().clear();
    userRepository.save(userEntity);

    userRepository.deleteById(id);
    }

    @Override
    public UserEntity addUser(UserEntity userEntity) {
        Set<RoleEntity> managedRoles = new HashSet<>();

        for (RoleEntity role : userEntity.getRoles()) {
            RoleEntity managedRole = roleRepository.findById(role.getId())
                    .orElseThrow(() -> new RuntimeException("Role not found with ID: " + role.getId()));
            managedRoles.add(managedRole);
        }

        userEntity.setRoles(managedRoles); // Reemplazar roles con los gestionados por Hibernate

        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


}
