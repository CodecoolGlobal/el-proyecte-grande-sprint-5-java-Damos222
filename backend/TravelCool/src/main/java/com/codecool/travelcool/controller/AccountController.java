package com.codecool.travelcool.controller;

import com.codecool.travelcool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public void registerAccount(@RequestParam String email, @RequestParam String password) {
        accountService.register(email, password);
    }

    @GetMapping ("/emailAvailable")
    public boolean emailAvailable(@RequestParam String email) {
        return accountService.emailAvailable(email);
    }

    @GetMapping("/checkLoginData")
    public boolean login(@RequestParam String email, @RequestParam String password) {
        return accountService.checkEmailAndPassword(email, password);
    }
}
