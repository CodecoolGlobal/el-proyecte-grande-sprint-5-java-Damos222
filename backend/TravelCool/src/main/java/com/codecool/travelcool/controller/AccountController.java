package com.codecool.travelcool.controller;

import com.codecool.travelcool.model.Accommodation;
import com.codecool.travelcool.model.Account;
import com.codecool.travelcool.model.Booking;
import com.codecool.travelcool.service.AccommodationService;
import com.codecool.travelcool.service.AccountService;
import com.codecool.travelcool.service.BookingService;
import com.codecool.travelcool.dto.AccountDto;
import com.codecool.travelcool.model.Account;
import com.codecool.travelcool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccommodationService accommodationService;
    private final AccountService accountService;
    private final BookingService bookingService;

    public AccountController(AccommodationService accommodationService, AccountService accountService, BookingService bookingService) {
        this.accommodationService = accommodationService;
        this.accountService = accountService;
        this.bookingService = bookingService;
    }


    @GetMapping("/all")
    public List<AccountDto> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/email")
    public String getEmail(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/accommodations")
    public List<Accommodation> getAccommodations(Principal principal) {
        Optional<Account> account = accountService.findByEmail(principal.getName());
        if (account.isPresent()) {
            return accommodationService.getByHost(account.get());
        } else {
            return Collections.emptyList();
        }
    }

    @GetMapping("/bookings")
    public List<Booking> getBookings(Principal principal) {
        Optional<Account> account = accountService.findByEmail(principal.getName());
        if (account.isPresent()) {
            return bookingService.findByGuest(account.get());
        }
        return Collections.emptyList();
    }
}
