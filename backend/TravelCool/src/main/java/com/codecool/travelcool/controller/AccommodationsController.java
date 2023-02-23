package com.codecool.travelcool.controller;

import com.codecool.travelcool.dto.AccommodationDto;
import com.codecool.travelcool.dto.BookingDto;
import com.codecool.travelcool.model.*;
import com.codecool.travelcool.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/accommodations")
public class AccommodationsController {
    private final AccommodationService accommodationService;
    private final List<byte[]> images = new ArrayList<>();
    private final AccountService accountService;
    private final FeaturesService featuresService;
    private final AddressService addressService;
    private final BookingService bookingService;


    @Autowired
    public AccommodationsController(AccommodationService accommodationService, AccountService accountService,
                                    FeaturesService featuresService, AddressService addressService, BookingService bookingService) {
        this.accommodationService = accommodationService;
        this.accountService = accountService;
        this.featuresService = featuresService;
        this.addressService = addressService;
        this.bookingService = bookingService;
    }

    @GetMapping("/public/all")
    public List<Accommodation> findAll() {
        return accommodationService.findAll();
    }

    @GetMapping("/public/byDate")
    public List<Accommodation> findByDate(@RequestParam Long startDate, @RequestParam Long endDate) {
        LocalDate fromDate = Instant.ofEpochMilli(startDate).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate toDate = Instant.ofEpochMilli(endDate).atZone(ZoneId.systemDefault()).toLocalDate();
        return accommodationService.findByBookingsStartDateAfterAndEndDateBefore(fromDate, toDate);
    }

    @GetMapping("/public/priceBetween")
    public List<Accommodation> findBetweenPrices(@RequestParam double min, @RequestParam double max) {
        return accommodationService.findByPriceBetween(BigDecimal.valueOf(min), BigDecimal.valueOf(max));
    }

    @GetMapping("/public/{id}")
    public Accommodation findById(@PathVariable Long id) {
        return accommodationService.findById(id).orElse(null);
    }

    @GetMapping("/public/host/{hostId}")
    public List<Accommodation> findByHost(@PathVariable Long hostId) {
        return accommodationService.findByHost(hostId);
    }

    @GetMapping("/public/filterByCountry/{country}")
    public List<Accommodation> findByCountry(@PathVariable String country) {
        return accommodationService.findByCountry(country);
    }

    @GetMapping("/public/capacity/{min}")
    public List<Accommodation> findByMinCapacity(@PathVariable int min) {
        return accommodationService.findByMinimumCapacity(min);
    }

    @PostMapping("/secured/addImages")
    public void getImages(@RequestParam Map<String, MultipartFile> formData) throws IOException {
        for (Map.Entry<String, MultipartFile> entry : formData.entrySet()) {
            images.add(entry.getValue().getBytes());
        }
    }

    @PostMapping("/secured/add")
    public void add(Authentication authentication, @RequestBody AccommodationDto formData) {
        Accommodation accommodation = formData.getAccommodation();
        Address address = formData.getAddress();
        AccommodationFeatures features = formData.getFeatures();
        featuresService.save(features);
        //check if address already in db
        Optional<Address> optionalAddress = addressService.findAddress(address);
        if (optionalAddress.isEmpty()) {
            addressService.save(address);
        } else {
            address = optionalAddress.get();
        }
        accommodation.setFeatures(features);
        accommodation.setAddress(address);
        accommodation.setImage(images.get(0));
        images.clear();
        String accountEmail = authentication.getName();
        Optional<Account> optionalAccount = accountService.findByEmail(accountEmail);
        accommodation.setHost(optionalAccount.orElseThrow());

        accommodationService.save(accommodation);
    }

    @GetMapping("/secured/byAccount")
    public List<Accommodation> getAccommodationsByAccount(Authentication auth) {
        return accommodationService.getByHost((Account) auth.getPrincipal());
    }
}
