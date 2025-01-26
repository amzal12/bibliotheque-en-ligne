package com.example.bibliotheque.services;

import com.example.bibliotheque.models.Auteur;
import com.example.bibliotheque.repositories.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuteurService {

    @Autowired
    private AuteurRepository auteurRepository;

    // Récupérer tous les auteurs
    public List<Auteur> getAllAuteurs() {
        return auteurRepository.findAll();
    }

    // Ajouter un auteur
    public void addAuteur(Auteur auteur) {
        auteurRepository.save(auteur);
    }

    // Mettre à jour un auteur
    public void updateAuteur(Auteur auteur) {
        auteurRepository.update(auteur);
    }

    // Supprimer un auteur
    public void deleteAuteur(int idPersonne) {
        auteurRepository.deleteById(idPersonne);
    }

    // Trouver un auteur par son ID
    public Auteur getAuteurById(int idPersonne) {
        return auteurRepository.findById(idPersonne);
    }
}