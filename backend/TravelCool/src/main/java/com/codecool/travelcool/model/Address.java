package com.codecool.travelcool.model;

import com.codecool.travelcool.dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Address {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private int zipCode;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String houseNumber;

    public AddressDto getAddressDto() {
        AddressDto addressDto = new AddressDto();
        addressDto.setCountry(country);
        addressDto.setZipCode(zipCode);
        addressDto.setCity(city);
        addressDto.setStreet(street);
        addressDto.setHouseNumber(houseNumber);
        return addressDto;
    }
}
