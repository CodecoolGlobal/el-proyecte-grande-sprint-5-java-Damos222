package com.codecool.travelcool.dto;

import com.codecool.travelcool.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Component
@Data
@NoArgsConstructor
public class CheckoutDto {
    private String title;
    private int capacity;
    private String description;
    private AccommodationType type;
    private Double pricePerNight;

    private String houseNumber;
    private String city;
    private String street;
    private int zipCode;
    private String country;

    private LocalDate startDate;
    private LocalDate endDate;
    private Timestamp timestamp;

    private String email;
    private String password;
    private String firstName;
    private String lastName;


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

    public Booking getBooking() {
        Booking booking = new Booking();
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setTimestamp(timestamp);
        booking.setBooker(getAccount());
        booking.setAccommodation(getAccommodation());
        return booking;
    }

    public Account getAccount() {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setPassword(firstName);
        account.setPassword(lastName);
        return account;
    }
}
