package com.modsen.peskov.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegDto {
    private String name;
    private String email;
    private String phone;
    private String password;
}
