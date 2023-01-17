package com.codecool.travelcool.controller;

import com.codecool.travelcool.model.Accommodation;
import com.codecool.travelcool.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3030")
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
