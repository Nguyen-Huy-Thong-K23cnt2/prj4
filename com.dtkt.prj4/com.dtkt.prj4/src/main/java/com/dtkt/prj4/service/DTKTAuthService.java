package com.dtkt.prj4.service;

import com.dtkt.prj4.dto.DTKTLoginRequestDTO;
import com.dtkt.prj4.dto.DTKTLoginResponseDTO;
import com.dtkt.prj4.dto.DTKTRegisterRequestDTO;
import com.dtkt.prj4.entity.Role;
import com.dtkt.prj4.entity.Users;
import com.dtkt.prj4.repository.DTKTRoleRepository;
import com.dtkt.prj4.repository.DTKTUsersRepository;
import com.dtkt.prj4.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DTKTAuthService {

    private final DTKTUsersRepository DTKTUsersRepository;
    private final DTKTRoleRepository DTKTRoleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public DTKTAuthService(DTKTUsersRepository DTKTUsersRepository,
                           DTKTRoleRepository DTKTRoleRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           JwtService jwtService) {

        this.DTKTUsersRepository = DTKTUsersRepository;
        this.DTKTRoleRepository = DTKTRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // REGISTER
    public String register(DTKTRegisterRequestDTO request) {

        Users user = new Users();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        // mã hóa password
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        // mặc định role USER
        Role role = DTKTRoleRepository.findById(2L)
                .orElseThrow();

        user.setRole(role);

        DTKTUsersRepository.save(user);

        return "Register success";
    }

    // LOGIN
    public DTKTLoginResponseDTO login(DTKTLoginRequestDTO request) {

        Users user = DTKTUsersRepository.findByUsername(
                        request.getUsername()
                )
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        boolean checkPassword = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!checkPassword) {
            throw new RuntimeException("Wrong password");
        }

        // tạo JWT token
        String token = jwtService.generateToken(
                user.getUsername()
        );

        DTKTLoginResponseDTO response = new DTKTLoginResponseDTO();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole().getName());
        response.setMessage("Login success");
        response.setToken(token);

        return response;
    }
}