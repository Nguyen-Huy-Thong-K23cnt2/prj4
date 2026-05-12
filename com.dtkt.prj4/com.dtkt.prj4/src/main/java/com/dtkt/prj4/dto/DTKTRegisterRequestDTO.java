package com.dtkt.prj4.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTKTRegisterRequestDTO {

    private String username;

    private String email;

    private String password;
}