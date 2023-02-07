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
@Builder
@Getter
@Setter
@Entity
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
}
