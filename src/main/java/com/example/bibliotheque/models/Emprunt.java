package com.example.bibliotheque.models;

import java.util.Date;

public class Emprunt {
    private int idPersonne;
    private int idDocument;
    private Date dateEmprunt;
    private Date dateRetour;
    private EtatEmprunt etat; // Utilisation de l'enum

    // Constructeurs
    public Emprunt() {}

    public Emprunt(int idPersonne, int idDocument, Date dateEmprunt, Date dateRetour, EtatEmprunt etat) {
        this.idPersonne = idPersonne;
        this.idDocument = idDocument;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.etat = etat;
    }


    public int getidPersonne() {
        return idPersonne;
    }

    public void setidPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
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