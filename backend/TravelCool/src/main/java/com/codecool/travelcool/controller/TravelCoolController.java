package main.java.com.codecool.travelcool.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TravelCoolController {
    @GetMapping("/")
    public String index() {
        return "Start Page";
    }
}
