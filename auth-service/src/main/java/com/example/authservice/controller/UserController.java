package com.example.authservice.controller;

import com.example.authservice.dto.AddUserDto;
import com.example.authservice.dto.AddUserResponseDto;
import com.example.authservice.dto.JwtTokenDto;
import com.example.authservice.dto.LoginUserDto;
import com.example.authservice.model.User;
import com.example.authservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
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
        User addedUser = userService.add(addUserDto);
        return modelMapper.map(addedUser, AddUserResponseDto.class);
    }

    @PostMapping(value = "/login")
    @ResponseStatus(value = HttpStatus.OK)
    public JwtTokenDto login(@RequestBody LoginUserDto loginUserDto) {
        JwtTokenDto tokenDto  = userService.login(loginUserDto);
        return tokenDto;
    }
}
