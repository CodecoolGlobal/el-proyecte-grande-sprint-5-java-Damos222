package com.codecool.travelcool.controller;

import com.codecool.travelcool.model.Booking;
import com.codecool.travelcool.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingService.findAll();
    }
}
