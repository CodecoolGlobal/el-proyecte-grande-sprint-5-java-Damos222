package com.codecool.travelcool.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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
    private int capacity;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @Lob
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

    public Accommodation(@NonNull String name, Account host) {
        this.name = name;
        this.host = host;
    }

    public Accommodation(Long id, @NonNull String name, @NonNull String description, @NonNull byte[] image, @NonNull BigDecimal pricePerNight, @NonNull AccommodationType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.pricePerNight = pricePerNight;
        this.type = type;
    }

    public Accommodation(int i, String rose_apartment, String luxury_apartment, byte[] byteArrayOfImage, BigDecimal bigDecimal, AccommodationType room) {
    }

    public Accommodation(int capacity, @NonNull String name, @NonNull String description, byte[] image, @NonNull BigDecimal pricePerNight, @NonNull AccommodationType type, @NonNull Address address) {
        this.capacity = capacity;
        this.name = name;
        this.description = description;
        this.image = image;
        this.pricePerNight = pricePerNight;
        this.type = type;
        this.address = address;
    }
}
