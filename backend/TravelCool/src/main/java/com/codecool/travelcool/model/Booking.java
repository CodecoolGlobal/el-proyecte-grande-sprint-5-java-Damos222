package com.codecool.travelcool.model;

import com.codecool.travelcool.dto.AccommodationDto;
import com.codecool.travelcool.dto.BookingDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    @JsonIgnore
    private Long id;
    @NonNull
    private LocalDate startDate;
    @NonNull
    private LocalDate endDate;
    @NonNull
    @Temporal(value = TemporalType.TIMESTAMP)
    @GeneratedValue
    private Timestamp timestamp;
    @ManyToOne
    private Account booker;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "accommodation_id")
    @JsonIgnore
    private Accommodation accommodation;

    public BookingDto getBookingDto() {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setStartDate(startDate);
        bookingDto.setEndDate(endDate);
        bookingDto.setBooker(booker.getAccountDto());
        AccommodationDto accommodationDto = accommodation.getAccommodationDto();
        bookingDto.setAccommodationDto(accommodationDto);
        return bookingDto;
    }
}