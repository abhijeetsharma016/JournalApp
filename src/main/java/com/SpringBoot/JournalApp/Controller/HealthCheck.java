package com.SpringBoot.JournalApp.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping({"/health-Check", "/public/health-Check"})
    public String healthCheck(){
        return "OK";
    }
}