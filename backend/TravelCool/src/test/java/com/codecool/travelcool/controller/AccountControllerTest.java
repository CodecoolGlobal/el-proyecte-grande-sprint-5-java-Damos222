package com.codecool.travelcool.controller;

import com.codecool.travelcool.service.AccommodationService;
import com.codecool.travelcool.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    AccountService accountService;

    @Test
    void registerAccount() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/accounts/register"))
                .andExpect(status().isOk());
    verify(accountService).register("test@test.com", "Password123!");
    }

    @Test
    void emailAvailable() {
    }

    @Test
    void login() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                        .get("/accounts/?email=test%40test.com&password=Password123"))
                .andExpect(status().isOk());
        verify(accountService).checkEmailAndPassword("test@test.com", "Password123!");
    }
}