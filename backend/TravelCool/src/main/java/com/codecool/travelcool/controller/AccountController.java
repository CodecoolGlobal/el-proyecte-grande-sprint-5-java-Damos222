package com.codecool.travelcool.controller;

import com.codecool.travelcool.model.Account;
import com.codecool.travelcool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin    // TODO: remove this in all Controllers when security is added
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/registration")
    public void registerAccount(@RequestParam String email, @RequestParam String password) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        accountService.save(account);
    }

    @GetMapping("/emailAvailable")
    public boolean emailAvailable(@RequestParam String email) {
        return accountService.emailAvailable(email);
    }
}
