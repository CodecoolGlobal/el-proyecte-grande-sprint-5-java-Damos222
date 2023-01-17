package com.codecool.travelcool.runner;

import com.codecool.travelcool.model.Accommodation;
import com.codecool.travelcool.model.AccommodationType;
import com.codecool.travelcool.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class AccommodationRunner implements CommandLineRunner {
    private final AccommodationRepository accommodationRepository;

    @Override
    public void run(String... args) throws IOException {
        accommodationRepository.saveAll(List.of(
                new Accommodation(2, "Rose Apartment", "Luxury apartment", getByteArrayOfImage("images/bedroom-1.jpg"), new BigDecimal(500), AccommodationType.ROOM),
                new Accommodation(4, "Cozy Room", "Near the beach", getByteArrayOfImage("images/bedroom-2.jpg"), new BigDecimal(100), AccommodationType.APARTMENT)
        ));
    }

    public byte[] getByteArrayOfImage(String imagePath) throws IOException {
        return Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(imagePath)).readAllBytes();
    }
}
