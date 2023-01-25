package com.codecool.travelcool.controller;

import com.codecool.travelcool.model.Accommodation;
import com.codecool.travelcool.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/accommodations")
public class AccommodationsController {
    private final AccommodationService accommodationService;

    @Autowired
    public AccommodationsController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping("/all")
    public List<Accommodation> findAll() {
        return accommodationService.findAll();
    }

    @GetMapping("/byDate")
    public List<Accommodation> findByDate(@RequestParam Long startDate, @RequestParam Long endDate) {
        System.out.println(startDate + " " + endDate);
        LocalDate fromDate = Instant.ofEpochMilli(startDate).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate toDate = Instant.ofEpochMilli(endDate).atZone(ZoneId.systemDefault()).toLocalDate();
        List<Accommodation> a = accommodationService.findByBookingsStartDateAfterAndEndDateBefore(fromDate, toDate);
        System.out.println(a);
        return a;
    }

    @GetMapping("/priceBetween")
    public List<Accommodation> findBetweenPrices(@RequestParam double min, @RequestParam double max) {
        return accommodationService.findByPriceBetween(BigDecimal.valueOf(min), BigDecimal.valueOf(max));
    }

    @GetMapping("/{id}")
    public Accommodation findById(@PathVariable Long id) {
        return accommodationService.findById(id).orElse(null);
    }

    @GetMapping("/host/{hostId}")
    public List<Accommodation> findByHost(@PathVariable Long hostId) {
        return accommodationService.findByHost(hostId);
    }

    @GetMapping("/filterByCountry/{country}")
    public List<Accommodation> findByCountry(@PathVariable String country) {
        return accommodationService.findByCountry(country);
    }

    @GetMapping("/capacity/{min}")
    public List<Accommodation> findByMinCapacity(@PathVariable int min) {
        return accommodationService.findByMinimumCapacity(min);
    }
}
