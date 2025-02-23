package com.example.bibliotheque.services;

import com.example.bibliotheque.models.Emprunt;
import com.example.bibliotheque.models.EtatEmprunt;
import com.example.bibliotheque.models.Utilisateur;
import com.example.bibliotheque.repositories.EmpruntRepository;
import com.example.bibliotheque.repositories.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmpruntService {

    @Autowired
    private EmpruntRepository empruntRepository; // Renommé de cartRepository à empruntRepository

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Ajouter un document à la liste des emprunts
    public void addDocumentToEmprunt(int idPersonne, int idDocument) {
        if (empruntRepository.isDocumentAlreadyBorrowed(idPersonne, idDocument)) {
            throw new IllegalStateException("Ce document est déjà emprunté par cet utilisateur.");
        }
        LocalDate today = LocalDate.now();
        LocalDate returnDate = today.plusWeeks(2);
        Emprunt emprunt = new Emprunt(
                idPersonne,
                idDocument,
                Date.valueOf(today),
                Date.valueOf(returnDate),
                EtatEmprunt.EMPRUNTE
        );
        
        empruntRepository.addEmprunt(emprunt);
    }

    public void retournerDocument(int idEmprunt) {
        empruntRepository.retournerDocument(idEmprunt);
    }

    public void signalerDocumentEndommage(int idEmprunt) {
        empruntRepository.signalerDocumentEndommage(idEmprunt);
    }

    public void removeDocumentFromEmprunt(int idUtilisateur, int idDocument) {
        empruntRepository.removeDocumentFromCart(idUtilisateur, idDocument); // Nom resté identique car c'est une action sur un panier
    }

    public List<Map<String, Object>> getCartItems(int idUtilisateur) {
        return empruntRepository.getCartItems(idUtilisateur); // Utilisation du repository pour obtenir la liste des emprunts
    }

    public int getUserIdFromUsername(String username) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByNomUtilisateur(username);
        return utilisateurOptional
                .map(Utilisateur::getIdPersonne)
                .orElseThrow(() -> new RuntimeException("Utilisateur avec le nom d'utilisateur " + username + " non trouvé"));
    }

    public boolean isDocumentAlreadyBorrowed(int idPersonne, int idDocument) {
        return empruntRepository.isDocumentAlreadyBorrowed(idPersonne, idDocument);
    }

    public List<Map<String, Object>> getAllUserAndDocEmprenter() {
        return empruntRepository.getAllUserAndDocEmprenter();
    }
    
}
