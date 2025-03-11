package com.app.fitLife.controller;

import com.app.fitLife.model.UserEntity;
import com.app.fitLife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
@PreAuthorize("isAuthenticated()")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id){
        UserEntity user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Optional<UserEntity>> findUserByEmail(@PathVariable String email){
        Optional<UserEntity> user = userService.findUserByEmail(email);
        return user.isPresent() ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserEntity> updateUser (@PathVariable Long id, @RequestBody UserEntity userEntity){
        UserEntity userExist = userService.getUserById(id);
        if (userExist == null) {
            return ResponseEntity.notFound().build();
        }

        userExist.setUsername(userEntity.getUsername());
        userExist.setAge(userEntity.getAge());
        userExist.setDiets(userEntity.getDiets());
        userExist.setHeight(userEntity.getHeight());
        userExist.setEmail(userEntity.getEmail());
        userExist.setPassword(userEntity.getPassword());
        userExist.setWeight(userEntity.getWeight());
        userExist.setPointSystem(userEntity.getPointSystem());
        userExist.setTrakings(userEntity.getTrakings());

        return ResponseEntity.ok(userService.updateUser(id, userExist));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity userEntity){
        return ResponseEntity.ok(userService.addUser(userEntity));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
