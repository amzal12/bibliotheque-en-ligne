package com.example.bibliotheque.repositories;

import com.example.bibliotheque.models.Auteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuteurRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper pour mapper les résultats de la base de données à un objet Auteur
    private static final class AuteurRowMapper implements RowMapper<Auteur> {
        @Override
        public Auteur mapRow(ResultSet rs, int rowNum) throws SQLException {
            Auteur auteur = new Auteur();
            auteur.setIdPersonne(rs.getInt("idPersonne"));
            auteur.setNom(rs.getString("nom"));
            auteur.setNomUtilisateur(rs.getString("nomUtilisateur"));
            auteur.setTelephone(rs.getString("telephone"));
            auteur.setMail(rs.getString("mail"));
            auteur.setMotDePasse(rs.getString("motDePasse"));
            auteur.setNationalite(rs.getString("nationalite"));
            auteur.setBiographie(rs.getString("biographie"));
            return auteur;
        }
    }

    // Méthode pour récupérer tous les auteurs
    public List<Auteur> findAll() {
        String sql = "SELECT * FROM Personne WHERE role = 'Auteur'";
        return jdbcTemplate.query(sql, new AuteurRowMapper());
    }

    // Méthode pour insérer un auteur
    public void save(Auteur auteur) {
        String sql = "INSERT INTO Personne (nom, nomUtilisateur, telephone, mail, motDePasse, role, nationalite, biographie) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, auteur.getNom(), auteur.getNomUtilisateur(), auteur.getTelephone(), auteur.getMail(), auteur.getMotDePasse(), auteur.getRole(), auteur.getNationalite(), auteur.getBiographie());
    }

    // Méthode pour mettre à jour un auteur
    public void update(Auteur auteur) {
        String sql = "UPDATE Personne SET nom = ?, nomUtilisateur = ?, telephone = ?, mail = ?, motDePasse = ?, nationalite = ?, biographie = ? WHERE idPersonne = ?";
        jdbcTemplate.update(sql, auteur.getNom(), auteur.getNomUtilisateur(), auteur.getTelephone(), auteur.getMail(), auteur.getMotDePasse(), auteur.getNationalite(), auteur.getBiographie(), auteur.getIdPersonne());
    }

    // Méthode pour supprimer un auteur
    public void deleteById(int idPersonne) {
        String sql = "DELETE FROM Personne WHERE idPersonne = ?";
        jdbcTemplate.update(sql, idPersonne);
    }

    // Méthode pour trouver un auteur par son ID
    public Auteur findById(int idPersonne) {
        String sql = "SELECT * FROM Personne WHERE idPersonne = ? AND role = 'Auteur'";
        return jdbcTemplate.queryForObject(sql, new AuteurRowMapper(), idPersonne);
    }
}
