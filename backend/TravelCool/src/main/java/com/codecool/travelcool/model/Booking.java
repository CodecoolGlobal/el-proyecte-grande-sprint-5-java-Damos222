package com.codecool.travelcool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @ManyToOne
    @JoinColumn(name = "accommodation_id")
    @JsonIgnore
    private Accommodation accommodation;
}