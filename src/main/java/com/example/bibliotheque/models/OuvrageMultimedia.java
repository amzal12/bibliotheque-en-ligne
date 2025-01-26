package com.example.bibliotheque.models;

import java.time.LocalDate;

public class OuvrageMultimedia extends Document {
    private String typeOuvrage;
    private int duree; // Durée en minutes

    // Constructeur par défaut
    public OuvrageMultimedia() {
    }

    // Constructeur avec paramètres
    public OuvrageMultimedia(int idDocument, String titre, String editeur, int qteTotale, int qteDisponible, LocalDate datePublication, String typeOuvrage, int duree, String imageUrl) {
        super(idDocument, titre, editeur, qteTotale, qteDisponible, datePublication, "OuvrageMultimedia", imageUrl);
        this.typeOuvrage = typeOuvrage;
        this.duree = duree;
    }

    // Getters et setters
    public String getTypeOuvrage() {
        return typeOuvrage;
    }

    public void setTypeOuvrage(String typeOuvrage) {
        this.typeOuvrage = typeOuvrage;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "OuvrageMultimedia{" +
                "idDocument=" + getIdDocument() +
                ", titre='" + getTitre() + '\'' +
                ", editeur='" + getEditeur() + '\'' +
                ", qteTotale=" + getQteTotale() +
                ", qteDisponible=" + getQteDisponible() +
                ", datePublication=" + getDatePublication() +
                ", typeDocument='" + getTypeDocument() + '\'' +
                ", imageUrl='" + getImageUrl() + '\'' +
                ", typeOuvrage='" + typeOuvrage + '\'' +
                ", duree=" + duree +
                '}';
    }
}