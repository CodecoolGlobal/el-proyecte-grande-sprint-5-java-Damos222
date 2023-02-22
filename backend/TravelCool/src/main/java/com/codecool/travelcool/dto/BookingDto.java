package com.codecool.travelcool.dto;

import com.codecool.travelcool.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BookingDto {
    private AccommodationDto accommodationDto;

    private LocalDate startDate;
    private LocalDate endDate;

    private AccountDto booker;
}
