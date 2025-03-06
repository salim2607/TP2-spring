	package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Client {

    @Id
    private String email;  
    private String password;
    private String nom;
    private String prenom;    
    
    @OneToMany
    private List<Commande> listeCommande;
                                    
    public Client() {}
                                
    public Client(String email, String password, String nom, String prenom ){                     
        this.email = email;                     
        this.password = password;                 
        this.nom = nom;
        this.prenom = prenom;
    }
    
    public void setMail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Commande> getListeCommande() {
        return listeCommande;
    }

    public void setListeCommande(List<Commande> listeCommande) {
        this.listeCommande = listeCommande;
    }
    
    public void addCommande(Commande commande) {
    	this.listeCommande.add(commande);
    }
    
}
