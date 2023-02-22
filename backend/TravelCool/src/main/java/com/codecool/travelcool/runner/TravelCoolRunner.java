package com.codecool.travelcool.runner;

import com.codecool.travelcool.model.*;
import com.codecool.travelcool.repository.*;
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
    private final AccommodationFeaturesRepository accommodationFeaturesRepository;
    private final AccountService accountService;
    private List<Accommodation> accommodations;
    private List<Address> addresses;
    private List<Booking> bookings;
    private List<AccommodationFeatures> features;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws IOException {
        createAddresses();
        createFeatures();
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
                new Address("Spain", 11009, "Cádiz", "C. Baleares", "2"),
                new Address("France", 13002, "Marseille", "Rue Lafon", "1"),
                new Address("Germany", 10117, "Berlin", "Emser Straße", "55"),
                new Address("Portugal", 6443, "Nazaré", "R. da Paz", "91"),
                new Address("Spain", 2343, "Valencia", "Pont del Regne", "1"),
                new Address("Spain", 2343, "Valencia", "Pont del Regne", "5"),
                new Address("Spain", 2343, "Valencia", "Pont del Regne", "13"),
                new Address("Portugal", 1200-224, "Lisbon","R. Capelo", "16"),
                new Address("Portugal", 1200-224, "Lisbon","R. Capelo", "14"),
                new Address("Portugal", 1200-224, "Lisbon","R. Capelo", "12"),
                new Address("Germany", 10117, "Berlin", "Emser Straße", "53"),
                new Address("Germany", 10117, "Berlin", "Emser Straße", "43"),
                new Address("Germany", 10117, "Berlin", "Emser Straße", "33"),
                new Address("Germany", 51065, "Cologne", "Brunner Straße", "33"),
                new Address("Germany", 51065, "Cologne", "Brunner Straße", "33"),
                new Address("Germany", 51065, "Cologne", "Brunner Straße", "33"),
                new Address("France", 13002, "Marseille", "Rue Lafon", "3"),
                new Address("France", 13002, "Marseille", "Rue Lafon", "5"),
                new Address("France", 13002, "Marseille", "Rue Lafon", "7"),
                new Address("France", 75001, "Paris", "Rue de Rivoli", "7"),
                new Address("France", 75001, "Paris", "Rue de Rivoli", "7"),
                new Address("France", 75001, "Paris", "Rue de Rivoli", "7"),
                new Address("Scotland", 382440,"Edinburgh", "Princes Street", "121"),
                new Address("Scotland", 382440,"Edinburgh", "Princes Street", "123"),
                new Address("Scotland", 382440,"Edinburgh", "Princes Street", "128"),
                new Address("Scotland", 62707,"Glasgow", "Waterloo Street", "1120"),
                new Address("Scotland", 62707,"Glasgow", "Waterloo Street", "145"),
                new Address("Scotland", 62707,"Glasgow", "Waterloo Street", "123")
                );
        addressRepository.saveAll(addresses);
    }

    public void createFeatures() {
        features = List.of(
                new AccommodationFeatures(1L,true, true, false, false, false, true, false)

        );
        accommodationFeaturesRepository.saveAll(features);
    }

    public void createAccommodations() throws IOException {
        accommodations = List.of(
                new Accommodation(2, "Rose Apartment", "Luxury apartment", getByteArrayOfImage("images/bedroom-1.jpg"), features.get(0), new BigDecimal(500), AccommodationType.APARTMENT, addresses.get(0)),
                new Accommodation(4, "Cozy Room", "Near the beach", getByteArrayOfImage("images/bedroom-2.jpg"), new BigDecimal(100), AccommodationType.ROOM, addresses.get(1)),
                new Accommodation(5, "City Apartment", "Apartment in the city center", getByteArrayOfImage("images/bedroom-3.jpg"), new BigDecimal(600), AccommodationType.APARTMENT, addresses.get(2)),
                new Accommodation(1, "Sunny room", "Sunny single room for adventurous travellers", getByteArrayOfImage("images/bedroom-4.jpg"), new BigDecimal(50), AccommodationType.ROOM, addresses.get(3)),
                new Accommodation(3, "Seaside room", "Cozy room near with seaside view", getByteArrayOfImage("images/hotels/valencia-1.jpg"), new BigDecimal(150), AccommodationType.ROOM, addresses.get(4)),
                new Accommodation(5, "Room with balcony", "Relax at night with a glass of wine on the balcony.", getByteArrayOfImage("images/hotels/valencia-2.jpg"), new BigDecimal(180), AccommodationType.ROOM, addresses.get(3)),
                new Accommodation(2, "Modern room", "Modern and green room", getByteArrayOfImage("images/hotels/valencia-3.jpg"), new BigDecimal(120), AccommodationType.ROOM, addresses.get(6)),
                new Accommodation(1, "Classic room", "Enjoy your stay like one of the royals.", getByteArrayOfImage("images/hotels/lisbon-1.jpg"), new BigDecimal(50), AccommodationType.ROOM, addresses.get(7)),
                new Accommodation(3, "Room with terrace", "Have a nice breakfast with a great view.", getByteArrayOfImage("images/hotels/lisbon-2.jpg"), new BigDecimal(80), AccommodationType.ROOM, addresses.get(8)),
                new Accommodation(4, "Room with view", "Sit back and be amazed by any sunset or sunrise.", getByteArrayOfImage("images/hotels/lisbon-3.jpg"), new BigDecimal(100), AccommodationType.ROOM, addresses.get(9)),
                new Accommodation(1, "Urban room", "Modern and awesome room.", getByteArrayOfImage("images/hotels/berlin-1.jpg"), new BigDecimal(200), AccommodationType.ROOM, addresses.get(10)),
                new Accommodation(2, "Central room", "Enjoy your stay in the center of Berlin.", getByteArrayOfImage("images/hotels/berlin-2.jpg"), new BigDecimal(250), AccommodationType.ROOM, addresses.get(11)),
                new Accommodation(3, "Modern room", "Room with a great view.", getByteArrayOfImage("images/hotels/berlin-3.jpg"), new BigDecimal(110), AccommodationType.ROOM, addresses.get(12)),
                new Accommodation(4, "Cozy room", "Great view of the cathedral.", getByteArrayOfImage("images/hotels/cologne-1.jpg"), new BigDecimal(200), AccommodationType.ROOM, addresses.get(10)),
                new Accommodation(5, "Central room", "Enjoy your stay in the nature of Cologne.", getByteArrayOfImage("images/hotels/cologne-2.jpg"), new BigDecimal(250), AccommodationType.ROOM, addresses.get(11)),
                new Accommodation(1, "Modern room", "Modern and compact room.", getByteArrayOfImage("images/hotels/cologne-3.jpg"), new BigDecimal(110), AccommodationType.ROOM, addresses.get(12)),
                new Accommodation(2, "Central room", "Enjoy your stay in the center of Marseille.", getByteArrayOfImage("images/hotels/marseille-1.jpg"), new BigDecimal(200), AccommodationType.ROOM, addresses.get(13)),
                new Accommodation(3, "Elegant room", "Perfect for a relaxing stay.", getByteArrayOfImage("images/hotels/marseille-2.jpg"), new BigDecimal(250), AccommodationType.ROOM, addresses.get(14)),
                new Accommodation(4, "Luxurious apartment", "Enjoy your stay like one of the royals.", getByteArrayOfImage("images/hotels/marseille-3.jpg"), new BigDecimal(110), AccommodationType.APARTMENT, addresses.get(15)),
                new Accommodation(2, "Cozy room", "Enjoy your stay in the center of Paris.", getByteArrayOfImage("images/hotels/paris-1.jpg"), new BigDecimal(50), AccommodationType.ROOM, addresses.get(16)),
                new Accommodation(2, "Room with view", "Perfect for a relaxing stay.", getByteArrayOfImage("images/hotels/paris-2.jpg"), new BigDecimal(100), AccommodationType.ROOM, addresses.get(17)),
                new Accommodation(2, "Luxurious apartment", "Modern and relaxing room.", getByteArrayOfImage("images/hotels/paris-3.jpg"), new BigDecimal(150), AccommodationType.APARTMENT, addresses.get(18)),
                new Accommodation(3, "Cozy room", "Enjoy your stay in the center of Edinburgh.", getByteArrayOfImage("images/hotels/edinburgh-1.jpg"), new BigDecimal(100), AccommodationType.ROOM, addresses.get(19)),
                new Accommodation(1, "Room with view", "Perfect for a relaxing stay.", getByteArrayOfImage("images/hotels/edinburgh-2.jpg"), new BigDecimal(120), AccommodationType.ROOM, addresses.get(20)),
                new Accommodation(4, "Luxurious apartment", "Modern and relaxing room.", getByteArrayOfImage("images/hotels/edinburgh-3.jpg"), new BigDecimal(200), AccommodationType.APARTMENT, addresses.get(21)),
                new Accommodation(3, "Cozy room", "Enjoy your stay in the center of Glasgow.", getByteArrayOfImage("images/hotels/glasgow-1.jpg"), new BigDecimal(230), AccommodationType.ROOM, addresses.get(22)),
                new Accommodation(1, "Room with view", "Perfect for a relaxing stay.", getByteArrayOfImage("images/hotels/glasgow-2.jpg"), new BigDecimal(80), AccommodationType.ROOM, addresses.get(23)),
                new Accommodation(4, "Luxurious apartment", "Modern and relaxing room.", getByteArrayOfImage("images/hotels/glasgow-3.jpg"), new BigDecimal(180), AccommodationType.APARTMENT, addresses.get(24))
                );
        accommodationRepository.saveAll(accommodations);
    }

    public void createAccounts() {
        accountService.save(Account.builder()
                .email("hello@there.com")
                .password(passwordEncoder.encode("helloThere1"))
                .firstName("hello")
                .lastName("there")
                .role(Role.USER)
                .build());
    }

    public byte[] getByteArrayOfImage(String imagePath) throws IOException {
        return Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(imagePath)).readAllBytes();
    }
}
