package com.codecool.travelcool.service;

import com.codecool.travelcool.dto.BookingDto;
import com.codecool.travelcool.model.Accommodation;
import com.codecool.travelcool.model.Account;
import com.codecool.travelcool.model.Booking;
import com.codecool.travelcool.repository.AccommodationRepository;
import com.codecool.travelcool.repository.AccountRepository;
import com.codecool.travelcool.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final AccommodationRepository accommodationRepository;
    private final AccountRepository accountRepository;

    public List<BookingDto> findAll() {
        return bookingRepository.findAll().stream().map(Booking::toBookingDto).toList();
    }

    public void save(BookingDto bookingDto) {
        Booking booking = bookingDto.getBooking();
        Accommodation accommodation = accommodationRepository.findById(booking.getAccommodation().getId()).get();
        Account account = accountRepository.findById(booking.getBooker().getId()).get();
        booking.setAccommodation(accommodation);
        booking.setBooker(account);
        bookingRepository.save(booking);
    }

}
