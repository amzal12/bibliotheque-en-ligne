package com.example.bibliotheque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.bibliotheque.services.*;
import com.example.bibliotheque.repositories.*;
import com.example.bibliotheque.models.*;


@SpringBootApplication
@ComponentScan(basePackages = "com.example.bibliotheque")
@Configuration
public class BibliothequeApplication {
    public static void main(String[] args) {
        // Démarrer l'application et obtenir le contexte Spring
        ApplicationContext context = SpringApplication.run(BibliothequeApplication.class, args);


    }
}
