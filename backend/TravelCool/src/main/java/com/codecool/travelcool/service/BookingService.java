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

    public void book(BookingDto bookingDto) {
        Accommodation accommodation = accommodationRepository.findById(bookingDto.getAccommodationDto().getId()).get();
        Account account = accountRepository.findById(bookingDto.getBooker().getId()).get();
        Booking booking = Booking.builder()
                .accommodation(accommodation)
                .booker(account)
                .startDate(bookingDto.getStartDate())
                .endDate(bookingDto.getEndDate())
                .build();
        bookingRepository.save(booking);
    }

}
