package com.codecool.travelcool.service;

import com.codecool.travelcool.dto.AccountDto;
import com.codecool.travelcool.model.Account;
import com.codecool.travelcool.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public boolean existsByEmail(String email) {
        return accountRepository.existsAccountByEmail(email);
    }

    public void register(String email, String password) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        save(account);
        System.out.println("account with email " + email + "password " + password + "registered");
    }

    public Optional<Account> findById(long id) {
        return accountRepository.findAccountById(id);
    }

    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public List<AccountDto> findAll() {
        return accountRepository.findAll().stream().map(Account::toAccountDto).toList();
    }
}
