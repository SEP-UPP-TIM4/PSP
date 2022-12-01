package com.example.authservice.controller;

import com.example.authservice.dto.CredentialsResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @PostMapping("validateToken")
    @ResponseStatus(value = HttpStatus.OK)
    public CredentialsResponseDto validate(@RequestParam String token) {
        System.out.println(token);
        return new CredentialsResponseDto("username", "password");
    }
}
