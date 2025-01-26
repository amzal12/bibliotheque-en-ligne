package com.example.bibliotheque.repositories;

import com.example.bibliotheque.models.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class LivreRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper pour mapper les résultats de la base de données à un objet Livre
    private static final class LivreRowMapper implements RowMapper<Livre> {
        @Override
        public Livre mapRow(ResultSet rs, int rowNum) throws SQLException {
            Livre livre = new Livre();
            livre.setIdDocument(rs.getInt("idDocument"));
            livre.setTitre(rs.getString("titre"));
            livre.setEditeur(rs.getString("editeur"));
            livre.setQteTotale(rs.getInt("qteTotale"));
            livre.setQteDisponible(rs.getInt("qteDisponible"));
            livre.setDatePublication(rs.getObject("datePublication", LocalDate.class));
            livre.setIsbn(rs.getString("isbn"));
            livre.setGenre(rs.getString("genre"));
            livre.setNombrePages(rs.getInt("nombrePages"));
            livre.setImageUrl(rs.getString("imageUrl"));
            return livre;
        }
    }

    // Méthode pour récupérer tous les livres
    public List<Livre> findAll() {
        String sql = "SELECT d.idDocument, d.titre, d.editeur, d.qteTotale, d.qteDisponible, d.datePublication, d.imageUrl, l.isbn, l.genre, l.nombrePages " +
                     "FROM documents d " +
                     "JOIN Livre l ON d.idDocument = l.idDocument " +
                     "WHERE d.typeDocument = 'Livre'";
        return jdbcTemplate.query(sql, new LivreRowMapper());
    }

    // Méthode pour insérer un livre
    public void save(Livre livre) {
        // Insérer dans la table Documents
        String sqlDocument = "INSERT INTO documents (idDocument, titre, editeur, qteTotale, qteDisponible, datePublication, typeDocument, imageUrl) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlDocument, livre.getIdDocument(), livre.getTitre(), livre.getEditeur(), livre.getQteTotale(), livre.getQteDisponible(), livre.getDatePublication(), livre.getTypeDocument(), livre.getImageUrl());

        // Insérer dans la table Livre
        String sqlLivre = "INSERT INTO Livre (idDocument, isbn, genre, nombrePages) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sqlLivre, livre.getIdDocument(), livre.getIsbn(), livre.getGenre(), livre.getNombrePages());
    }

    // Méthode pour mettre à jour un livre
    public void update(Livre livre) {
        // Mettre à jour la table Document
        String sqlDocument = "UPDATE documents SET titre = ?, editeur = ?, qteTotale = ?, qteDisponible = ?, datePublication = ?, imageUrl = ? WHERE idDocument = ?";
        jdbcTemplate.update(sqlDocument, livre.getTitre(), livre.getEditeur(), livre.getQteTotale(), livre.getQteDisponible(), livre.getDatePublication(), livre.getImageUrl(), livre.getIdDocument());

        // Mettre à jour la table Livre
        String sqlLivre = "UPDATE Livre SET isbn = ?, genre = ?, nombrePages = ? WHERE idDocument = ?";
        jdbcTemplate.update(sqlLivre, livre.getIsbn(), livre.getGenre(), livre.getNombrePages(), livre.getIdDocument());
    }

    // Méthode pour supprimer un livre
    public void deleteById(int idDocument) {
        // Supprimer de la table Livre
        String sqlLivre = "DELETE FROM Livre WHERE idDocument = ?";
        jdbcTemplate.update(sqlLivre, idDocument);

        // Supprimer de la table Document
        String sqlDocument = "DELETE FROM documents WHERE idDocument = ?";
        jdbcTemplate.update(sqlDocument, idDocument);
    }

    // Méthode pour trouver un livre par son ID
    public Livre findById(int idDocument) {
        String sql = "SELECT d.idDocument, d.titre, d.editeur, d.qteTotale, d.qteDisponible, d.datePublication, d.imageUrl, l.isbn, l.genre, l.nombrePages " +
                     "FROM documents d " +
                     "JOIN Livre l ON d.idDocument = l.idDocument " +
                     "WHERE d.idDocument = ? AND d.typeDocument = 'Livre'";
        return jdbcTemplate.queryForObject(sql, new LivreRowMapper(), idDocument);
    }
}
