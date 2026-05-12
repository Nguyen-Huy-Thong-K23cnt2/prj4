package com.dtkt.prj4.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTKTUsersResponseDTO {

    private Long id;

    private String username;

    private String email;

    private String role;
}