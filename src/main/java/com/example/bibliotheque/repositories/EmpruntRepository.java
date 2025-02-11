package com.example.bibliotheque.repositories;

import com.example.bibliotheque.models.Document;
import com.example.bibliotheque.models.Emprunt;
import com.example.bibliotheque.models.EtatEmprunt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmpruntRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // L'injection de DocumentRepository reste possible si vous souhaitez l'utiliser ailleurs.
    @Autowired
    private DocumentRepository documentRepository;

    private static final class EmpruntRowMapper implements RowMapper<Emprunt> {
        @Override
        public Emprunt mapRow(ResultSet rs, int rowNum) throws SQLException {
            Emprunt emprunt = new Emprunt();
            emprunt.setIdEmprunt(rs.getInt("idEmprunt"));
            emprunt.setIdUtilisateur(rs.getInt("idUtilisateur"));
            emprunt.setIdGestionnaire(rs.getInt("idGestionnaire"));
            emprunt.setIdDocument(rs.getInt("idDocument"));
            emprunt.setEtat(EtatEmprunt.fromValeur(rs.getString("etat")));
            return emprunt;
        }
    }

    public void addEmprunt(Emprunt emprunt) {
        String sql = "INSERT INTO emprunt (idUtilisateur, idGestionnaire, idDocument, dateEmprunt, dateRetour, etat) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                emprunt.getIdUtilisateur(),
                emprunt.getIdGestionnaire(),
                emprunt.getIdDocument(),
                emprunt.getDateEmprunt(),
                emprunt.getDateRetour(),
                emprunt.getEtat().getValeur());
    }

    // Méthode pour ajouter un document au panier
    public void addDocumentToCart(int idUtilisateur, int idDocument, int idGestionnaire) {
        String sql = "INSERT INTO emprunt (idUtilisateur, idGestionnaire, idDocument, dateEmprunt, dateRetour, etat) " +
                     "VALUES (?, ?, ?, CURRENT_DATE, NULL, ?)";
        jdbcTemplate.update(sql,
                idUtilisateur,
                idGestionnaire,
                idDocument,
                EtatEmprunt.EMPRUNTE.getValeur());
    }

    public void updateEmprunt(Emprunt emprunt) {
        String sql = "UPDATE emprunt SET idUtilisateur = ?, idGestionnaire = ?, idDocument = ?, dateEmprunt = ?, dateRetour = ?, etat = ? " +
                     "WHERE idEmprunt = ?";
        jdbcTemplate.update(sql,
                emprunt.getIdUtilisateur(),
                emprunt.getIdGestionnaire(),
                emprunt.getIdDocument(),
                emprunt.getDateEmprunt(),
                emprunt.getDateRetour(),
                emprunt.getEtat().getValeur(),
                emprunt.getIdEmprunt());
    }

    public void deleteEmprunt(int idEmprunt) {
        String sql = "DELETE FROM emprunt WHERE idEmprunt = ?";
        jdbcTemplate.update(sql, idEmprunt);
    }

    public Emprunt findById(int idEmprunt) {
        String sql = "SELECT * FROM emprunt WHERE idEmprunt = ?";
        return jdbcTemplate.queryForObject(sql, new EmpruntRowMapper(), idEmprunt);
    }

    public List<Emprunt> findAllByUserId(int idUtilisateur) {
        String sql = "SELECT * FROM emprunt WHERE idUtilisateur = ?";
        return jdbcTemplate.query(sql, new EmpruntRowMapper(), idUtilisateur);
    }

    public List<Emprunt> findAll() {
        String sql = "SELECT * FROM emprunt";
        return jdbcTemplate.query(sql, new EmpruntRowMapper());
    }

    public boolean isDocumentAlreadyBorrowed(int idUtilisateur, int idDocument) {
        String sql = "SELECT COUNT(*) FROM emprunt WHERE idUtilisateur = ? AND idDocument = ? AND etat = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idUtilisateur, idDocument, EtatEmprunt.EMPRUNTE.getValeur());
        return count != null && count > 0;
    }

    public void retournerDocument(int idEmprunt) {
        String sql = "UPDATE emprunt SET etat = ? WHERE idEmprunt = ?";
        jdbcTemplate.update(sql, EtatEmprunt.RETOURNE.getValeur(), idEmprunt);
    }

    public void signalerDocumentEndommage(int idEmprunt) {
        String sql = "UPDATE emprunt SET etat = ? WHERE idEmprunt = ?";
        jdbcTemplate.update(sql, EtatEmprunt.ENDOMMAGE.getValeur(), idEmprunt);
    }

    public void removeDocumentFromCart(int idUtilisateur, int idDocument) {
        String sql = "DELETE FROM emprunt WHERE idUtilisateur = ? AND idDocument = ? AND etat = ?";
        jdbcTemplate.update(sql, idUtilisateur, idDocument, EtatEmprunt.EMPRUNTE.getValeur());
    }

    // Utilisation du DocumentRowMapper désormais public et statique.
    public List<Document> getCartItems(int idUtilisateur) {
        String sql = "SELECT * FROM document d JOIN emprunt e ON d.idDocument = e.idDocument " +
                     "WHERE e.idUtilisateur = ? AND e.etat = ?";
        return jdbcTemplate.query(sql, new DocumentRepository.DocumentRowMapper(), idUtilisateur, EtatEmprunt.EMPRUNTE.getValeur());
    }
}
