package com.example.bibliotheque.repositories;

import com.example.bibliotheque.models.Magazine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class MagazineRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper pour mapper les résultats de la base de données à un objet Magazine
    private static final class MagazineRowMapper implements RowMapper<Magazine> {
        @Override
        public Magazine mapRow(ResultSet rs, int rowNum) throws SQLException {
            Magazine magazine = new Magazine();
            magazine.setIdDocument(rs.getInt("idDocument"));
            magazine.setTitre(rs.getString("titre"));
            magazine.setEditeur(rs.getString("editeur"));
            magazine.setQteTotale(rs.getInt("qteTotale"));
            magazine.setQteDisponible(rs.getInt("qteDisponible"));
            magazine.setDatePublication(rs.getObject("datePublication", LocalDate.class));
            magazine.setFrequence(rs.getString("frequence"));
            magazine.setNumParution(rs.getInt("numParution"));
            magazine.setImageUrl(rs.getString("imageUrl"));
            return magazine;
        }
    }

    // Méthode pour récupérer tous les magazines
    public List<Magazine> findAll() {
        String sql = "SELECT d.idDocument, d.titre, d.editeur, d.qteTotale, d.qteDisponible, d.datePublication, d.imageUrl, m.frequence, m.numParution " +
                     "FROM documents d " +
                     "JOIN Magazine m ON d.idDocument = m.idDocument " +
                     "WHERE d.typeDocument = 'Magazine'";
        return jdbcTemplate.query(sql, new MagazineRowMapper());
    }

    // Méthode pour insérer un magazine
    public void save(Magazine magazine) {
        // Insérer dans la table Document
        String sqlDocument = "INSERT INTO documents (idDocument, titre, editeur, qteTotale, qteDisponible, datePublication, typeDocument, imageUrl) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlDocument, magazine.getIdDocument(), magazine.getTitre(), magazine.getEditeur(), magazine.getQteTotale(), magazine.getQteDisponible(), magazine.getDatePublication(), magazine.getTypeDocument(), magazine.getImageUrl());

        // Insérer dans la table Magazine
        String sqlMagazine = "INSERT INTO Magazine (idDocument, frequence, numParution) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlMagazine, magazine.getIdDocument(), magazine.getFrequence(), magazine.getNumParution());
    }

    // Méthode pour mettre à jour un magazine
    public void update(Magazine magazine) {
        // Mettre à jour la table Document
        String sqlDocument = "UPDATE documents SET titre = ?, editeur = ?, qteTotale = ?, qteDisponible = ?, datePublication = ?, imageUrl = ? WHERE idDocument = ?";
        jdbcTemplate.update(sqlDocument, magazine.getTitre(), magazine.getEditeur(), magazine.getQteTotale(), magazine.getQteDisponible(), magazine.getDatePublication(), magazine.getImageUrl(), magazine.getIdDocument());

        // Mettre à jour la table Magazine
        String sqlMagazine = "UPDATE Magazine SET frequence = ?, numParution = ? WHERE idDocument = ?";
        jdbcTemplate.update(sqlMagazine, magazine.getFrequence(), magazine.getNumParution(), magazine.getIdDocument());
    }

    // Méthode pour supprimer un magazine
    public void deleteById(int idDocument) {
        // Supprimer de la table Magazine
        String sqlMagazine = "DELETE FROM Magazine WHERE idDocument = ?";
        jdbcTemplate.update(sqlMagazine, idDocument);

        // Supprimer de la table Document
        String sqlDocument = "DELETE FROM documents WHERE idDocument = ?";
        jdbcTemplate.update(sqlDocument, idDocument);
    }

    // Méthode pour trouver un magazine par son ID
    public Magazine findById(int idDocument) {
        String sql = "SELECT d.idDocument, d.titre, d.editeur, d.qteTotale, d.qteDisponible, d.datePublication, d.imageUrl, m.frequence, m.numParution " +
                     "FROM documents d " +
                     "JOIN Magazine m ON d.idDocument = m.idDocument " +
                     "WHERE d.idDocument = ? AND d.typeDocument = 'Magazine'";
        return jdbcTemplate.queryForObject(sql, new MagazineRowMapper(), idDocument);
    }
}

