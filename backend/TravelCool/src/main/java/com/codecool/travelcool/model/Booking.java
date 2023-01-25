package com.codecool.travelcool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @NonNull
    private LocalDate startDate;
    @NonNull
    private LocalDate endDate;
    @Temporal(value = TemporalType.TIMESTAMP)
    @GeneratedValue
    private Timestamp timestamp;
    @ManyToOne
    private Account booker;
    private int adults;
    private int children;
    @NonNull
    @ManyToOne
    private Accommodation accommodation;
}
