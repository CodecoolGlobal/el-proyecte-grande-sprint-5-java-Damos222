package com.codecool.travelcool.service;

import com.codecool.travelcool.model.Accommodation;
import com.codecool.travelcool.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
