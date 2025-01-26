package com.example.bibliotheque.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.bibliotheque.services.*;
import com.example.bibliotheque.models.*;


@Controller
public class DocumentController {

    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentService documentService;
    @Autowired
    private LivreService livreService;
    @Autowired
    private JournalService journalService;
    @Autowired
    private MagazineService magazineService;
    @Autowired
    private OuvrageMultimediaService ouvrageMultimediaService;


    @GetMapping("/documents/{id}")
    public String getDocumentDetails(@PathVariable int id, Model model) {
        logger.debug("Début de la méthode getDocumentDetails pour l'ID : {}", id);

        // Récupérer le document par son ID
        Document document = documentService.getDocumentById(id);
        logger.debug("Document récupéré : {}", document);

        // Vérifier si le document existe
        if (document != null) {
            // Ajouter le document au modèle pour l'afficher dans la vue
            model.addAttribute("document", document);
            logger.debug("Document ajouté au modèle : {}", document);

        // Vérifier si le document est un livre
        if ("Livre".equals(document.getTypeDocument())) {
            logger.debug("Le document est un livre. Récupération des informations spécifiques au livre...");
            // Récupérer les informations spécifiques au livre
            Livre livre = livreService.getLivreById(id);
            if (livre != null) {
                model.addAttribute("livre", livre);
                logger.debug("Livre ajouté au modèle : {}", livre);
            } else {
                logger.debug("Aucun livre trouvé pour l'ID : {}", id);
            }
        }
        // Vérifier si le document est un journal
        else if ("Journal".equals(document.getTypeDocument())) {
            logger.debug("Le document est un journal. Récupération des informations spécifiques au journal...");
            // Récupérer les informations spécifiques au journal
            Journal journal = journalService.getJournalById(id);
            if (journal != null) {
                model.addAttribute("journal", journal);
                logger.debug("Journal ajouté au modèle : {}", journal);
            } else {
                logger.debug("Aucun journal trouvé pour l'ID : {}", id);
            }
        }
        // Vérifier si le document est un magazine
        else if ("Magazine".equals(document.getTypeDocument())) {
            logger.debug("Le document est un magazine. Récupération des informations spécifiques au magazine...");
            // Récupérer les informations spécifiques au magazine
            Magazine magazine = magazineService.getMagazineById(id);
            if (magazine != null) {
                model.addAttribute("magazine", magazine);
                logger.debug("Magazine ajouté au modèle : {}", magazine);
            } else {
                logger.debug("Aucun magazine trouvé pour l'ID : {}", id);
            }
        }
        // Vérifier si le document est un ouvrage multimédia
        else if ("OuvrageMultimedia".equals(document.getTypeDocument())) {
            logger.debug("Le document est un ouvrage multimédia. Récupération des informations spécifiques à l'ouvrage multimédia...");
            // Récupérer les informations spécifiques à l'ouvrage multimédia
            OuvrageMultimedia ouvrageMultimedia = ouvrageMultimediaService.getOuvrageMultimediaById(id);
            if (ouvrageMultimedia != null) {
                model.addAttribute("ouvrageMultimedia", ouvrageMultimedia);
                logger.debug("Ouvrage multimédia ajouté au modèle : {}", ouvrageMultimedia);
            } else {
                logger.debug("Aucun ouvrage multimédia trouvé pour l'ID : {}", id);
            }
        } else {
            logger.debug("Type de document inconnu : {}", document.getTypeDocument());
        }


            logger.debug("Redirection vers la vue document-details");
            return "document-details"; // Nom de la vue Thymeleaf
        } else {
            logger.debug("Document non trouvé. Redirection vers la page d'accueil.");
            // Rediriger vers une page d'erreur ou la page d'accueil si le document n'existe pas
            return "redirect:/";
        }
    }
}

