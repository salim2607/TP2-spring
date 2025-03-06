package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Commande {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;

    @ManyToOne
    private Client client;

    @OneToMany
    private List<LigneCommande> ligneCommande;

    public Commande() {}

    public Commande(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public List<LigneCommande> getLigneCommande() {
        return ligneCommande;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setLigneCommande(List<LigneCommande> ligneCommande) {
        this.ligneCommande = ligneCommande;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
