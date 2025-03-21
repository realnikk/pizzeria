package com.modsen.peskov.userservice.service;

import com.modsen.peskov.userservice.dto.UserInfoDto;
import com.modsen.peskov.userservice.dto.UserProfileDto;
import com.modsen.peskov.userservice.dto.UserSecurityDto;
import com.modsen.peskov.userservice.dto.UserRegDto;
import com.modsen.peskov.userservice.entity.User;
import com.modsen.peskov.userservice.exception.UserEmailAlreadyExistsException;
import com.modsen.peskov.userservice.exception.UserNotFoundException;
import com.modsen.peskov.userservice.exception.UserPhoneAlreadyExistsException;
import com.modsen.peskov.userservice.mapper.UserMapper;
import com.modsen.peskov.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User addUser(UserRegDto userRegDto){
        if(userRepository.existsByEmail(userRegDto.getEmail())){
            throw new UserEmailAlreadyExistsException();
        } else if(userRepository.existsByPhone(userRegDto.getPhone())){
            throw new UserPhoneAlreadyExistsException();
        } else {
            String encryptedPassword = passwordEncoder.encode(userRegDto.getPassword());
            User user = UserMapper.convertToEntity(userRegDto, encryptedPassword);
            return userRepository.save(user);
        }
    }

    public boolean validateUser(String email, String password){
        if (userRepository.existsByEmail(email)){
            User user = userRepository.findByEmail(email);
            if (password.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public UserSecurityDto getSecurityUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        return UserMapper.convertToSecurityDto(user);
    }

    public UserProfileDto getProfileUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        return UserMapper.convertToProfileDto(user);
    }

    public List<UserInfoDto> getUsersInfo(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::convertToInfoDto).toList();
    }

    public User updateUserProfile(String email, UserProfileDto userProfileDto){
        if(!userRepository.existsByEmail(email)){
            throw new UserNotFoundException();
        } else {
            User user = userRepository.findByEmail(email);
            if(!email.equals(userProfileDto.getEmail()) &&
                    userRepository.existsByEmail(userProfileDto.getEmail())){
                throw new UserEmailAlreadyExistsException();
            }
            if(!user.getPhone().equals(userProfileDto.getPhone()) &&
                    userRepository.existsByPhone(userProfileDto.getPhone())){
                throw new UserPhoneAlreadyExistsException();
            }
            user.setName(userProfileDto.getName());
            user.setEmail(userProfileDto.getEmail());
            user.setPhone(userProfileDto.getPhone());
            return userRepository.save(user);
        }
    }

    public User updateUserToAdmin(String email){
        if(!userRepository.existsByEmail(email)){
            throw new UserNotFoundException();
        } else {
            User user = userRepository.findByEmail(email);
            user.setRole("ROLE_ADMIN");
            return userRepository.save(user);
        }
    }

    public void deleteUser(String email){
        if(!userRepository.existsByEmail(email)){
            throw new UserNotFoundException();
        } else {
            User user = userRepository.findByEmail(email);
            userRepository.delete(user);
        }
    }
}
