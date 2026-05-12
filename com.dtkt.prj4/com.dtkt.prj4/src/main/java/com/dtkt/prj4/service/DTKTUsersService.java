package com.dtkt.prj4.service;

import com.dtkt.prj4.dto.DTKTUsersResponseDTO;
import com.dtkt.prj4.repository.DTKTUsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DTKTUsersService {

    private final DTKTUsersRepository DTKTUsersRepository;

    public DTKTUsersService(DTKTUsersRepository DTKTUsersRepository) {
        this.DTKTUsersRepository = DTKTUsersRepository;
    }

    public List<DTKTUsersResponseDTO> getAllUsers() {

        return DTKTUsersRepository.findAll().stream().map(user -> {

            DTKTUsersResponseDTO dto = new DTKTUsersResponseDTO();

            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getRole().getName());

            return dto;

        }).toList();
    }
}