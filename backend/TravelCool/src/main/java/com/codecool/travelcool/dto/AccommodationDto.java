package com.codecool.travelcool.dto;

import com.codecool.travelcool.model.Accommodation;
import com.codecool.travelcool.model.AccommodationFeatures;
import com.codecool.travelcool.model.AccommodationType;
import com.codecool.travelcool.model.Address;
import com.codecool.travelcool.service.AddressService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@Data
@NoArgsConstructor
public class AccommodationDto {
    private String title;
    private String country;
    private String city;
    private int zipCode;
    private String street;
    private String houseNumber;
    private boolean parking;
    private boolean pool;
    private boolean bedSheets;
    private boolean towels;
    private boolean hairDryer;
    private boolean kitchen;
    private boolean sauna;
    private int capacity;
    private String description;
    private Double pricePerNight;
    private AccommodationType type;

    public Accommodation getAccommodation() {
        Accommodation accommodation = new Accommodation();
        accommodation.setName(title);
        accommodation.setCapacity(capacity);
        accommodation.setDescription(description);
        accommodation.setType(type);
        accommodation.setPricePerNight(BigDecimal.valueOf(pricePerNight));

        return accommodation;
    }

    public Address getAddress() {
        Address address = new Address();
        address.setHouseNumber(houseNumber);
        address.setCity(city);
        address.setStreet(street);
        address.setZipCode(zipCode);
        address.setCountry(country);
        return address;
    }

    public AccommodationFeatures getFeatures() {
        AccommodationFeatures features = new AccommodationFeatures();
        features.setHairDryer(hairDryer);
        features.setPool(pool);
        features.setParking(parking);
        features.setSauna(sauna);
        features.setTowels(towels);
        features.setKitchen(kitchen);
        features.setBedSheets(bedSheets);
        return features;
    }
}
