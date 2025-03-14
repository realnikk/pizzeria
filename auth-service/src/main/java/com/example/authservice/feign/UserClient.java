package com.example.authservice.feign;

import com.example.authservice.dto.UserSecurityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/user/security/{email}")
    UserSecurityDto getSecurityUserByEmail(@PathVariable String email);
}
