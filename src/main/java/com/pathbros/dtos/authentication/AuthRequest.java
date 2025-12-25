package com.pathbros.dtos.authentication;

import lombok.Data;

@Data
public class AuthRequest {

    private String email;
    private String password;


}
