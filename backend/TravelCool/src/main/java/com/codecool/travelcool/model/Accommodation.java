package com.codecool.travelcool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Accommodation {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int capacity;
    @OneToOne
    private AccommodationFeatures features;
    private BigDecimal pricePerNight;
    private AccommodationType type;
    @ManyToOne
    private Account host;
    @ManyToOne
    private Address address;
}
