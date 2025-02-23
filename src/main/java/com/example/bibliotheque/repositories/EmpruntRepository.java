package com.example.bibliotheque.repositories;

import com.example.bibliotheque.models.Emprunt;
import com.example.bibliotheque.models.EtatEmprunt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EmpruntRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Ajouter un emprunt à la base de données
    public void addEmprunt(Emprunt emprunt) {
        String sql = "INSERT INTO emprunt (idPersonne, idDocument, dateEmprunt, dateRetour, etat) " +
                         "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            emprunt.getidPersonne(),
            emprunt.getIdDocument(),
            emprunt.getDateEmprunt(),
            emprunt.getDateRetour(),
            emprunt.getEtat().getValeur());
    }

    // Vérifier si un document est déjà emprunté par un utilisateur
    public boolean isDocumentAlreadyBorrowed(int idPersonne, int idDocument) {
        String sql = "SELECT COUNT(*) FROM emprunt WHERE idPersonne = ? AND idDocument = ? AND etat = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{idPersonne, idDocument, EtatEmprunt.EMPRUNTE.getValeur()}, Integer.class);
        return count != null && count > 0;
    }

    // Retourner un document emprunté
    public void retournerDocument(int idEmprunt) {
        String sql = "UPDATE emprunt SET etat = ? WHERE idEmprunt = ?";
        jdbcTemplate.update(sql, EtatEmprunt.RETOURNE.getValeur(), idEmprunt);
    }

    // Signaler qu'un document est endommagé
    public void signalerDocumentEndommage(int idEmprunt) {
        String sql = "UPDATE emprunt SET etat = ? WHERE idEmprunt = ?";
        jdbcTemplate.update(sql, EtatEmprunt.ENDOMMAGE.getValeur(), idEmprunt);
    }

    // Supprimer un document de la liste des emprunts
    public void removeDocumentFromCart(int idPersonne, int idDocument) {
        String sql = "DELETE FROM emprunt WHERE idPersonne = ? AND idDocument = ? AND etat = ?";
        jdbcTemplate.update(sql, idPersonne, idDocument, EtatEmprunt.EMPRUNTE.getValeur());
    }


    public List<Map<String, Object>> getCartItems(int idPersonne) {
        String sql = "SELECT d.*, e.dateEmprunt, e.dateRetour, e.etat " +
                 "FROM documents d " +
                 "JOIN emprunt e ON d.idDocument = e.idDocument " +
                 "WHERE e.idPersonne = ? AND e.etat = ?";
        return jdbcTemplate.queryForList(sql, idPersonne, EtatEmprunt.EMPRUNTE.getValeur());
    }

    public List<Map<String, Object>> getAllUserAndDocEmprenter() {
        String sql = "SELECT p.nomUtilisateur, d.typeDocument, d.editeur, d.titre, d.imageUrl, d.qteDisponible, d.qteTotale, e.dateEmprunt, e.dateRetour, e.etat " +
                     "FROM documents d " +
                     "JOIN emprunt e ON d.idDocument = e.idDocument " +
                     "JOIN personne p ON p.idPersonne = e.idPersonne " +
                     "WHERE p.role = 'Utilisateur'";
        
        return jdbcTemplate.queryForList(sql);
    }    

}

