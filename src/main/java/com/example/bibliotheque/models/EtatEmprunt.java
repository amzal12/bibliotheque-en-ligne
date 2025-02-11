package com.example.bibliotheque.models;

public enum EtatEmprunt {
    EMPRUNTE("emprunté"),
    RETOURNE("retourné"),
    ENDOMMAGE("endommagé");

    private final String valeur;

    EtatEmprunt(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return valeur;
    }

    // Méthode pour convertir une chaîne en enum
    public static EtatEmprunt fromValeur(String valeur) {
        for (EtatEmprunt etat : EtatEmprunt.values()) {
            if (etat.getValeur().equalsIgnoreCase(valeur)) {
                return etat;
            }
        }
        throw new IllegalArgumentException("État inconnu : " + valeur);
    }
}
