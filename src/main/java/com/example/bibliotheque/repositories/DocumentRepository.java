package com.example.bibliotheque.repositories;

import com.example.bibliotheque.models.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DocumentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper pour mapper les résultats de la base de données à un objet Document
    private static final class DocumentRowMapper implements RowMapper<Document> {
        @Override
        public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
            Document document = new Document();
            document.setIdDocument(rs.getInt("idDocument"));
            document.setTitre(rs.getString("titre"));
            document.setEditeur(rs.getString("editeur"));
            document.setQteTotale(rs.getInt("qteTotale"));
            document.setQteDisponible(rs.getInt("qteDisponible"));
            document.setDatePublication(rs.getObject("datePublication", LocalDate.class));
            document.setTypeDocument(rs.getString("typeDocument"));
            document.setImageUrl(rs.getString("imageUrl"));
            return document;
        }
    }

    // Méthode pour récupérer tous les documents
    public List<Document> findAll() {
        String sql = "SELECT * FROM documents";
        return jdbcTemplate.query(sql, new DocumentRowMapper());
    }

    // Méthode pour insérer un document
    public void save(Document document) {
        String sql = "INSERT INTO documents (idDocument, titre, editeur, qteTotale, qteDisponible, datePublication, typeDocument, imageUrl) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, document.getIdDocument(), document.getTitre(), document.getEditeur(), document.getQteTotale(), document.getQteDisponible(), document.getDatePublication(), document.getTypeDocument(), document.getImageUrl());
    }

    // Méthode pour mettre à jour un document
    public void update(Document document) {
        String sql = "UPDATE documents SET titre = ?, editeur = ?, qteTotale = ?, qteDisponible = ?, datePublication = ?, typeDocument = ?, imageUrl = ? WHERE idDocument = ?";
        jdbcTemplate.update(sql, document.getTitre(), document.getEditeur(), document.getQteTotale(), document.getQteDisponible(), document.getDatePublication(), document.getTypeDocument(), document.getImageUrl(), document.getIdDocument());
    }

    // Méthode pour supprimer un document
    public void deleteById(int idDocument) {
        String sql = "DELETE FROM documents WHERE idDocument = ?";
        jdbcTemplate.update(sql, idDocument);
    }

    // Méthode pour trouver un document par son ID
    public Document findById(int idDocument) {
        String sql = "SELECT * FROM documents WHERE idDocument = ?";
        return jdbcTemplate.queryForObject(sql, new DocumentRowMapper(), idDocument);
    }
}
