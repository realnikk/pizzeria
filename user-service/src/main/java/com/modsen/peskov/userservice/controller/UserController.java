package com.modsen.peskov.userservice.controller;

import com.modsen.peskov.userservice.dto.UserInfoDto;
import com.modsen.peskov.userservice.dto.UserProfileDto;
import com.modsen.peskov.userservice.dto.UserSecurityDto;
import com.modsen.peskov.userservice.dto.UserRegDto;
import com.modsen.peskov.userservice.exception.UserEmailAlreadyExistsException;
import com.modsen.peskov.userservice.exception.UserNotFoundException;
import com.modsen.peskov.userservice.exception.UserPhoneAlreadyExistsException;
import com.modsen.peskov.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
//    @GetMapping("/validate")
//    public ResponseEntity<Boolean> validateUser(@RequestParam String email,
//                                                @RequestParam String password) {
//        if (userService.validateUser(email, password)) {
//            return ResponseEntity.ok(true);
//        }
//        return ResponseEntity.ok(false);
//    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserRegDto userRegDto) {
        try{
            return ResponseEntity.ok(userService.addUser(userRegDto));
        }catch (UserEmailAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (UserPhoneAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/security/{email}")
    UserSecurityDto getSecurityUserByEmail(@PathVariable String email){
        return userService.getSecurityUserByEmail(email);
    }

    @GetMapping("/profile/{email}")
    UserProfileDto getProfileUserByEmail(@PathVariable String email){
        return userService.getProfileUserByEmail(email);
    }
    @GetMapping
    List<UserInfoDto> getUsersInfo(){
        return userService.getUsersInfo();
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> updateUserProfile(@PathVariable String email,
                                               @RequestBody UserProfileDto userProfileDto) {
        try{
            return ResponseEntity.ok(userService.updateUserProfile(email, userProfileDto));
        }catch (UserEmailAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (UserPhoneAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/admin/{email}")
    public ResponseEntity<?> updateUserToAdmin(@PathVariable String email) {
        try{
            return ResponseEntity.ok(userService.updateUserToAdmin(email));
        }catch (UserEmailAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (UserPhoneAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        try{
            userService.deleteUser(email);
            return ResponseEntity.ok(200);
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}