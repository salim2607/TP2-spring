package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Client;
import com.example.demo.entity.Commande;
import com.example.demo.entity.LigneCommande;
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
    public RedirectView createCommand(@RequestParam String nom, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null || nom == null || nom.trim().isEmpty()) {
            return new RedirectView("/store/home?error=invalidRequest");
        }
        
        Client client = clientService.findByEmail(email);
        if (client == null) {
            return new RedirectView("/store/home?error=clientNotFound");
        }
        
        service.create(nom, client);
        return new RedirectView("/store/client");
    }

    @PostMapping("/addArticle")
    public RedirectView addArticle(@RequestParam Long commandeId, @RequestParam String articleNom,
                                   @RequestParam int quantity, @RequestParam double price, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null || quantity <= 0 || price <= 0) {
            return new RedirectView("/store/home?error=invalidInput");
        }
        
        Optional<Commande> optionalCommande = service.findById(commandeId);
        if (optionalCommande.isEmpty() || !optionalCommande.get().getClient().getEmail().equals(email)) {
            return new RedirectView("/store/home?error=unauthorized");
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
        
        Optional<Commande> optionalCommande = service.findById(commandeId);
        if (optionalCommande.isEmpty() || !optionalCommande.get().getClient().getEmail().equals(email)) {
            return new RedirectView("/store/home?error=unauthorized");
        }

        service.removeArticleFromCommande(commandeId, articleNom);
        return new RedirectView("/store/client/commande/" + commandeId);
    }

    @GetMapping("/commande/{id}")
    public ModelAndView commandeDetails(@PathVariable Long id, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return new ModelAndView("redirect:/store/home");
        }
        
        Optional<Commande> optionalCommande = service.findById(id);
        if (optionalCommande.isEmpty() || !optionalCommande.get().getClient().getEmail().equals(email)) {
            return new ModelAndView("redirect:/store/home?error=unauthorized");
        }
        
        Commande commande = optionalCommande.get();
        List<LigneCommande> articles = commande.getLigneCommande();
        double total = calculateTotal(articles);
        
        return new ModelAndView("/store/commande")
                .addObject("commande", commande)
                .addObject("articles", articles)
                .addObject("total", total);
    }

    @GetMapping("/commande/print/{id}")
    public ModelAndView printCommande(@PathVariable Long id, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return new ModelAndView("redirect:/store/home");
        }
        
        Optional<Commande> optionalCommande = service.findById(id);
        if (optionalCommande.isEmpty() || !optionalCommande.get().getClient().getEmail().equals(email)) {
            return new ModelAndView("redirect:/store/home?error=unauthorized");
        }
        
        Commande commande = optionalCommande.get();
        List<LigneCommande> articles = commande.getLigneCommande();
        double total = calculateTotal(articles);
        
        return new ModelAndView("/store/print")
                .addObject("commande", commande)
                .addObject("articles", articles)
                .addObject("total", total);
    }

    private double calculateTotal(List<LigneCommande> ligneCommandes) {
        return ligneCommandes.stream()
                .mapToDouble(lc -> lc.getNbArticle() * lc.getArticle().getPrixUnitaire())
                .sum();
    }
}