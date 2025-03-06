package com.example.demo.services;

import java.util.Optional;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.entity.Commande;
import com.example.demo.entity.LigneCommande;
import com.example.demo.repositories.ArticleRepository;
import com.example.demo.repositories.CommandeRepository;
import com.example.demo.repositories.LigneCommandeRepository;

@Service
public class CommandeService implements CommandeItf {
    
    @Autowired
    private CommandeRepository repo;

    @Autowired
    private ArticleRepository articleRepo;

    @Autowired
    private LigneCommandeRepository ligneCommandeRepo;

    @Override
    public Iterable<Commande> findAll() {
        return repo.findAll();
    }

    @Override
    public void create(String nom, Client client) {
        var commande = new Commande(nom);
        commande.setClient(client);
        client.addCommande(commande);
        repo.save(commande);
    }

    @Override
    public Iterable<Commande> findAllByClient(Client client) {
        return repo.findAllByClient(client);
    }

    @Override
    public void addArticleToCommande(Long commandeId, String articleNom, int quantity, double prix) {
        Optional<Commande> optionalCommande = repo.findById(commandeId);
        Optional<Article> optionalArticle = articleRepo.findById(articleNom);

        if (optionalCommande.isPresent()) {
            Commande commande = optionalCommande.get();
            Article article;
            
            if (optionalArticle.isPresent()) {
                article = optionalArticle.get();
            } else {
                article = new Article(articleNom, prix);
                articleRepo.save(article);
            }

            LigneCommande ligneCommande = new LigneCommande();
            ligneCommande.setArticle(article);
            ligneCommande.setNbArticle(quantity);
            ligneCommande.setCommande(commande);
            ligneCommandeRepo.save(ligneCommande); // Save the LigneCommande data
            commande.getLigneCommande().add(ligneCommande);
            repo.save(commande);
        } else {
            throw new NoSuchElementException("Commande not found");
        }
    }

    @Override
    public void removeArticleFromCommande(Long commandeId, String articleNom) {
        Commande commande = repo.findById(commandeId).orElseThrow();
        commande.getLigneCommande().removeIf(lc -> lc.getArticle().getNomArticle().equals(articleNom));
        repo.save(commande);
    }

    @Override
    public Optional<Commande> findById(Long id) {
        return repo.findById(id);
    }
}
