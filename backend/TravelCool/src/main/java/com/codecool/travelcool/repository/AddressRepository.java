package com.codecool.travelcool.repository;

import com.codecool.travelcool.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByCountryAndCityAndZipCodeAndStreetAndHouseNumber(String country, String city, int zipCode, String street, String houseNumber);

}
