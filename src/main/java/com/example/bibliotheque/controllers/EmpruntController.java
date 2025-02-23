package com.example.bibliotheque.controllers;

import com.example.bibliotheque.services.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class EmpruntController { // Renommé CartController -> EmpruntController

    @Autowired
    private EmpruntService empruntService; 

    // Méthode pour ajouter un document au panier (emprunt)
    @PostMapping("/emprunter")
    public String emprunterDocument(@RequestParam("idDocument") int idDocument, Authentication authentication) {
    //public String emprunterDocument() {
        try {
            // Log the document and user info
            System.out.println("Tentative d'emprunt du document avec ID: " + idDocument);
            
            //Récupérer l'utilisateur connecté
            String username = authentication.getName();
            int idUtilisateur = empruntService.getUserIdFromUsername(username);
            
            // Log the user information
            System.out.println("Utilisateur avec ID: " + idUtilisateur + " tente d'emprunter.");
    
            
            // Appel du service pour ajouter le document à l'emprunt
            empruntService.addDocumentToEmprunt(idUtilisateur, idDocument);
            
            return "redirect:/emprunts";
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
            return "redirect:/error";
        }
    }
    

    // Afficher les emprunts (ancien "panier")
    @GetMapping("/emprunts")  // Changé de "/cart" à "/emprunts"
    public String viewEmprunts(Model model, Authentication authentication) {
        // Récupérer le nom d'utilisateur de l'utilisateur connecté
        String username = authentication.getName();
        int idUtilisateur = empruntService.getUserIdFromUsername(username); // Utilise le service pour obtenir l'ID utilisateur

        // Récupérer les documents empruntés (panier)
        model.addAttribute("empruntItems", empruntService.getCartItems(idUtilisateur));

        return "emprunt"; // Assurez-vous que la vue est bien "emprunt.html"
    }
}


