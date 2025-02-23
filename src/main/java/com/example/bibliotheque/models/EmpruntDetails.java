package com.example.bibliotheque.models;

import java.util.Date;

public class EmpruntDetails {

    private String nomUtilisateur;
    private String typeDocument;
    private String editeur;
    private String titre;
    private String imageUrl;
    private int qteDisponible;
    private int qteTotale;
    private Date dateEmprunt;
    private Date dateRetour;
    private String etat;

    // Getters et Setters

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQteDisponible() {
        return qteDisponible;
    }

    public void setQteDisponible(int qteDisponible) {
        this.qteDisponible = qteDisponible;
    }

    public int getQteTotale() {
        return qteTotale;
    }

    public void setQteTotale(int qteTotale) {
        this.qteTotale = qteTotale;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
