package com.example.demo.magasin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;
    
    @Autowired
    private ClientRepository clientRepository;
    
    // Crée une commande pour le client spécifié par son email.
    @PostMapping("/create")
    public ResponseEntity<?> createCommande(@RequestParam String email) {
        Optional<Client> clientOpt = clientRepository.findByEmail(email);
        if (clientOpt.isPresent()) {
            Commande commande = commandeService.createCommande(clientOpt.get());
            return ResponseEntity.ok(commande);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client non trouvé");
    }
    
    
    // Retourne la liste des commandes d'un client spécifié par son email.
    @GetMapping("/client/{email}")
    public ResponseEntity<List<Commande>> getCommandesByClient(@PathVariable String email) {
        Optional<Client> clientOpt = clientRepository.findByEmail(email);
        if (clientOpt.isPresent()) {
            List<Commande> commandes = commandeService.getCommandesByClient(clientOpt.get());
            return ResponseEntity.ok(commandes);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
