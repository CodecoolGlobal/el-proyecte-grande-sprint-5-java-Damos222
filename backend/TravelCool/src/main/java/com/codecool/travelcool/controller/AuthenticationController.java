package com.codecool.travelcool.controller;

import com.codecool.travelcool.dto.LoginRequest;
import com.codecool.travelcool.dto.RegisterRequest;
import com.codecool.travelcool.model.Account;
import com.codecool.travelcool.model.Role;
import com.codecool.travelcool.service.AccountService;
import com.codecool.travelcool.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationController(TokenService tokenService,
                                    AuthenticationManager authenticationManager,
                                    AccountService accountService,
                                    PasswordEncoder passwordEncoder) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.OK)
    public String register(@RequestBody RegisterRequest request) {
        var account = Account.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();
        accountService.save(account);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        System.out.println(authentication.getName());
        return tokenService.generateToken(authentication);
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        System.out.println(authentication.getName());
        return tokenService.generateToken(authentication);
    }

    @GetMapping ("/emailTaken/{email}")
    public Map<String, Boolean> emailTaken(@PathVariable String email) {
        boolean emailTaken = accountService.existsByEmail(email);
        return Collections.singletonMap("emailTaken", emailTaken);
    }
}
