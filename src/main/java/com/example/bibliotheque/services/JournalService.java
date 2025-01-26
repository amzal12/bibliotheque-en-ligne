package com.example.bibliotheque.services;

import com.example.bibliotheque.models.Journal;
import com.example.bibliotheque.repositories.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    // Récupérer tous les journaux
    public List<Journal> getAllJournals() {
        return journalRepository.findAll();
    }

    // Ajouter un journal
    public void addJournal(Journal journal) {
        journalRepository.save(journal);
    }

    // Mettre à jour un journal
    public void updateJournal(Journal journal) {
        journalRepository.update(journal);
    }

    // Supprimer un journal
    public void deleteJournal(int idDocument) {
        journalRepository.deleteById(idDocument);
    }

    // Trouver un journal par son ID
    public Journal getJournalById(int idDocument) {
        return journalRepository.findById(idDocument);
    }
}