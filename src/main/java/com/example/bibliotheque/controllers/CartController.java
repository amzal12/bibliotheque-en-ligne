package com.example.bibliotheque.controllers;

import com.example.bibliotheque.repositories.EmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {

    @Autowired
    private EmpruntRepository cartService;

    // Afficher le panier de l'utilisateur
    @GetMapping("/cart")
    public String viewCart(Model model, Authentication authentication) {
        // Récupérer l'ID de l'utilisateur connecté
        
        String username = authentication.getName();
        int idUtilisateur = getUserIdFromUsername(username); // Implémentez cette méthode

        // Récupérer les documents du panier
        model.addAttribute("cartItems", cartService.getCartItems(idUtilisateur));
        
        return "cart";
    }

    // // Méthode pour récupérer l'ID de l'utilisateur à partir de son nom d'utilisateur
    private int getUserIdFromUsername(String username) {
        // Implémentez cette méthode pour récupérer l'ID de l'utilisateur
        // Exemple : utiliser `utilisateurRepository.findByUsername(username).getId()`
        return 1; // Remplacez par la logique réelle
    }
    
    // // Ajouter un document au panier (emprunt)
    // @PostMapping("/documents/{id}/add-to-cart")
    // public String addToCart(@PathVariable int id, Authentication authentication) {
    //     // Récupérer l'ID de l'utilisateur connecté
    //     String username = authentication.getName();
    //     int idUtilisateur = getUserIdFromUsername(username); // Implémentez cette méthode

    //     // ID du gestionnaire (exemple : 1 pour l'admin)
    //     int idGestionnaire = 1;

    //     // Ajouter le document au panier
    //     cartService.addDocumentToCart(idUtilisateur, id, idGestionnaire);

    //     return "redirect:/cart"; // Rediriger vers la page du panier
    // }


    // // Supprimer un document du panier
    // @PostMapping("/cart/remove/{id}")
    // public String removeFromCart(@PathVariable int id, Authentication authentication) {
    //     // Récupérer l'ID de l'utilisateur connecté
    //     String username = authentication.getName();
    //     int idUtilisateur = getUserIdFromUsername(username); // Implémentez cette méthode

    //     // Supprimer le document du panier
    //     cartService.removeDocumentFromCart(idUtilisateur, id);

    //     return "redirect:/cart"; // Rediriger vers la page du panier
    // }
}
