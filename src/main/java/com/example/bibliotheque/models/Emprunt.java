package com.example.bibliotheque.models;

import java.util.Date;

public class Emprunt {
    private int idEmprunt;
    private int idUtilisateur;
    private int idGestionnaire;
    private int idDocument;
    private Date dateEmprunt;
    private Date dateRetour;
    private EtatEmprunt etat; // Utilisation de l'enum

    // Constructeurs
    public Emprunt() {}

    public Emprunt(int idUtilisateur, int idGestionnaire, int idDocument, Date dateEmprunt, Date dateRetour, EtatEmprunt etat) {
        this.idUtilisateur = idUtilisateur;
        this.idGestionnaire = idGestionnaire;
        this.idDocument = idDocument;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.etat = etat;
    }

    // Getters et Setters
    public int getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(int idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdGestionnaire() {
        return idGestionnaire;
    }

    public void setIdGestionnaire(int idGestionnaire) {
        this.idGestionnaire = idGestionnaire;
    }

    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
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

    public EtatEmprunt getEtat() {
        return etat;
    }

    public void setEtat(EtatEmprunt etat) {
        this.etat = etat;
    }
}