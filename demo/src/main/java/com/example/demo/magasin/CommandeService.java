package com.example.demo.magasin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

// Service pour la gestion des commandes.
 
@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;
    
    // Crée une commande pour un client .
   
    public Commande createCommande(Client client) {
        Commande commande = new Commande(client);
        return commandeRepository.save(commande);
    }
    
    //   Retourne la liste des commandes d'un client.
   
    public List<Commande> getCommandesByClient(Client client) {
        return commandeRepository.findByClient(client);
    }
}
