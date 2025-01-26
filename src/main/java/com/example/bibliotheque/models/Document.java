package com.example.bibliotheque.models;

import java.time.LocalDate;

public class Document {
    private int idDocument;
    private String titre;
    private String editeur;
    private int qteTotale;
    private int qteDisponible;
    private LocalDate datePublication; 
    private String typeDocument;  
    private String imageUrl;      

    public Document() {}

    // Constructeur
    public Document(int idDocument, String titre, String editeur, int qteTotale, int qteDisponible, LocalDate datePublication, String typeDocument, String imageUrl) {
        this.idDocument = idDocument;
        this.titre = titre;
        this.editeur = editeur;
        this.qteTotale = qteTotale;
        this.qteDisponible = qteDisponible;
        this.datePublication = datePublication;
        this.typeDocument = typeDocument;
        this.imageUrl = imageUrl; 
    }

    // Getters et setters
    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public int getQteTotale() {
        return qteTotale;
    }

    public void setQteTotale(int qteTotale) {
        this.qteTotale = qteTotale;
    }

    public int getQteDisponible() {
        return qteDisponible;
    }

    public void setQteDisponible(int qteDisponible) {
        this.qteDisponible = qteDisponible;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Document{" +
                "idDocument=" + idDocument +
                ", titre='" + titre + '\'' +
                ", editeur='" + editeur + '\'' +
                ", qteTotale=" + qteTotale +
                ", qteDisponible=" + qteDisponible +
                ", datePublication=" + datePublication +
                ", typeDocument='" + typeDocument + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
