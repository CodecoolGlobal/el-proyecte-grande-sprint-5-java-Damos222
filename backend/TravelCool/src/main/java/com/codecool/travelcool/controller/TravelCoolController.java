package com.codecool.travelcool.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin // TODO: remove this in all Controllers when security is added
@RestController
public class TravelCoolController {
    @GetMapping("/home")
    public void index() {

    }
}
