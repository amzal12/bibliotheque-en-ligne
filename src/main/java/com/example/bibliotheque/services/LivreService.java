package com.example.bibliotheque.services;

import com.example.bibliotheque.models.Livre;
import com.example.bibliotheque.repositories.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    // Récupérer tous les livres
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    // Ajouter un livre
    public void addLivre(Livre livre) {
        livreRepository.save(livre);
    }

    // Mettre à jour un livre
    public void updateLivre(Livre livre) {
        livreRepository.update(livre);
    }

    // Supprimer un livre
    public void deleteLivre(int idDocument) {
        livreRepository.deleteById(idDocument);
    }

    // Trouver un livre par son ID
    public Livre getLivreById(int idDocument) {
        return livreRepository.findById(idDocument);
    }
}
