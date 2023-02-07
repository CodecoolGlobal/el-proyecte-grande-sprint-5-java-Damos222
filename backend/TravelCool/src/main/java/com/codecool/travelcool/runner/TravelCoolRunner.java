package com.codecool.travelcool.runner;

import com.codecool.travelcool.model.*;
import com.codecool.travelcool.repository.AccommodationRepository;
import com.codecool.travelcool.repository.AccountRepository;
import com.codecool.travelcool.repository.AddressRepository;
import com.codecool.travelcool.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.awt.print.Book;
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
    private List<Accommodation> accommodations;
    private List<Address> addresses;
    private List<Booking> bookings;
    private List<Account> accounts;

    @Override
    public void run(String... args) throws IOException {
        createAddresses();
        createAccommodations();
        createBookings();
        createAccount();
    }

    private void createAccount() {
        accounts = List.of(
                Account.builder().email("example@gmail.com").password("password").firstName("Anna").lastName("Miller").address(addresses.get(0)).build()
        );
        accountRepository.saveAll(accounts);
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
                Booking.builder().startDate(startDates.get(0)).endDate(endDates.get(0)).timestamp(new Timestamp(System.currentTimeMillis())).build(),
                Booking.builder().startDate(startDates.get(1)).endDate(endDates.get(1)).timestamp(new Timestamp(System.currentTimeMillis())).build()
        );

        bookingRepository.saveAll(bookings);
        accommodations.get(0).getBookings().add(bookings.get(0));
        accommodations.get(1).setBookings(Set.of(bookings.get(1)));

    }

    public void createAddresses() {
        addresses = List.of(
                Address.builder().country("Spain").zipCode(2343).city("Cádiz").street("C. Baleares").houseNumber("2").build(),
                Address.builder().country("France").zipCode(1234).city("Marseille").street("Rue Lafon").houseNumber("1").build(),
                Address.builder().country("Germany").zipCode(4332).city("Berlin").street("Emser Straße").houseNumber("55").build(),
                Address.builder().country("Portugal").zipCode(6443).city("Nazaré").street("R. da Paz").houseNumber("91").build()
        );

        addressRepository.saveAll(addresses);
    }

    public void createAccommodations() throws IOException {
        accommodations = List.of(
                // TODO: use builder
                new Accommodation(2, "Rose Apartment", "Luxury apartment", getByteArrayOfImage("images/bedroom-1.jpg"), new BigDecimal(500), AccommodationType.APARTMENT, addresses.get(0)),
                new Accommodation(4, "Cozy Room", "Near the beach", getByteArrayOfImage("images/bedroom-2.jpg"), new BigDecimal(100), AccommodationType.ROOM, addresses.get(1)),
                new Accommodation(5, "City Apartment", "Apartment in the city center", getByteArrayOfImage("images/bedroom-3.jpg"), new BigDecimal(600), AccommodationType.APARTMENT, addresses.get(2)),
                new Accommodation(1, "Sunny room", "Sunny single room for adventurous travellers", getByteArrayOfImage("images/bedroom-4.jpg"), new BigDecimal(50), AccommodationType.ROOM, addresses.get(3))
        );

        accommodationRepository.saveAll(accommodations);
    }

    public byte[] getByteArrayOfImage(String imagePath) throws IOException {
        return Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(imagePath)).readAllBytes();
    }
}
