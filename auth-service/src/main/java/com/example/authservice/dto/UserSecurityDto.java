package com.example.authservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSecurityDto {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String role;
}
