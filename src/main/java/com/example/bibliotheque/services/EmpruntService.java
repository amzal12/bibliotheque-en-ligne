package com.example.bibliotheque.services;

import com.example.bibliotheque.models.Emprunt;
import com.example.bibliotheque.models.EtatEmprunt;
import com.example.bibliotheque.repositories.EmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import com.example.bibliotheque.models.Document;
import java.util.List;

@Service
public class EmpruntService {

    @Autowired
    private EmpruntRepository cartRepository;

    public void addDocumentToCart(int idUtilisateur, int idDocument, int idGestionnaire) {
        if (cartRepository.isDocumentAlreadyBorrowed(idUtilisateur, idDocument)) {
            throw new IllegalStateException("Ce document est déjà emprunté par cet utilisateur.");
        }
        LocalDate today = LocalDate.now();
        LocalDate returnDate = today.plusWeeks(2);
        Emprunt emprunt = new Emprunt(
                idUtilisateur,
                idGestionnaire,
                idDocument,
                Date.valueOf(today),
                Date.valueOf(returnDate),
                EtatEmprunt.EMPRUNTE
        );
        cartRepository.addEmprunt(emprunt);
    }

    public void retournerDocument(int idEmprunt) {
        cartRepository.retournerDocument(idEmprunt);
    }

    public void signalerDocumentEndommage(int idEmprunt) {
        cartRepository.signalerDocumentEndommage(idEmprunt);
    }

    public void removeDocumentFromCart(int idUtilisateur, int idDocument) {
        cartRepository.removeDocumentFromCart(idUtilisateur, idDocument);
    }

    public List<Document> getCartItems(int idUtilisateur) {
        return cartRepository.getCartItems(idUtilisateur);
    }
}
