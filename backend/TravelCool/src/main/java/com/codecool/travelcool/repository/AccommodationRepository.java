package com.codecool.travelcool.repository;

import com.codecool.travelcool.model.Accommodation;
import com.codecool.travelcool.model.AccommodationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    List<Accommodation> findAccommodationsByHost_Id(long hostId);

    List<Accommodation> findAccommodationsByAddress_Country(String country);

    List<Accommodation> findAccommodationsByCapacityGreaterThanEqual(int capacity);

    List<Accommodation> findAccommodationsByCapacityLessThanEqualAndCapacityGreaterThanEqual(int min, int max);

    List<Accommodation> findAccommodationsByPricePerNightBetween(BigDecimal min, BigDecimal max);

    List<Accommodation> findAccommodationsByType(AccommodationType type);

    @Query("SELECT a from Accommodation a LEFT JOIN a.bookings b " +
            "WHERE (b.startDate NOT BETWEEN ?1 AND ?2) AND (b.endDate NOT BETWEEN ?1 AND ?2) " +
            "OR b.accommodation IS NULL")
    List<Accommodation> findByBookingsStartDateAfterAndEndDateBefore(LocalDate startDate, LocalDate endDate);



}
