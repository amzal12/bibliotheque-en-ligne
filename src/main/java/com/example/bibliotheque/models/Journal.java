package com.example.bibliotheque.models;

import java.time.LocalDate;

public class Journal extends Document {
    private String periodicite;

    // Constructeur par défaut
    public Journal() {
    }

    // Constructeur avec paramètres
    public Journal(int idDocument, String titre, String editeur, int qteTotale, int qteDisponible, LocalDate datePublication, String periodicite, String imageUrl) {
        super(idDocument, titre, editeur, qteTotale, qteDisponible, datePublication, "Journal", imageUrl);
        this.periodicite = periodicite;
    }

    // Getters et setters
    public String getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "idDocument=" + getIdDocument() +
                ", titre='" + getTitre() + '\'' +
                ", editeur='" + getEditeur() + '\'' +
                ", qteTotale=" + getQteTotale() +
                ", qteDisponible=" + getQteDisponible() +
                ", datePublication=" + getDatePublication() +
                ", typeDocument='" + getTypeDocument() + '\'' +
                ", imageUrl='" + getImageUrl() + '\'' +
                ", periodicite='" + periodicite + '\'' +
                '}';
    }
}