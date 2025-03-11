package com.app.fitLife.service.impl;

import com.app.fitLife.model.UserEntity;
import com.app.fitLife.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        // Verificar los roles que tiene asignados
        user.getRoles().forEach(role -> {
            String roleName = "ROLE_" + role.getRoleEnum().name();
            authorityList.add(new SimpleGrantedAuthority(roleName));

            // Verificar los permisos asociados a los roles
            role.getPermissionsSet().forEach(permission -> {
                authorityList.add(new SimpleGrantedAuthority(permission.getPermissionsEnum().name()));
            });
        });

        return new User(user.getUsername(), user.getPassword(),
                user.isEnabled(),
                user.isAccountNoExpired(),
                user.isCredentialNoExpired(),
                user.isAccountNoLocked(),
                authorityList) {
        };
            }
}
