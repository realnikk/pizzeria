package com.modsen.peskov.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserSecurityDto {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String role;
}
