package com.codecool.travelcool.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TravelCoolController {
    @GetMapping("/home")
    public void index() {

    }
}
