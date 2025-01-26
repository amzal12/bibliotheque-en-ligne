package com.example.bibliotheque.controllers;

import com.example.bibliotheque.models.Document;
import com.example.bibliotheque.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/")
    public String home(Model model) {
        // Récupérer tous les documents
        List<Document> documents = documentService.getAllDocuments();

        // Ajouter les documents au modèle pour les afficher dans la vue
        model.addAttribute("documents", documents);

        // Retourner le nom de la vue (home.html)
        return "home";
    }
}
