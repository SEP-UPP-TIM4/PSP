package com.example.authservice.service;

import com.example.authservice.dto.JwtTokenDto;
import com.example.authservice.dto.LoginUserDto;
import com.example.authservice.exception.NotFoundException;
import com.example.authservice.exception.UsernameAlreadyExistsException;
import com.example.authservice.model.Role;
import com.example.authservice.model.User;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.security.util.TokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;

    public UserService(UserRepository userRepository, RoleService roleService, AuthenticationManager authenticationManager, TokenUtils tokenUtils) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.tokenUtils = tokenUtils;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User add(String username, String password, String userRole) {
        Role role = roleService.findByName(userRole);
        if(userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException();
        User newUser = User.builder().role(role).username(username).password(passwordEncoder.encode(password)).activated(true).build();
        return userRepository.save(newUser);
    }

    public JwtTokenDto login(LoginUserDto loginUserDto) {
        Optional<User> user = userRepository.findByUsername(loginUserDto.getUsername());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUserDto.getUsername(), loginUserDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new JwtTokenDto(getToken(user.get()));
    }

    private String getToken(User user) {
        return tokenUtils.generateToken(user.getUsername(), user.getRole().getName());
    }

    public User findByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new NotFoundException(User.class.getSimpleName());
        return user.get();
    }

}
