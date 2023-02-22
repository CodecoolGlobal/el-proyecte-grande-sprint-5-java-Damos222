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
    private List<Account> accounts;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws IOException {
        createAddresses();
        createAccommodations();
        createAccounts();
        createBookings();
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
                        .houseNumber("91").build(),
                Address.builder()
                        .country("Spain")
                        .zipCode(2343)
                        .city("Valencia")
                        .street("Pont del Regne")
                        .houseNumber("1").build(),
                Address.builder()
                        .country("Spain")
                        .zipCode(2343)
                        .city("Valencia")
                        .street("Pont del Regne")
                        .houseNumber("5").build(),
                Address.builder()
                        .country("Spain")
                        .zipCode(2343)
                        .city("Valencia")
                        .street("Pont del Regne")
                        .houseNumber("13").build(),
                Address.builder()
                        .country("Portugal")
                        .zipCode(1200-224)
                        .city("Lisbon")
                        .street("R. Capelo")
                        .houseNumber("16").build(),
                Address.builder()
                        .country("Portugal")
                        .zipCode(1200-224)
                        .city("Lisbon")
                        .street("R. Capelo")
                        .houseNumber("14").build(),
                Address.builder()
                        .country("Portugal")
                        .zipCode(1200-224)
                        .city("Lisbon")
                        .street("R. Capelo")
                        .houseNumber("12").build(),
                Address.builder()
                        .country("Germany")
                        .zipCode(10117)
                        .city("Berlin")
                        .street("Emser Straße")
                        .houseNumber("53").build(),
                Address.builder()
                        .country("Germany")
                        .zipCode(10117)
                        .city("Berlin")
                        .street("Emser Straße")
                        .houseNumber("43").build(),
                Address.builder()
                        .country("Germany")
                        .zipCode(10117)
                        .city("Berlin")
                        .street("Emser Straße")
                        .houseNumber("33").build(),
                Address.builder()
                        .country("Germany")
                        .zipCode(51065)
                        .city("Cologne")
                        .street("Brunner Straße")
                        .houseNumber("1").build(),
                Address.builder()
                        .country("Germany")
                        .zipCode(51065)
                        .city("Cologne")
                        .street("Brunner Straße")
                        .houseNumber("2").build(),
                Address.builder()
                        .country("Germany")
                        .zipCode(51065)
                        .city("Cologne")
                        .street("Brunner Straße")
                        .houseNumber("3").build(),
                Address.builder()
                        .country("France")
                        .zipCode(13002)
                        .city("Marseille")
                        .street("Rue Lafon")
                        .houseNumber("4").build(),
                Address.builder()
                        .country("France")
                        .zipCode(13002)
                        .city("Marseille")
                        .street("Rue Lafon")
                        .houseNumber("5").build(),
                Address.builder()
                        .country("France")
                        .zipCode(13002)
                        .city("Marseille")
                        .street("Rue Lafon")
                        .houseNumber("6").build(),
                Address.builder()
                        .country("France")
                        .zipCode(75001)
                        .city("Paris")
                        .street("Rue de Rivoli")
                        .houseNumber("10").build(),
                Address.builder()
                        .country("France")
                        .zipCode(75001)
                        .city("Paris")
                        .street("Rue de Rivoli")
                        .houseNumber("20").build(),
                Address.builder()
                        .country("France")
                        .zipCode(75001)
                        .city("Paris")
                        .street("Rue de Rivoli")
                        .houseNumber("30").build(),
                Address.builder()
                        .country("Scotland")
                        .zipCode(382440)
                        .city("Edinburgh")
                        .street("Princes Street")
                        .houseNumber("121").build(),
                Address.builder()
                        .country("Scotland")
                        .zipCode(382440)
                        .city("Edinburgh")
                        .street("Princes Street")
                        .houseNumber("122").build(),
                Address.builder()
                        .country("Scotland")
                        .zipCode(382440)
                        .city("Edinburgh")
                        .street("Princes Street")
                        .houseNumber("123").build(),
                Address.builder()
                        .country("Scotland")
                        .zipCode(62707)
                        .city("Glasgow")
                        .street("Waterloo Street")
                        .houseNumber("5").build(),
                Address.builder()
                        .country("Scotland")
                        .zipCode(62707)
                        .city("Glasgow")
                        .street("Waterloo Street")
                        .houseNumber("10").build(),
                Address.builder()
                        .country("Scotland")
                        .zipCode(62707)
                        .city("Glasgow")
                        .street("Waterloo Street")
                        .houseNumber("15").build()
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
                        .address(addresses.get(3)).build(),
                Accommodation.builder()
                        .capacity(3)
                        .name("Seaside room")
                        .description("Cozy room near with seaside view")
                        .image(getByteArrayOfImage("images/hotels/valencia-1.jpg"))
                        .pricePerNight(new BigDecimal(150))
                        .type(AccommodationType.ROOM)
                        .address(addresses.get(4)).build(),
                Accommodation.builder()
                        .capacity(5)
                        .name("Room with balcony")
                        .description("Relax at night with a glass of wine on the balcony")
                        .image(getByteArrayOfImage("images/hotels/valencia-2.jpg"))
                        .pricePerNight(new BigDecimal(180))
                        .type(AccommodationType.ROOM)
                        .address(addresses.get(5)).build(),
                Accommodation.builder()
                        .capacity(2)
                        .name("Modern room")
                        .description("Modern and green room")
                        .image(getByteArrayOfImage("images/hotels/valencia-3.jpg"))
                        .pricePerNight(new BigDecimal(120))
                        .type(AccommodationType.ROOM)
                        .address(addresses.get(6)).build(),
                Accommodation.builder()
                        .capacity(1)
                        .name("Classic room")
                        .description("Enjoy your stay like one of the royals")
                        .image(getByteArrayOfImage("images/hotels/lisbon-1.jpg"))
                        .pricePerNight(new BigDecimal(50))
                        .type(AccommodationType.ROOM)
                        .address(addresses.get(7)).build(),
                Accommodation.builder()
                        .capacity(3)
                        .name("Room with terrace")
                        .description("Have a nice breakfast with a great view")
                        .image(getByteArrayOfImage("images/hotels/lisbon-2.jpg"))
                        .pricePerNight(new BigDecimal(80))
                        .type(AccommodationType.ROOM)
                        .address(addresses.get(8)).build(),
                Accommodation.builder()
                        .capacity(4)
                        .name("Room with view")
                        .description("Sit back and be amazed by any sunset or sunrise")
                        .image(getByteArrayOfImage("images/hotels/lisbon-3.jpg"))
                        .pricePerNight(new BigDecimal(100))
                        .type(AccommodationType.ROOM)
                        .address(addresses.get(9)).build(),
                Accommodation.builder()
                        .capacity(1)
                        .name("Urban room")
                        .description("Modern and awesome room")
                        .image(getByteArrayOfImage("images/hotels/berlin-1.jpg"))
                        .pricePerNight(new BigDecimal(200))
                        .type(AccommodationType.ROOM)
                        .address(addresses.get(10)).build()
        );

        accommodationRepository.saveAll(accommodations);
    }

    public void createAccounts() {
        accounts = List.of(Account.builder()
                .email("hello@there.com")
                .password(passwordEncoder.encode("helloThere1"))
                .firstName("hello")
                .lastName("there")
                .address(addresses.get(0))
                .role(Role.USER)
                .build());
        accountRepository.saveAll(accounts);
    }

    public byte[] getByteArrayOfImage(String imagePath) throws IOException {
        return Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(imagePath)).readAllBytes();
    }
}
