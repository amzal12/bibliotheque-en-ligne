package com.example.bibliotheque.services;

import com.example.bibliotheque.models.Utilisateur;
import com.example.bibliotheque.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String nomUtilisateur) throws UsernameNotFoundException {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByNomUtilisateur(nomUtilisateur);
        if (utilisateur.isPresent()) {
            Utilisateur user = utilisateur.get();
            return User.builder()
                    .username(user.getNomUtilisateur())
                    .password(user.getMotDePasse())
                    .roles(user.getRole())
                    .build();
        } else {
            throw new UsernameNotFoundException("Utilisateur non trouvé : " + nomUtilisateur);
        }
    }

    // Méthode pour ajouter un utilisateur
    public void addUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }

    // Méthode pour mettre à jour un utilisateur
    public void updateUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.update(utilisateur);
    }

    // Méthode pour supprimer un utilisateur
    public void deleteUtilisateur(int idPersonne) {
        utilisateurRepository.deleteById(idPersonne);
    }

    // Méthode pour trouver un utilisateur par son ID
    public Utilisateur getUtilisateurById(int idPersonne) {
        return utilisateurRepository.findById(idPersonne);
    }
}