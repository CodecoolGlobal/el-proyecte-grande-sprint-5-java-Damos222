package com.codecool.travelcool.configuration;

import com.codecool.travelcool.model.Accommodation;
import com.codecool.travelcool.model.AccommodationType;
import com.codecool.travelcool.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AccommodationConfig implements CommandLineRunner {
    private final AccommodationRepository accommodationRepository;

    @Override
    public void run(String... args) {
        accommodationRepository.saveAll(List.of(
                new Accommodation(2, "Hilton", "Luxury hotel", new BigDecimal(500), AccommodationType.ROOM),
                new Accommodation(4, "Beach Hotel", "Near the beach", new BigDecimal(100), AccommodationType.APARTMENT)
        ));
    }
}
