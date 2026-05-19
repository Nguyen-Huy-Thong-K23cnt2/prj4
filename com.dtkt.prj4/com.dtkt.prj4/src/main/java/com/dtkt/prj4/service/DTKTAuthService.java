package com.dtkt.prj4.service;

import com.dtkt.prj4.dto.DTKTRegisterRequestDTO;
import com.dtkt.prj4.entity.Role;
import com.dtkt.prj4.entity.Users;
import com.dtkt.prj4.repository.DTKTRoleRepository;
import com.dtkt.prj4.repository.DTKTUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DTKTAuthService {

    private final DTKTUserRepository userRepository;

    private final DTKTRoleRepository roleRepository;

    public DTKTAuthService(
            DTKTUserRepository userRepository,
            DTKTRoleRepository roleRepository
    ) {

        this.userRepository = userRepository;

        this.roleRepository = roleRepository;
    }

    // =========================
    // REGISTER
    // =========================
    public String register(
            DTKTRegisterRequestDTO request
    ) {

        Optional<Users> userExist =
                userRepository.findByUsername(
                        request.getUsername()
                );

        if (userExist.isPresent()) {

            return "Username already exists";
        }

        Users user = new Users();

        user.setUsername(
                request.getUsername()
        );

        user.setPassword(
                request.getPassword()
        );

        user.setEmail(
                request.getEmail()
        );

        user.setFullName(
                request.getFullName()
        );

        user.setPhone(
                request.getPhone()
        );

        user.setAddress(
                request.getAddress()
        );

        user.setIsActive(true);

        user.setCreatedAt(
                LocalDateTime.now()
        );

        Role role =
                roleRepository.findByName("USER")
                        .orElseThrow(() ->
                                new RuntimeException("Role not found"));

        user.setRole(role);

        userRepository.save(user);

        return "Register success";
    }

    // =========================
    // LOGIN
    // =========================
    public Users login(
            String username,
            String password
    ) {

        Users user =
                userRepository.findByUsername(username)
                        .orElse(null);

        if (user == null) {

            return null;
        }

        if (!user.getPassword().equals(password)) {

            return null;
        }

        return user;
    }
}