package com.codecool.travelcool.controller;

import com.codecool.travelcool.dto.AuthenticationRequestDto;
import com.codecool.travelcool.dto.AuthenticationResponseDto;
import com.codecool.travelcool.dto.RegisterRequestDto;
import com.codecool.travelcool.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody RegisterRequestDto request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
