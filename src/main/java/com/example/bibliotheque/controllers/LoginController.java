package com.example.bibliotheque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    // Gérer la requête GET pour afficher la page de connexion
    @GetMapping("/req/login")
    public String login() {
        System.out.println("Accès à la page de connexion"); 
        return "login";  // Retourner la vue login (login.html)
    }
    
}


