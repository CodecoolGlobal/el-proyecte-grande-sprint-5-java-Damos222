package com.codecool.travelcool.controller;

import com.codecool.travelcool.dto.AccountDto;
import com.codecool.travelcool.model.Account;
import com.codecool.travelcool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/account")
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
}
