package com.codecool.travelcool.model;

import com.codecool.travelcool.dto.AccommodationDto;
import com.codecool.travelcool.dto.BookingDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Timestamp timestamp;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Account booker;
    @ManyToOne
    @JoinColumn(name = "accommodation_id", nullable = false)
    @JsonIgnore
    private Accommodation accommodation;

    public BookingDto toBookingDto() {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setStartDate(startDate);
        bookingDto.setEndDate(endDate);
        bookingDto.setBooker(booker.toAccountDto());
        AccommodationDto accommodationDto = accommodation.toAccommodationDto();
        bookingDto.setAccommodationDto(accommodationDto);
        return bookingDto;
    }
}
