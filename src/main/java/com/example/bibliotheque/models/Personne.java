package com.example.bibliotheque.models;


public abstract class Personne {

    private int idPersonne;
    private String nom;
    private String nomUtilisateur;
    private String telephone;
    private String mail;
    private String motDePasse;
    protected String role; 

    public Personne(){
    }

    // Constructeur avec paramètres
    public Personne(int idPersonne, String nom, String nomUtilisateur, String telephone, String mail, String motDePasse, String role) {
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.nomUtilisateur = nomUtilisateur;
        this.telephone = telephone;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    // Getters et setters pour tous les champs, y compris role
    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
