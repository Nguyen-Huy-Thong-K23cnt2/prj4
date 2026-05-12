package com.dtkt.prj4.controller;

import com.dtkt.prj4.dto.DTKTLoginResponseDTO;
import com.dtkt.prj4.dto.DTKTRegisterRequestDTO;
import com.dtkt.prj4.service.DTKTAuthService;
import org.springframework.web.bind.annotation.*;
import com.dtkt.prj4.dto.DTKTLoginRequestDTO;

@RestController
@RequestMapping("/api/auth")
public class DTKTAuthController {

    private final DTKTAuthService authService;

    public DTKTAuthController(DTKTAuthService authService) {
        this.authService = authService;
    }
   @PostMapping("/register")
    public String register(@RequestBody DTKTRegisterRequestDTO request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public DTKTLoginResponseDTO login(@RequestBody DTKTLoginRequestDTO request) {
        return authService.login(request);
    }
}