package com.codecool.travelcool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int capacity;
    private String name;
    private String description;
    @Lob
    private byte [] image;
    @OneToOne
    private AccommodationFeatures features;
    private BigDecimal pricePerNight;
    private AccommodationType type;
    @ManyToOne
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
}
