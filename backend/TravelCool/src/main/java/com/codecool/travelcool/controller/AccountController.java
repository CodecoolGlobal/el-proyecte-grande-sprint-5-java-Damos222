package com.codecool.travelcool.controller;

import com.codecool.travelcool.model.Account;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/account")
public class AccountController {
    @GetMapping("/greeting")
    public String greeting(Authentication authentication) {
//        Account account = (Account) authentication;
        return "Hello, " + authentication.getName();
    }
}
