package com.modsen.peskov.userservice.mapper;

import com.modsen.peskov.userservice.dto.UserRegDto;
import com.modsen.peskov.userservice.entity.User;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class UserMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static UserRegDto convertToDTO(User user) {
        return modelMapper.map(user, UserRegDto.class);
    }

    public static User convertToEntity(UserRegDto userDTO) {
        return User.builder()
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .password(userDTO.getPassword())
                .username(userDTO.getUsername())
                .role("USER")
                .build();
    }
}
