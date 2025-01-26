package com.example.bibliotheque.services;

import com.example.bibliotheque.models.Document;
import com.example.bibliotheque.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    // Récupérer tous les documents
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    // Ajouter un document
    public void addDocument(Document document) {
        documentRepository.save(document);
    }

    // Mettre à jour un document
    public void updateDocument(Document document) {
        documentRepository.update(document);
    }

    // Supprimer un document
    public void deleteDocument(int idDocument) {
        documentRepository.deleteById(idDocument);
    }

    // Trouver un document par son ID
    public Document getDocumentById(int idDocument) {
        return documentRepository.findById(idDocument);
    }
}
