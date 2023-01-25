package com.codecool.travelcool.incomingRequestData;

import com.codecool.travelcool.model.Accommodation;
import com.codecool.travelcool.model.AccommodationFeatures;
import com.codecool.travelcool.model.AccommodationType;
import com.codecool.travelcool.model.Address;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Data
public class AddAccommodationFormData {
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

    public Accommodation getAccommodation(List<MultipartFile> images) {
        Accommodation accommodation = new Accommodation();
        Address address = new Address();
        AccommodationFeatures features = new AccommodationFeatures();

        //build address
        address.setCity(city);
        address.setStreet(street);
        address.setCountry(country);
        address.setZipCode(zipCode);
        address.setHouseNumber(houseNumber);

        //build features
        features.setPool(pool);
        features.setParking(parking);
        features.setSauna(sauna);
        features.setTowels(towels);
        features.setKitchen(kitchen);
        features.setBedSheets(bedSheets);
        features.setHairDryer(hairDryer);

        accommodation.setAddress(address);
        accommodation.setFeatures(features);

        //set rest
        accommodation.setHost(null);//todo
        //todo accommodation.setImage(images);
        accommodation.setName(title);
        accommodation.setCapacity(capacity);
        accommodation.setDescription(description);
        accommodation.setType(type);
        accommodation.setPricePerNight(BigDecimal.valueOf(pricePerNight));

        return accommodation;
    }
}
