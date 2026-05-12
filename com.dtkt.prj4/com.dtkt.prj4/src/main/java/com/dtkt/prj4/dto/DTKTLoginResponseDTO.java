package com.dtkt.prj4.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTKTLoginResponseDTO {

    private Long id;

    private String username;

    private String role;

    private String message;

    private String token;
}