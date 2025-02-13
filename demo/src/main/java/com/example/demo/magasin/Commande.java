package com.example.demo.magasin;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nom de la commande
    private String nom;

    // Chaque commande est associée à un client (identifié par son id)
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // Une commande contient plusieurs articles
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<Article> articles;

    // Date de la commande
    private java.time.LocalDateTime dateCommande;

    // Statut de la commande (par exemple, en cours, expédiée)
    @Enumerated(EnumType.STRING)
    private StatutCommande statut;

    // Prix total de la commande
    private double prixTotal;

    public Commande() {}

    public Commande(String nom, Client client) {
        this.nom = nom;
        this.client = client;
        this.dateCommande = java.time.LocalDateTime.now();
        this.statut = StatutCommande.EN_COURS;  // Statut initial
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public java.time.LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(java.time.LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    public StatutCommande getStatut() {
        return statut;
    }

    public void setStatut(StatutCommande statut) {
        this.statut = statut;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }
}

// Enum pour les statuts de commande
enum StatutCommande {
    EN_COURS, EXPEDIEE, LIVREE
}
