package com.example.bibliotheque.services;

import com.example.bibliotheque.models.Magazine;
import com.example.bibliotheque.repositories.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagazineService {

    @Autowired
    private MagazineRepository magazineRepository;

    // Récupérer tous les magazines
    public List<Magazine> getAllMagazines() {
        return magazineRepository.findAll();
    }

    // Ajouter un magazine
    public void addMagazine(Magazine magazine) {
        magazineRepository.save(magazine);
    }

    // Mettre à jour un magazine
    public void updateMagazine(Magazine magazine) {
        magazineRepository.update(magazine);
    }

    // Supprimer un magazine
    public void deleteMagazine(int idDocument) {
        magazineRepository.deleteById(idDocument);
    }

    // Trouver un magazine par son ID
    public Magazine getMagazineById(int idDocument) {
        return magazineRepository.findById(idDocument);
    }
}