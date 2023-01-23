package com.codecool.travelcool.controller;

import com.codecool.travelcool.MessageDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class TravelCoolController {
    @GetMapping("/home")
    public void index() {

    }
}
