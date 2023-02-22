package com.codecool.travelcool.service;

import com.codecool.travelcool.model.Account;
import com.codecool.travelcool.model.Booking;
import com.codecool.travelcool.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public List<Booking> findByGuest(Account account) {
        bookingRepository.findAllByBooker(account);
    }
}
