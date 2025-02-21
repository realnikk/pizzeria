package com.modsen.peskov.userservice.service;

import com.modsen.peskov.userservice.dto.UserRegDto;
import com.modsen.peskov.userservice.entity.User;
import com.modsen.peskov.userservice.exception.UserAlreadyExistsException;
import com.modsen.peskov.userservice.mapper.UserMapper;
import com.modsen.peskov.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void signUp(UserRegDto userRegDto){
        if(userRepository.existsByEmail(userRegDto.getEmail())){
            throw new UserAlreadyExistsException("User with this email already exists");
        } else if(userRepository.existsByPhone(userRegDto.getPhone())){
            throw new UserAlreadyExistsException("User with this phone already exists");
        } else {
            User user = UserMapper.convertToEntity(userRegDto);
            userRepository.save(user);
        }
    }
}
