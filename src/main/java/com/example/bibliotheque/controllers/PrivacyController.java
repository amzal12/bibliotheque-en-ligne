package com.example.bibliotheque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivacyController {

    @GetMapping("/privacy")
    public String privacy() {
        return "privacy";  
    }
}
