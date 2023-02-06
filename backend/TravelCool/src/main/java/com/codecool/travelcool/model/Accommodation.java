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
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private int capacity;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @Lob
    @NonNull
    private byte [] image;
    @OneToOne
    private AccommodationFeatures features;
    @NonNull
    private BigDecimal pricePerNight;
    @NonNull
    private AccommodationType type;
    @ManyToOne
    private Account host;
    @NonNull
    @ManyToOne
    private Address address;
    @NonNull
    @OneToMany(mappedBy = "accommodation")
    private Set<Booking> bookings = new HashSet<>();
}
