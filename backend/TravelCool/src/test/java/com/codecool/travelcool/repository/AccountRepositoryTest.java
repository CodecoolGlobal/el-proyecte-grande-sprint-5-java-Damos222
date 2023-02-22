package com.codecool.travelcool.repository;

import com.codecool.travelcool.model.Account;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountRepositoryTest {
    private final AccountRepository accountRepository;

    private static List<Account> accounts = new ArrayList<>();

    @Autowired
    AccountRepositoryTest(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @BeforeAll
    void beforeAll() {
        accounts.addAll(List.of(Account.builder()
                        .password("password")
                        .email("email")
                        .build(),
                Account.builder()
                        .email("email2")
                        .password("password2")
                        .build()));
        accountRepository.saveAll(accounts);
    }

    @AfterAll
    void afterAll() {
        accountRepository.deleteAll();
    }

    @Test
    void existsAccountByEmail() {
        assertTrue(accountRepository.existsAccountByEmail("email"));
        assertFalse(accountRepository.existsAccountByEmail("something"));
    }

    @Test
    void existsAccountByEmailAndPassword() {
    }

    @Test
    void findAccountById() {
    }

    @Test
    void findAccountByEmail() {
    }
}