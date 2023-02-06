package com.codecool.travelcool.service;

import com.codecool.travelcool.model.Accommodation;
import com.codecool.travelcool.model.AccommodationType;
import com.codecool.travelcool.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;

    @Autowired
    public AccommodationService(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    public List<Accommodation> findByHost(Long hostId) {
        return accommodationRepository.findAccommodationsByHost_Id(hostId);
    }

    public List<Accommodation> findByCountry(String country) {
        return accommodationRepository.findAccommodationsByAddress_Country(country);
    }

    public List<Accommodation> findByMinimumCapacity(int minCapacity) {
        return accommodationRepository.findAccommodationsByCapacityGreaterThanEqual(minCapacity);
    }

    public List<Accommodation> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return accommodationRepository.findAccommodationsByPricePerNightBetween(minPrice, maxPrice);
    }

    public List<Accommodation> findByType(AccommodationType type) {
        return accommodationRepository.findAccommodationsByType(type);
    }

    public void save(Accommodation accommodation) {
        accommodationRepository.save(accommodation);
    }

    public void saveAll(List<Accommodation> accommodations) {
        accommodationRepository.saveAll(accommodations);
    }

    public List<Accommodation> findByBookingsStartDateAfterAndEndDateBefore(LocalDate startDate, LocalDate endDate) {
        return accommodationRepository.findByBookingsStartDateAfterAndEndDateBefore(startDate, endDate);
    }
}
