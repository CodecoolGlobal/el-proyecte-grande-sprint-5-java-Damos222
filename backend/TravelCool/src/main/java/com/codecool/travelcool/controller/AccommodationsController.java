package com.codecool.travelcool.controller;

import com.codecool.travelcool.incomingRequestData.AddAccommodationFormData;
import com.codecool.travelcool.model.Accommodation;
import com.codecool.travelcool.service.AccommodationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Void> uploadImages(@RequestParam Map<String, MultipartFile> formData) {
        for (Map.Entry<String, MultipartFile> entry : formData.entrySet()) {
            System.out.println(entry.getValue().getOriginalFilename());
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> add(@RequestParam Map<String, String> formData) {
        System.out.println(formData.toString());
        Gson gson = new Gson();
        String formDataJson = gson.toJson(formData);
        AddAccommodationFormData accommodation = gson.fromJson(formDataJson, AddAccommodationFormData.class);
        System.out.println(accommodation.toString());
        return ResponseEntity.ok(null);
    }

//    @PostMapping("/add")
//    public ResponseEntity<Void> add(@RequestBody AddAccommodationFormData formData) {
//        System.out.println(formData.toString());
//        return ResponseEntity.ok(null);
//    }
}
