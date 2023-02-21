package com.codecool.travelcool.runner;

import com.codecool.travelcool.model.*;
import com.codecool.travelcool.repository.AccommodationRepository;
import com.codecool.travelcool.repository.AccountRepository;
import com.codecool.travelcool.repository.AddressRepository;
import com.codecool.travelcool.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
        createAccount();
        createBookings();
    }

    private void createAccount() {
        accounts = List.of(
                Account.builder()
                        .email("example@gmail.com")
                        .password("password")
                        .firstName("Anna")
                        .lastName("Miller")
                        .address(addresses.get(0)).build()
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
                Booking.builder()
                        .startDate(startDates.get(0))
                        .endDate(endDates.get(0))
                        .timestamp(new Timestamp(System.currentTimeMillis()))
                        .accommodation(accommodations.get(0))
                        .booker(accounts.get(0))
                        .build(),
                Booking.builder()
                        .startDate(startDates.get(1))
                        .endDate(endDates.get(1))
                        .timestamp(new Timestamp(System.currentTimeMillis()))
                        .accommodation(accommodations.get(1))
                        .booker(accounts.get(0))
                        .build()
        );

        bookingRepository.saveAll(bookings);
        accommodations.get(0).setBookings(Set.of(bookings.get(0)));
        accommodations.get(1).setBookings(Set.of(bookings.get(1)));

    }

    public void createAddresses() {
        addresses = List.of(
                Address.builder()
                        .country("Spain")
                        .zipCode(2343)
                        .city("Cádiz")
                        .street("C. Baleares")
                        .houseNumber("2").build(),
                Address.builder()
                        .country("France")
                        .zipCode(1234)
                        .city("Marseille")
                        .street("Rue Lafon")
                        .houseNumber("1").build(),
                Address.builder()
                        .country("Germany")
                        .zipCode(4332)
                        .city("Berlin")
                        .street("Emser Straße")
                        .houseNumber("55").build(),
                Address.builder()
                        .country("Portugal")
                        .zipCode(6443)
                        .city("Nazaré")
                        .street("R. da Paz")
                        .houseNumber("91").build()
        );

        addressRepository.saveAll(addresses);
    }

    public void createAccommodations() throws IOException {
        accommodations = List.of(
                Accommodation.builder()
                        .capacity(2)
                        .name("Rose Apartment")
                        .description("Luxury apartment")
                        .image(getByteArrayOfImage("images/bedroom-1.jpg"))
                        .pricePerNight(new BigDecimal(500))
                        .type(AccommodationType.APARTMENT)
                        .address(addresses.get(0)).build(),
                Accommodation.builder()
                        .capacity(4)
                        .name("Cozy Room")
                        .description("Near the beach")
                        .image(getByteArrayOfImage("images/bedroom-2.jpg"))
                        .pricePerNight(new BigDecimal(100))
                        .type(AccommodationType.ROOM)
                        .address(addresses.get(1)).build(),
                Accommodation.builder()
                        .capacity(5)
                        .name("City Apartment")
                        .description("Apartment in the city center")
                        .image(getByteArrayOfImage("images/bedroom-3.jpg"))
                        .pricePerNight(new BigDecimal(600))
                        .type(AccommodationType.APARTMENT)
                        .address(addresses.get(2)).build(),
                Accommodation.builder()
                        .capacity(1)
                        .name("Sunny room")
                        .description("Sunny single room for adventurous travellers")
                        .image(getByteArrayOfImage("images/bedroom-4.jpg"))
                        .pricePerNight(new BigDecimal(50))
                        .type(AccommodationType.ROOM)
                        .address(addresses.get(3)).build()
        );

        accommodationRepository.saveAll(accommodations);
    }

    public byte[] getByteArrayOfImage(String imagePath) throws IOException {
        return Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(imagePath)).readAllBytes();
    }
}
