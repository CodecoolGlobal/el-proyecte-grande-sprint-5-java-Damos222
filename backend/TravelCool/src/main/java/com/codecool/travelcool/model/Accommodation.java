package com.codecool.travelcool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Accommodation {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private Integer capacity;
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
    @ManyToOne
    private Address address;
}
