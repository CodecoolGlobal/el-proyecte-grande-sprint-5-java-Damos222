package com.codecool.travelcool.repository;

import com.codecool.travelcool.model.Booking;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findBookingByStartDateBetween(LocalDate startDate, LocalDate endDate);

    List<Booking> findBookingsByAccommodation_Id(Long accommodationId);

    List<Booking> findBookingsByAccommodation_IdAndEndDateBetweenOrStartDateBetween(Long accommodationId, LocalDate startDate, LocalDate endDate, LocalDate startDate1, LocalDate endDate1);

    List<Booking> findBookingsByStartDateAfterAndEndDateBefore(LocalDate startDate, LocalDate endDate);

}
