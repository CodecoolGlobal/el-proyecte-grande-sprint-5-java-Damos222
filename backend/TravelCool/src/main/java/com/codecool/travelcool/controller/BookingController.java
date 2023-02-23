package com.codecool.travelcool.controller;

import com.codecool.travelcool.dto.BookingDto;
import com.codecool.travelcool.model.Booking;
import com.codecool.travelcool.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/all")
    public List<BookingDto> getAllBookings() {
        return bookingService.findAll();
    }

    @PostMapping("/checkout")
    public BookingDto checkout(Principal principal, @RequestBody BookingDto bookingDto) {
        String bookerEmail = principal.getName();
        return bookingService.book(bookingDto, bookerEmail);
    }
}
