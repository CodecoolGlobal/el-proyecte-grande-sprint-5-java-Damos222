package com.codecool.travelcool.service;

import com.codecool.travelcool.model.Account;
import com.codecool.travelcool.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public boolean emailAvailable(String email) {
        return !accountRepository.existsAccountByEmail(email);
    }
}
