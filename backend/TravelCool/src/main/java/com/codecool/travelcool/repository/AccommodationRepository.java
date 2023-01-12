package com.codecool.travelcool.repository;

import com.codecool.travelcool.model.Accommodation;
import com.codecool.travelcool.model.AccommodationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    List<Accommodation> findAccommodationsByHost_Id(Long hostId);

    List<Accommodation> findAccommodationsByAddress_Country(String country);

    List<Accommodation> findAccommodationsByCapacityGreaterThanEqual(int capacity);

    List<Accommodation> findAccommodationsByCapacityLessThanEqualAndCapacityGreaterThanEqual(int min, int max);

    List<Accommodation> findAccommodationsByPricePerNightBetween(BigDecimal min, BigDecimal max);

    List<Accommodation> findAccommodationsByType(AccommodationType type);
//    void saveAll(List<Accommodation> accommodations);
}
