package com.codecool.travelcool.service;

import com.codecool.travelcool.model.Address;
import com.codecool.travelcool.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void save(Address address) {
        addressRepository.save(address);
    }

    public Optional<Address> findById(long id) {
        return addressRepository.findById(id);
    }

    public Optional<Address> findAddress(Address address) {
        return addressRepository.findByCountryAndCityAndZipCodeAndStreetAndHouseNumber(
                address.getCountry(),
                address.getCity(),
                address.getZipCode(),
                address.getStreet(),
                address.getHouseNumber());
    }
}
