package com.example.bibliotheque.models;

import java.time.LocalDate;

public class Livre extends Document {
    private String isbn;
    private String genre;
    private int nombrePages;

    // Constructeur par défaut
    public Livre() {
    }

    // Constructeur avec paramètres
    public Livre(int idDocument, String titre, String editeur, int qteTotale, int qteDisponible, LocalDate datePublication, String isbn, String genre, int nombrePages, String imageUrl) {
        super(idDocument, titre, editeur, qteTotale, qteDisponible, datePublication, "Livre", imageUrl);
        this.isbn = isbn;
        this.genre = genre;
        this.nombrePages = nombrePages;
    }

    // Getters et setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getNombrePages() {
        return nombrePages;
    }

    public void setNombrePages(int nombrePages) {
        this.nombrePages = nombrePages;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "idDocument=" + getIdDocument() +
                ", titre='" + getTitre() + '\'' +
                ", editeur='" + getEditeur() + '\'' +
                ", qteTotale=" + getQteTotale() +
                ", qteDisponible=" + getQteDisponible() +
                ", datePublication=" + getDatePublication() +
                ", typeDocument='" + getTypeDocument() + '\'' +
                ", imageUrl='" + getImageUrl() + '\'' +
                ", isbn='" + isbn + '\'' +
                ", genre='" + genre + '\'' +
                ", nombrePages=" + nombrePages +
                '}';
    }
}