package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class LigneCommande {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "article_nom", referencedColumnName = "nomArticle")
    private Article article;

    private int NbArticle;

    @ManyToOne
    private Commande commande;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getNbArticle() {
        return NbArticle;
    }

    public void setNbArticle(int nbArticle) {
        NbArticle = nbArticle;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    
}
