package com.modsen.peskov.userservice.mapper;

import com.modsen.peskov.userservice.dto.UserInfoDto;
import com.modsen.peskov.userservice.dto.UserProfileDto;
import com.modsen.peskov.userservice.dto.UserSecurityDto;
import com.modsen.peskov.userservice.dto.UserRegDto;
import com.modsen.peskov.userservice.entity.User;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class UserMapper {
    public static UserSecurityDto convertToSecurityDto(User user) {
        return UserSecurityDto.builder()
                .id(String.valueOf(user.getId()))
                .email(user.getEmail())
                .phone(user.getPhone())
                .password(user.getPassword())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
    public static UserProfileDto convertToProfileDto(User user) {
        return UserProfileDto.builder()
                .email(user.getEmail())
                .phone(user.getPhone())
                .name(user.getName())
                .build();
    }

    public static UserInfoDto convertToInfoDto(User user) {
        return UserInfoDto.builder()
                .id(String.valueOf(user.getId()))
                .email(user.getEmail())
                .phone(user.getPhone())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
    public static User convertToEntity(UserRegDto userDTO, String encodedPassword) {
        return User.builder()
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .password(encodedPassword)
                .name(userDTO.getName())
                .role("ROLE_USER")
                .build();
    }

}
