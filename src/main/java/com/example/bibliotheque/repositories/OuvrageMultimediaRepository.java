package com.example.bibliotheque.repositories;

import com.example.bibliotheque.models.OuvrageMultimedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class OuvrageMultimediaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper pour mapper les résultats de la base de données à un objet OuvrageMultimedia
    private static final class OuvrageMultimediaRowMapper implements RowMapper<OuvrageMultimedia> {
        @Override
        public OuvrageMultimedia mapRow(ResultSet rs, int rowNum) throws SQLException {
            OuvrageMultimedia ouvrageMultimedia = new OuvrageMultimedia();
            ouvrageMultimedia.setIdDocument(rs.getInt("idDocument"));
            ouvrageMultimedia.setTitre(rs.getString("titre"));
            ouvrageMultimedia.setEditeur(rs.getString("editeur"));
            ouvrageMultimedia.setQteTotale(rs.getInt("qteTotale"));
            ouvrageMultimedia.setQteDisponible(rs.getInt("qteDisponible"));
            ouvrageMultimedia.setDatePublication(rs.getObject("datePublication", LocalDate.class));
            ouvrageMultimedia.setTypeOuvrage(rs.getString("typeOuvrage"));
            ouvrageMultimedia.setDuree(rs.getInt("duree"));
            ouvrageMultimedia.setImageUrl(rs.getString("imageUrl"));
            return ouvrageMultimedia;
        }
    }

    // Méthode pour récupérer tous les ouvrages multimédias
    public List<OuvrageMultimedia> findAll() {
        String sql = "SELECT d.idDocument, d.titre, d.editeur, d.qteTotale, d.qteDisponible, d.datePublication, d.imageUrl, o.typeOuvrage, o.duree " +
                     "FROM documents d " +
                     "JOIN OuvrageMultimedia o ON d.idDocument = o.idDocument " +
                     "WHERE d.typeDocument = 'OuvrageMultimedia'";
        return jdbcTemplate.query(sql, new OuvrageMultimediaRowMapper());
    }

    // Méthode pour insérer un ouvrage multimédia
    public void save(OuvrageMultimedia ouvrageMultimedia) {
        // Insérer dans la table Document
        String sqlDocument = "INSERT INTO documents (idDocument, titre, editeur, qteTotale, qteDisponible, datePublication, typeDocument, imageUrl) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlDocument, ouvrageMultimedia.getIdDocument(), ouvrageMultimedia.getTitre(), ouvrageMultimedia.getEditeur(), ouvrageMultimedia.getQteTotale(), ouvrageMultimedia.getQteDisponible(), ouvrageMultimedia.getDatePublication(), ouvrageMultimedia.getTypeDocument(), ouvrageMultimedia.getImageUrl());

        // Insérer dans la table OuvrageMultimedia
        String sqlOuvrageMultimedia = "INSERT INTO OuvrageMultimedia (idDocument, typeOuvrage, duree) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlOuvrageMultimedia, ouvrageMultimedia.getIdDocument(), ouvrageMultimedia.getTypeOuvrage(), ouvrageMultimedia.getDuree());
    }

    // Méthode pour mettre à jour un ouvrage multimédia
    public void update(OuvrageMultimedia ouvrageMultimedia) {
        // Mettre à jour la table Document
        String sqlDocument = "UPDATE documents SET titre = ?, editeur = ?, qteTotale = ?, qteDisponible = ?, datePublication = ?, imageUrl = ? WHERE idDocument = ?";
        jdbcTemplate.update(sqlDocument, ouvrageMultimedia.getTitre(), ouvrageMultimedia.getEditeur(), ouvrageMultimedia.getQteTotale(), ouvrageMultimedia.getQteDisponible(), ouvrageMultimedia.getDatePublication(), ouvrageMultimedia.getImageUrl(), ouvrageMultimedia.getIdDocument());

        // Mettre à jour la table OuvrageMultimedia
        String sqlOuvrageMultimedia = "UPDATE OuvrageMultimedia SET typeOuvrage = ?, duree = ? WHERE idDocument = ?";
        jdbcTemplate.update(sqlOuvrageMultimedia, ouvrageMultimedia.getTypeOuvrage(), ouvrageMultimedia.getDuree(), ouvrageMultimedia.getIdDocument());
    }

    // Méthode pour supprimer un ouvrage multimédia
    public void deleteById(int idDocument) {
        // Supprimer de la table OuvrageMultimedia
        String sqlOuvrageMultimedia = "DELETE FROM OuvrageMultimedia WHERE idDocument = ?";
        jdbcTemplate.update(sqlOuvrageMultimedia, idDocument);

        // Supprimer de la table Document
        String sqlDocument = "DELETE FROM documents WHERE idDocument = ?";
        jdbcTemplate.update(sqlDocument, idDocument);
    }

    // Méthode pour trouver un ouvrage multimédia par son ID
    public OuvrageMultimedia findById(int idDocument) {
        String sql = "SELECT d.idDocument, d.titre, d.editeur, d.qteTotale, d.qteDisponible, d.datePublication, d.imageUrl, o.typeOuvrage, o.duree " +
                     "FROM documents d " +
                     "JOIN OuvrageMultimedia o ON d.idDocument = o.idDocument " +
                     "WHERE d.idDocument = ? AND d.typeDocument = 'OuvrageMultimedia'";
        return jdbcTemplate.queryForObject(sql, new OuvrageMultimediaRowMapper(), idDocument);
    }
}
