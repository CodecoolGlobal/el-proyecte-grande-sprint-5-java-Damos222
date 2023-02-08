package com.codecool.travelcool.controller;

import com.codecool.travelcool.dto.AccountDto;
import com.codecool.travelcool.model.Account;
import com.codecool.travelcool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin    // TODO: remove this in all Controllers when security is added
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public List<AccountDto> getAllAccounts() {
        return accountService.findAll();
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
