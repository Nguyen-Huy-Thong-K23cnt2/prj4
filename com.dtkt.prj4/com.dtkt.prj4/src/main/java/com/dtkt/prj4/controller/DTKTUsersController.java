package com.dtkt.prj4.controller;

import com.dtkt.prj4.dto.DTKTUsersResponseDTO;
import com.dtkt.prj4.service.DTKTUsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DTKTUsersController {

    private final DTKTUsersService DTKTUsersService;

    public DTKTUsersController(DTKTUsersService DTKTUsersService) {
        this.DTKTUsersService = DTKTUsersService;
    }

    @GetMapping("/api/users")
    public List<DTKTUsersResponseDTO> getUsers() {
        return DTKTUsersService.getAllUsers();
    }
}