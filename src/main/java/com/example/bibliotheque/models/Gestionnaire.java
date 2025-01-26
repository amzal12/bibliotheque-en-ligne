package com.example.bibliotheque.models;


public class Gestionnaire extends Personne {

    public Gestionnaire() {
    }

    // Constructeur avec paramètres
    public Gestionnaire(int idPersonne, String nom, String nomUtilisateur, String telephone, String mail, String motDePasse) {
        // Appel du constructeur de la classe parente avec un rôle spécifique
        super(idPersonne, nom, nomUtilisateur, telephone, mail, motDePasse, "Gestionnaire"); // Suivi de la convention Spring Security pour le rôle
    }
    
    // Cette classe peut être étendue si nécessaire pour ajouter des fonctionnalités spécifiques au gestionnaire
}
