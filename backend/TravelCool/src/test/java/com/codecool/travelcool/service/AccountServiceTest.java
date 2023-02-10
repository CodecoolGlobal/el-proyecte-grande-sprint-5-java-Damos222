package com.codecool.travelcool.service;

import com.codecool.travelcool.model.Account;
import com.codecool.travelcool.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void save() {
        //Arrange
        Account account = Account.builder()
                .email("hello@there.com")
                .password("password")
                .build();
        when(accountRepository.findAccountById(Long.parseLong("1"))).thenReturn(Optional.of(account));
        Optional<Account> expected = Optional.of(account);
        //Act
        Optional<Account> actual = accountService.findById(Long.parseLong("1"));
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void emailTakenFalseWhenNoEmailInDatabase() {
        //Arrange
        when(accountRepository.existsAccountByEmail("hello@there.com")).thenReturn(false);
        //Act and Assert
        assertFalse(accountService.existsByEmail("hello@there.com"));
    }

    @Test
    void emailTakenTrueWhenEmailInDatabase() {
        //Arrange
        when(accountRepository.existsAccountByEmail("hello@there.com")).thenReturn(true);
        //Act and Assert
        assertTrue(accountService.existsByEmail("hello@there.com"));
    }
}