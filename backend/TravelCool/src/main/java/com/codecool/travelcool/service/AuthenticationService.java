package com.codecool.travelcool.service;

import com.codecool.travelcool.dto.AuthenticationRequestDto;
import com.codecool.travelcool.dto.AuthenticationResponseDto;
import com.codecool.travelcool.dto.RegisterRequestDto;
import com.codecool.travelcool.model.Account;
import com.codecool.travelcool.model.Role;
import com.codecool.travelcool.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto register(RegisterRequestDto request) {
        var account = Account.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        accountRepository.save(account);
        var jwtToken = jwtService.generateToken(account);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var account = accountRepository.findAccountByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(account);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}
