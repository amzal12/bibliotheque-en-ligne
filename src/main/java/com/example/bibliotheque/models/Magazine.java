package com.example.bibliotheque.models;

import java.time.LocalDate;

public class Magazine extends Document {
    private String frequence;
    private int numParution;

    // Constructeur par défaut
    public Magazine() {
    }

    // Constructeur avec paramètres
    public Magazine(int idDocument, String titre, String editeur, int qteTotale, int qteDisponible, LocalDate datePublication, String frequence, int numParution, String imageUrl) {
        super(idDocument, titre, editeur, qteTotale, qteDisponible, datePublication, "Magazine", imageUrl);
        this.frequence = frequence;
        this.numParution = numParution;
    }

    // Getters et setters
    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    public int getNumParution() {
        return numParution;
    }

    public void setNumParution(int numParution) {
        this.numParution = numParution;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "idDocument=" + getIdDocument() +
                ", titre='" + getTitre() + '\'' +
                ", editeur='" + getEditeur() + '\'' +
                ", qteTotale=" + getQteTotale() +
                ", qteDisponible=" + getQteDisponible() +
                ", datePublication=" + getDatePublication() +
                ", typeDocument='" + getTypeDocument() + '\'' +
                ", imageUrl='" + getImageUrl() + '\'' +
                ", frequence='" + frequence + '\'' +
                ", numParution=" + numParution +
                '}';
    }
}
