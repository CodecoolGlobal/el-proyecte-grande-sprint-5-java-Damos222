package com.codecool.travelcool.controller;

import com.codecool.travelcool.dto.AccommodationDto;
import com.codecool.travelcool.model.Accommodation;
import com.codecool.travelcool.model.AccommodationFeatures;
import com.codecool.travelcool.model.Account;
import com.codecool.travelcool.model.Address;
import com.codecool.travelcool.service.AccommodationService;
import com.codecool.travelcool.service.AccountService;
import com.codecool.travelcool.service.AddressService;
import com.codecool.travelcool.service.FeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
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


    @Autowired
    public AccommodationsController(AccommodationService accommodationService, AccountService accountService, FeaturesService featuresService, AddressService addressService) {
        this.accommodationService = accommodationService;
        this.accountService = accountService;
        this.featuresService = featuresService;
        this.addressService = addressService;
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

    @PostMapping("/addImages")
    public void getImages(@RequestParam Map<String, MultipartFile> formData) throws IOException {
        for (Map.Entry<String, MultipartFile> entry : formData.entrySet()) {
            images.add(entry.getValue().getBytes());
        }
    }

    @PostMapping("/add")
    public void add(@RequestBody AccommodationDto formData) {
        System.out.println("accommodations/add: " + formData);
        Accommodation accommodation = formData.getAccommodation();
        Address address = formData.getAddress();
        AccommodationFeatures features = formData.getFeatures();

        featuresService.save(features);

        //todo: check if address already in db
        addressService.save(address);

        accommodation.setFeatures(features);
        accommodation.setAddress(address);

        //todo: find better solution to save all images
        accommodation.setImage(images.get(0));

        images.clear();

        //todo: get account id from request and account from db
        Account account = new Account();
        account.setEmail("mail@emample.com");
        account.setPassword("password");
        accountService.save(account);
        accommodation.setHost(account);

        accommodationService.save(accommodation);
    }
}
