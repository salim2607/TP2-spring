package com.example.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Client;
import com.example.demo.entity.Commande;
import com.example.demo.services.ClientItf;
import com.example.demo.services.CommandeItf;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/store/client")
public class CommandeController {
    
    @Autowired
    private CommandeItf service;
    
    @Autowired
    private ClientItf clientService;

    @PostMapping("/createCommand")
    public RedirectView create(@RequestParam String nom, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return new RedirectView("/store/home");
        }
        Client client = clientService.findByEmail(email);
        service.create(nom, client);
        return new RedirectView("/store/client");
    }

    @PostMapping("/addArticle")
    public RedirectView addArticle(@RequestParam Long commandeId, @RequestParam String articleNom, @RequestParam int quantity, @RequestParam double price, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return new RedirectView("/store/home");
        }
        Commande commande = service.findById(commandeId).orElseThrow();
        if (!commande.getClient().getEmail().equals(email)) {
            return new RedirectView("/store/home"); // Redirection si l'utilisateur n'est pas le propriétaire
        }
        service.addArticleToCommande(commandeId, articleNom, quantity, price);
        return new RedirectView("/store/client/commande/" + commandeId);
    }

    @PostMapping("/removeArticle")
    public RedirectView removeArticle(@RequestParam Long commandeId, @RequestParam String articleNom, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return new RedirectView("/store/home");
        }
        Commande commande = service.findById(commandeId).orElseThrow();
        if (!commande.getClient().getEmail().equals(email)) {
            return new RedirectView("/store/home"); // Redirection si l'utilisateur n'est pas le propriétaire
        }
        service.removeArticleFromCommande(commandeId, articleNom);
        return new RedirectView("/store/client/commande/" + commandeId);
    }

    @GetMapping("/commande/{id}")
    public ModelAndView commandePage(@PathVariable Long id, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return new ModelAndView("redirect:/store/home");
        }
        Commande commande = service.findById(id).orElseThrow();
        if (!commande.getClient().getEmail().equals(email)) {
            return new ModelAndView("redirect:/store/home"); // Redirection si l'utilisateur n'est pas le propriétaire
        }
        var model = Map.of(
            "commande", commande,
            "articles", commande.getLigneCommande()
        );
        return new ModelAndView("/store/commande", model);
    }
}
