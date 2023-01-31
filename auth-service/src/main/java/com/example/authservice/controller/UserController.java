package com.example.authservice.controller;

import com.example.authservice.dto.AddUserDto;
import com.example.authservice.dto.AddUserResponseDto;
import com.example.authservice.dto.JwtTokenDto;
import com.example.authservice.dto.LoginUserDto;
import com.example.authservice.model.User;
import com.example.authservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

@RestController
@Slf4j
@RequestMapping(value = "api/v1/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper = new ModelMapper();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public AddUserResponseDto add(@RequestBody AddUserDto addUserDto) {
        User addedUser = userService.add(addUserDto.getUsername(), addUserDto.getPassword(), "ROLE_ADMIN");
        log.info("Registration successful. Username: {}", addUserDto.getUsername());
        return modelMapper.map(addedUser, AddUserResponseDto.class);
    }

    @PostMapping(value = "/login")
    @ResponseStatus(value = HttpStatus.OK)
    public JwtTokenDto login(@RequestBody LoginUserDto loginUserDto) {
        log.info("Login successful. Username: {}", loginUserDto.getUsername());
        return userService.login(loginUserDto);
    }

    @GetMapping(value = "/confirm/{token}")
    public ResponseEntity<HttpStatus> confirmToken(@PathVariable String token) {

        userService.verifyUserAccount(token);
        return ResponseEntity.ok().build();
    }
}
