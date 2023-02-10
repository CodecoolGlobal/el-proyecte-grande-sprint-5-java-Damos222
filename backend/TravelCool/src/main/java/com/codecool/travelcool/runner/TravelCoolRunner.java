package com.codecool.travelcool.runner;

import com.codecool.travelcool.model.*;
import com.codecool.travelcool.repository.AccommodationRepository;
import com.codecool.travelcool.repository.AccountRepository;
import com.codecool.travelcool.repository.AddressRepository;
import com.codecool.travelcool.repository.BookingRepository;
import com.codecool.travelcool.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@Configuration
@RequiredArgsConstructor
public class TravelCoolRunner implements CommandLineRunner {
    private final AccommodationRepository accommodationRepository;
    private final AddressRepository addressRepository;
    private final BookingRepository bookingRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private List<Accommodation> accommodations;
    private List<Address> addresses;
    private List<Booking> bookings;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws IOException {
        createAddresses();
        createAccommodations();
        createBookings();
        createAccounts();
    }

    public void createBookings() {
        List<LocalDate> startDates = List.of(
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 3, 10)
        );

        List<LocalDate> endDates = List.of(
                LocalDate.of(2023, 2, 5),
                LocalDate.of(2023, 3, 20)
        );

        bookings = List.of(
                new Booking(startDates.get(0), endDates.get(0), new Timestamp(System.currentTimeMillis()), accommodations.get(0)),
                new Booking(startDates.get(1), endDates.get(1), new Timestamp(System.currentTimeMillis()), accommodations.get(1))
        );

        bookingRepository.saveAll(bookings);
        accommodations.get(0).getBookings().add(bookings.get(0));
        accommodations.get(1).setBookings(Set.of(bookings.get(1)));

    }

    public void createAddresses() {
        addresses = List.of(
                new Address("Spain", 2343, "Cádiz", "C. Baleares", "2"),
                new Address("France", 1234, "Marseille", "Rue Lafon", "1"),
                new Address("Germany", 4332, "Berlin", "Emser Straße", "55"),
                new Address("Portugal", 6443, "Nazaré", "R. da Paz", "91")
        );

        addressRepository.saveAll(addresses);
    }

    public void createAccommodations() throws IOException {
        accommodations = List.of(
                new Accommodation(2, "Rose Apartment", "Luxury apartment", getByteArrayOfImage("images/bedroom-1.jpg"), new BigDecimal(500), AccommodationType.APARTMENT, addresses.get(0)),
                new Accommodation(4, "Cozy Room", "Near the beach", getByteArrayOfImage("images/bedroom-2.jpg"), new BigDecimal(100), AccommodationType.ROOM, addresses.get(1)),
                new Accommodation(5, "City Apartment", "Apartment in the city center", getByteArrayOfImage("images/bedroom-3.jpg"), new BigDecimal(600), AccommodationType.APARTMENT, addresses.get(2)),
                new Accommodation(1, "Sunny room", "Sunny single room for adventurous travellers", getByteArrayOfImage("images/bedroom-4.jpg"), new BigDecimal(50), AccommodationType.ROOM, addresses.get(3))
        );

        accommodationRepository.saveAll(accommodations);
    }

    public void createAccounts() {
        accountService.save(Account.builder()
                .email("hello@there.com")
                .password(passwordEncoder.encode("helloThere1"))
                .role(Role.USER)
                .build());
    }

    public byte[] getByteArrayOfImage(String imagePath) throws IOException {
        return Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(imagePath)).readAllBytes();
    }
}
