package com.example.bibliotheque.services;

import com.example.bibliotheque.models.OuvrageMultimedia;
import com.example.bibliotheque.repositories.OuvrageMultimediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OuvrageMultimediaService {

    @Autowired
    private OuvrageMultimediaRepository ouvrageMultimediaRepository;

    // Récupérer tous les ouvrages multimédias
    public List<OuvrageMultimedia> getAllOuvragesMultimedia() {
        return ouvrageMultimediaRepository.findAll();
    }

    // Ajouter un ouvrage multimédia
    public void addOuvrageMultimedia(OuvrageMultimedia ouvrageMultimedia) {
        ouvrageMultimediaRepository.save(ouvrageMultimedia);
    }

    // Mettre à jour un ouvrage multimédia
    public void updateOuvrageMultimedia(OuvrageMultimedia ouvrageMultimedia) {
        ouvrageMultimediaRepository.update(ouvrageMultimedia);
    }

    // Supprimer un ouvrage multimédia
    public void deleteOuvrageMultimedia(int idDocument) {
        ouvrageMultimediaRepository.deleteById(idDocument);
    }

    // Trouver un ouvrage multimédia par son ID
    public OuvrageMultimedia getOuvrageMultimediaById(int idDocument) {
        return ouvrageMultimediaRepository.findById(idDocument);
    }
}
