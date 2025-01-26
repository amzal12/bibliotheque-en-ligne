package com.example.bibliotheque.controllers;

import com.example.bibliotheque.models.Utilisateur;
import com.example.bibliotheque.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/req/signup")
    public String inscription() {
        return "registration"; // Affiche la page d'inscription (registration.html)
    }

    @PostMapping("/req/signup")
    public String createUser(@RequestParam String fullname, @RequestParam String username, 
                             @RequestParam String email, @RequestParam String telephone, 
                             @RequestParam String password, @RequestParam String confirm_password,
                             RedirectAttributes redirectAttributes) {
        
        // Vérification si le mot de passe et la confirmation correspondent
        if (!password.equals(confirm_password)) {
            redirectAttributes.addFlashAttribute("error", "Les mots de passe ne correspondent pas.");
            return "redirect:/req/signup";
        }
        
        // Hash du mot de passe
        String hashedPassword = passwordEncoder.encode(password);

        // Créer un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(fullname);
        utilisateur.setNomUtilisateur(username);
        utilisateur.setMail(email);
        utilisateur.setTelephone(telephone);
        utilisateur.setMotDePasse(hashedPassword);
        utilisateur.setRole("Utilisateur");

        // Enregistrer l'utilisateur
        utilisateurService.addUtilisateur(utilisateur);
        
        // Redirection vers la page de connexion après l'enregistrement
        return "redirect:/req/login"; 
    }
}
