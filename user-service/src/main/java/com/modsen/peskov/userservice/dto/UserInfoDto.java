package com.modsen.peskov.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserInfoDto {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String role;
}
