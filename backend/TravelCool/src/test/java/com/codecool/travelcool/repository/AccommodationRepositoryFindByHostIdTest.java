package com.codecool.travelcool.repository;

import com.codecool.travelcool.model.Accommodation;
import com.codecool.travelcool.model.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.*;

@DataJpaTest
class AccommodationRepositoryFindByHostIdTest {
    @Autowired
    AccommodationRepository accommodationRepository;
    @Autowired
    AccountRepository accountRepository;

    List<Account> accounts = new ArrayList<>();
    List<Accommodation> accommodations = new ArrayList<>();

    public static Stream<Arguments> findByHostParameters() {
        return Stream.of(
                of(0, 0),
                of(1, 1),
                of(2, 2)
        );
    }

    @BeforeEach
    void before() {
        Stream.of("host-1", "host2", "host-3")
                .map(Account::new)
                .map(account -> accountRepository.save(account))
                .forEach(account -> accounts.add(account));

        Accommodation accommodation1 = new Accommodation("accommodation-1", accounts.get(1));
        accommodationRepository.save(accommodation1);
        accommodations.add(accommodation1);

        Stream.of("accomodation-2", "accommodation-3")
                .map(name -> new Accommodation(name, accounts.get(2)))
                .map(accommodation -> accommodationRepository.save(accommodation))
                .forEach(accommodation -> accommodations.add(accommodation));
    }

    @AfterEach
    void after() {
        accommodationRepository.deleteAll(accommodations);
        accountRepository.deleteAll(accounts);
    }

    @ParameterizedTest
    @MethodSource("findByHostParameters")
    void findAccommodationsByHost_Id(int expectedNumberOfAccommodations, int accountsIndex) {
        Account host = accounts.get(accountsIndex);

        List<Accommodation> accommodations = accommodationRepository.findAccommodationsByHost_Id(host.getId());

        assertEquals(expectedNumberOfAccommodations, accommodations.size());
    }

}