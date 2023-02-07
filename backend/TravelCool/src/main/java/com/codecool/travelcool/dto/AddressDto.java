package com.codecool.travelcool.dto;

import com.codecool.travelcool.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {
    private Long id;
    private String country;
    private int zipCode;
    private String city;
    private String street;
    private String houseNumber;

    public Address getAddress() {
        Address address = new Address();
        address.setHouseNumber(houseNumber);
        address.setCity(city);
        address.setStreet(street);
        address.setZipCode(zipCode);
        address.setCountry(country);
        return address;
    }
}
