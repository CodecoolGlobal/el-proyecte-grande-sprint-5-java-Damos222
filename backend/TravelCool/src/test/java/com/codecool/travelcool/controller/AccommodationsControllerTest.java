package com.codecool.travelcool.controller;
import com.codecool.travelcool.service.AccommodationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AccommodationsController.class)
class AccommodationsControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    AccommodationService accommodationService;

    void checkIfStatusIsOk(String url) throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(url))
                .andExpect(status().isOk())
        ;}

    @Test
    void findAll() throws Exception{
        checkIfStatusIsOk("/accommodations/all");
        verify(accommodationService).findAll();
    }

    @Test
    void findBetweenPrices() throws Exception{
        checkIfStatusIsOk("/priceBetween/?min=1&max=10");
        verify(accommodationService).findByPriceBetween(BigDecimal.ONE, BigDecimal.TEN);
    }

    @Test
    void findById() throws Exception{
        checkIfStatusIsOk("/accommodations/1");
        verify(accommodationService).findById(1L);
    }

    @Test
    void findByHost() throws Exception{
        checkIfStatusIsOk("/accommodations/host/1");
        verify(accommodationService).findByHost(1L);
    }

    @Test
    void findByCountry() throws Exception{
        checkIfStatusIsOk("/accommodations/filterByCountry/england");
        verify(accommodationService).findByCountry("england");
    }

    @Test
    void findByMinCapacity() throws Exception{
        checkIfStatusIsOk("/accommodations/capacity/1");
        verify(accommodationService).findByMinimumCapacity(1);
    }
}