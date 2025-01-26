package com.example.bibliotheque.models;

public class Auteur extends Personne {

    private String nationalite;
    private String biographie;

    // Constructeur par défaut
    public Auteur() {
    }

    // Constructeur avec paramètres
    public Auteur(int idPersonne, String nom, String nomUtilisateur, String telephone, String mail, String motDePasse, String nationalite, String biographie) {
        super(idPersonne, nom, nomUtilisateur, telephone, mail, motDePasse, "Auteur"); // Rôle spécifique à l'auteur
        this.nationalite = nationalite;
        this.biographie = biographie;
    }

    // Getters et setters pour nationalite et biographie
    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    @Override
    public String toString() {
        return "Auteur{" +
                "idPersonne=" + getIdPersonne() +
                ", nom='" + getNom() + '\'' +
                ", nomUtilisateur='" + getNomUtilisateur() + '\'' +
                ", telephone='" + getTelephone() + '\'' +
                ", mail='" + getMail() + '\'' +
                ", motDePasse='" + getMotDePasse() + '\'' +
                ", role='" + getRole() + '\'' +
                ", nationalite='" + nationalite + '\'' +
                ", biographie='" + biographie + '\'' +
                '}';
    }
}