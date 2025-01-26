package com.example.bibliotheque.repositories;

import com.example.bibliotheque.models.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class JournalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper pour mapper les résultats de la base de données à un objet Journal
    private static final class JournalRowMapper implements RowMapper<Journal> {
        @Override
        public Journal mapRow(ResultSet rs, int rowNum) throws SQLException {
            Journal journal = new Journal();
            journal.setIdDocument(rs.getInt("idDocument"));
            journal.setTitre(rs.getString("titre"));
            journal.setEditeur(rs.getString("editeur"));
            journal.setQteTotale(rs.getInt("qteTotale"));
            journal.setQteDisponible(rs.getInt("qteDisponible"));
            journal.setDatePublication(rs.getObject("datePublication", LocalDate.class));
            journal.setPeriodicite(rs.getString("periodicite"));
            journal.setImageUrl(rs.getString("imageUrl"));
            return journal;
        }
    }

    // Méthode pour récupérer tous les journaux
    public List<Journal> findAll() {
        String sql = "SELECT d.idDocument, d.titre, d.editeur, d.qteTotale, d.qteDisponible, d.datePublication, d.imageUrl, j.periodicite " +
                     "FROM Document d " +
                     "JOIN Journal j ON d.idDocument = j.idDocument " +
                     "WHERE d.typeDocument = 'Journal'";
        return jdbcTemplate.query(sql, new JournalRowMapper());
    }

    // Méthode pour insérer un journal
    public void save(Journal journal) {
        // Insérer dans la table Document
        String sqlDocument = "INSERT INTO Document (idDocument, titre, editeur, qteTotale, qteDisponible, datePublication, typeDocument, imageUrl) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlDocument, journal.getIdDocument(), journal.getTitre(), journal.getEditeur(), journal.getQteTotale(), journal.getQteDisponible(), journal.getDatePublication(), journal.getTypeDocument(), journal.getImageUrl());

        // Insérer dans la table Journal
        String sqlJournal = "INSERT INTO Journal (idDocument, periodicite) VALUES (?, ?)";
        jdbcTemplate.update(sqlJournal, journal.getIdDocument(), journal.getPeriodicite());
    }

    // Méthode pour mettre à jour un journal
    public void update(Journal journal) {
        // Mettre à jour la table Document
        String sqlDocument = "UPDATE Document SET titre = ?, editeur = ?, qteTotale = ?, qteDisponible = ?, datePublication = ?, imageUrl = ? WHERE idDocument = ?";
        jdbcTemplate.update(sqlDocument, journal.getTitre(), journal.getEditeur(), journal.getQteTotale(), journal.getQteDisponible(), journal.getDatePublication(), journal.getImageUrl(), journal.getIdDocument());

        // Mettre à jour la table Journal
        String sqlJournal = "UPDATE Journal SET periodicite = ? WHERE idDocument = ?";
        jdbcTemplate.update(sqlJournal, journal.getPeriodicite(), journal.getIdDocument());
    }

    // Méthode pour supprimer un journal
    public void deleteById(int idDocument) {
        // Supprimer de la table Journal
        String sqlJournal = "DELETE FROM Journal WHERE idDocument = ?";
        jdbcTemplate.update(sqlJournal, idDocument);

        // Supprimer de la table Document
        String sqlDocument = "DELETE FROM Document WHERE idDocument = ?";
        jdbcTemplate.update(sqlDocument, idDocument);
    }

    // Méthode pour trouver un journal par son ID
    public Journal findById(int idDocument) {
        String sql = "SELECT d.idDocument, d.titre, d.editeur, d.qteTotale, d.qteDisponible, d.datePublication, d.imageUrl, j.periodicite " +
                     "FROM Document d " +
                     "JOIN Journal j ON d.idDocument = j.idDocument " +
                     "WHERE d.idDocument = ? AND d.typeDocument = 'Journal'";
        return jdbcTemplate.queryForObject(sql, new JournalRowMapper(), idDocument);
    }
}