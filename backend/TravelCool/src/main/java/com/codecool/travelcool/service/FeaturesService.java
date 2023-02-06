package com.codecool.travelcool.service;

import com.codecool.travelcool.model.AccommodationFeatures;
import com.codecool.travelcool.repository.AccommodationFeaturesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeaturesService {
    private final AccommodationFeaturesRepository repository;

    @Autowired
    public FeaturesService(AccommodationFeaturesRepository repository) {
        this.repository = repository;
    }

    public void save(AccommodationFeatures features) {
        repository.save(features);
    }
}
