package com.example.bibliotheque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.bibliotheque.services.*;

import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private EmpruntService empruntService;

    @GetMapping("/admin/panel")
    public String showAdminPanel(Model model) {
        List<Map<String, Object>> emprunts = empruntService.getAllUserAndDocEmprenter();
        model.addAttribute("emprunts", emprunts);
        return "admin-panel";
    }
}
