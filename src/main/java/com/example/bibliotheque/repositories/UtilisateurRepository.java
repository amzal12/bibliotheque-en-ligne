package com.example.bibliotheque.repositories;

import com.example.bibliotheque.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UtilisateurRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper pour mapper les résultats de la base de données à un objet Utilisateur
    private static final class UtilisateurRowMapper implements RowMapper<Utilisateur> {
        @Override
        public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setIdPersonne(rs.getInt("idPersonne"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setNomUtilisateur(rs.getString("nomUtilisateur"));
            utilisateur.setTelephone(rs.getString("telephone"));
            utilisateur.setMail(rs.getString("mail"));
            utilisateur.setMotDePasse(rs.getString("motDePasse"));
            utilisateur.setRole(rs.getString("role"));
            return utilisateur;
        }
    }

    // Méthode pour récupérer un utilisateur par son nom d'utilisateur
    public Optional<Utilisateur> findByNomUtilisateur(String nomUtilisateur) {
        String sql = "SELECT p.idPersonne, p.nom, p.nomUtilisateur, p.telephone, p.mail, p.motDePasse, p.role " +
                     "FROM Personne p " +
                     "JOIN Utilisateur u ON p.idPersonne = u.idPersonne " +
                     "WHERE p.nomUtilisateur = ?";
        try {
            Utilisateur utilisateur = jdbcTemplate.queryForObject(sql, new UtilisateurRowMapper(), nomUtilisateur);
            return Optional.ofNullable(utilisateur);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // Méthode pour insérer un utilisateur
    public void save(Utilisateur utilisateur) {
        // Insérer dans la table Personne
        String sqlPersonne = "INSERT INTO Personne (nom, nomUtilisateur, telephone, mail, motDePasse, role) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlPersonne, utilisateur.getNom(), utilisateur.getNomUtilisateur(), utilisateur.getTelephone(), utilisateur.getMail(), utilisateur.getMotDePasse(), utilisateur.getRole());

        // Récupérer l'ID généré
        String sqlGetId = "SELECT idPersonne FROM Personne WHERE nomUtilisateur = ?";
        int idPersonne = jdbcTemplate.queryForObject(sqlGetId, Integer.class, utilisateur.getNomUtilisateur());

        // Insérer dans la table Utilisateur
        String sqlUtilisateur = "INSERT INTO Utilisateur (idPersonne) VALUES (?)";
        jdbcTemplate.update(sqlUtilisateur, idPersonne);
    }

    // Méthode pour mettre à jour un utilisateur
    public void update(Utilisateur utilisateur) {
        // Mettre à jour la table Personne
        String sqlPersonne = "UPDATE Personne SET nom = ?, nomUtilisateur = ?, telephone = ?, mail = ?, motDePasse = ?, role = ? WHERE idPersonne = ?";
        jdbcTemplate.update(sqlPersonne, utilisateur.getNom(), utilisateur.getNomUtilisateur(), utilisateur.getTelephone(), utilisateur.getMail(), utilisateur.getMotDePasse(), utilisateur.getRole(), utilisateur.getIdPersonne());
    }

    // Méthode pour supprimer un utilisateur
    public void deleteById(int idPersonne) {
        // Supprimer de la table Utilisateur
        String sqlUtilisateur = "DELETE FROM Utilisateur WHERE idPersonne = ?";
        jdbcTemplate.update(sqlUtilisateur, idPersonne);

        // Supprimer de la table Personne
        String sqlPersonne = "DELETE FROM Personne WHERE idPersonne = ?";
        jdbcTemplate.update(sqlPersonne, idPersonne);
    }

    // Méthode pour trouver un utilisateur par son ID
    public Utilisateur findById(int idPersonne) {
        String sql = "SELECT p.idPersonne, p.nom, p.nomUtilisateur, p.telephone, p.mail, p.motDePasse, p.role " +
                     "FROM Personne p " +
                     "JOIN Utilisateur u ON p.idPersonne = u.idPersonne " +
                     "WHERE p.idPersonne = ?";
        return jdbcTemplate.queryForObject(sql, new UtilisateurRowMapper(), idPersonne);
    }

    public int getIdUtilisateur(String username) {
        String sql = "SELECT idPersonne FROM Personne WHERE nomUtilisateur = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, username);
    }
    
}
