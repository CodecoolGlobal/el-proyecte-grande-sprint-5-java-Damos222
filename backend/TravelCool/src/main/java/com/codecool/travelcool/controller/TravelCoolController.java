package com.codecool.travelcool.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TravelCoolController {
    @GetMapping("/hello")
    public String index() {
        return new JSONObject()
                .put("JSON1", "Hello World!")
                .toString();
    }
}
