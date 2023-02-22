package com.codecool.travelcool.model;

import com.codecool.travelcool.dto.AccommodationDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int capacity;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Lob
    @Column(nullable = false)
    private byte [] image;
    @OneToOne
    private AccommodationFeatures features;
    @Column(nullable = false)
    private BigDecimal pricePerNight;
    @Column(nullable = false)
    private AccommodationType type;
    @ManyToOne
    @JsonIgnore
    private Account host;
    @ManyToOne
    private Address address;
    @OneToMany(mappedBy = "accommodation")
    private Set<Booking> bookings = new HashSet<>();

    public Accommodation(int capacity, String name, String description, byte[] image, BigDecimal pricePerNight, AccommodationType type, Address address) {
        this.capacity = capacity;
        this.description = name;
        this.name = description;
        this.image = image;
        this.pricePerNight = pricePerNight;
        this.type = type;
        this.address = address;
    }

    public AccommodationDto toAccommodationDto() {
        AccommodationDto accommodationDto = new AccommodationDto();
        accommodationDto.setId(id);
        accommodationDto.setTitle(name);
        accommodationDto.setCapacity(capacity);
        accommodationDto.setDescription(description);
        accommodationDto.setType(type);
        accommodationDto.setPricePerNight(pricePerNight);
        accommodationDto.setCountry(address.getCountry());
        accommodationDto.setZipCode(address.getZipCode());
        accommodationDto.setCity(address.getCity());
        accommodationDto.setHouseNumber(address.getHouseNumber());
        return accommodationDto;
    }
}
