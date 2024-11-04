package com.promocode_functionality.promocode_functionality.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("api/health-check")
    public static String healthCheck(){
        return "Everything is running fine, :)";
    }
}
