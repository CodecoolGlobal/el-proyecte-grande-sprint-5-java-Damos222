package com.codecool.travelcool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    @Temporal(value = TemporalType.TIMESTAMP)
    @GeneratedValue
    private Timestamp timestamp;
    @ManyToOne
    private Account booker;
    private int adults;
    private int children;
    @ManyToOne
    private Accommodation accommodation;
}
