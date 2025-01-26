package com.example.bibliotheque.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String hashPassword(String password) {
        return passwordEncoder.encode(password); // Hache le mot de passe avec BCrypt
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword); // Vérifie si le mot de passe correspond à la version hachée
    }
}

