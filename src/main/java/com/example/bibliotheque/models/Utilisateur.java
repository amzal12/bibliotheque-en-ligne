package com.example.bibliotheque.models;

public class Utilisateur extends Personne {

    // Constructeur par défaut
    public Utilisateur() {
    }

    // Constructeur avec paramètres
    public Utilisateur(int idPersonne, String nom, String nomUtilisateur, String telephone, String mail, String motDePasse) {
        super(idPersonne, nom, nomUtilisateur, telephone, mail, motDePasse, "Utilisateur"); // Rôle spécifique à l'utilisateur
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idPersonne=" + getIdPersonne() +
                ", nom='" + getNom() + '\'' +
                ", nomUtilisateur='" + getNomUtilisateur() + '\'' +
                ", telephone='" + getTelephone() + '\'' +
                ", mail='" + getMail() + '\'' +
                ", motDePasse='" + getMotDePasse() + '\'' +
                ", role='" + getRole() + '\'' +
                '}';
    }
}
