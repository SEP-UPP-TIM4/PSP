package com.example.authservice.service;

import com.example.authservice.dto.JwtTokenDto;
import com.example.authservice.dto.LoginUserDto;
import com.example.authservice.exception.*;
import de.taimos.totp.TOTP;
import com.example.authservice.model.Role;
import com.example.authservice.model.User;
import com.example.authservice.model.VerificationToken;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.security.util.TokenUtils;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenService verificationTokenService;
    private static final int REGISTRATION_TOKEN_EXPIRES = 60;

    public UserService(UserRepository userRepository, RoleService roleService, EmailService emailService, AuthenticationManager authenticationManager, TokenUtils tokenUtils, VerificationTokenService verificationTokenService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.emailService = emailService;
        this.authenticationManager = authenticationManager;
        this.tokenUtils = tokenUtils;
        this.verificationTokenService = verificationTokenService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User add(String username, String password, String userRole) {
        Role role = roleService.findByName(userRole);
        if(userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException();
        User newUser = User.builder().role(role).username(username).password(passwordEncoder.encode(password)).activated(false).build();
        userRepository.save(newUser);
        VerificationToken verificationToken = new VerificationToken(newUser);
        verificationTokenService.saveVerificationToken(verificationToken);
        emailService.sendEmail(newUser.getUsername(), "Account verification", "http://"+ System.getenv("ip_address") + ":4200/confirm/" + verificationToken.getToken() + " Click on this link to activate your account");
        return newUser;
    }

    public JwtTokenDto login(LoginUserDto loginUserDto) {
        Optional<User> user = userRepository.findByUsername(loginUserDto.getUsername());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUserDto.getUsername(), loginUserDto.getPassword()));
        if(loginUserDto.getCode() == null || !loginUserDto.getCode().equals(getTOTPCode(user.get().getSecret()))) {
            throw new CodeNotMatchingException();
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new JwtTokenDto(getToken(user.get()));
    }

    private String getTOTPCode(String secretKey) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(secretKey);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);
    }

    private String getToken(User user) {
        return tokenUtils.generateToken(user.getUsername(), user.getRole().getName());
    }

    public User findByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new NotFoundException(User.class.getSimpleName());
        return user.get();
    }

    public String verifyUserAccount(String token) throws TokenExpiredException {

        VerificationToken verificationToken = verificationTokenService.findVerificationTokenByToken(token);
        if (verificationToken == null) {
            throw new TokenNotValid();
        }
        User user = findByUsername(verificationToken.getUser().getUsername());

        verificationTokenService.delete(verificationToken);

        if (getDifferenceInMinutes(verificationToken) < REGISTRATION_TOKEN_EXPIRES) {
            user.setActivated(true);
            userRepository.save(user);
            return user.getUsername();
        } else {
            throw new TokenExpiredException();
        }
    }

    private long getDifferenceInMinutes(VerificationToken verificationToken) {
        LocalDateTime tokenCreated = LocalDateTime.ofInstant(verificationToken.getCreatedDateTime().toInstant(), ZoneId.systemDefault());
        return ChronoUnit.MINUTES.between(tokenCreated, LocalDateTime.now());
    }

}
