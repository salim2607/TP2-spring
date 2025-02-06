package com.example.demo.magasin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Inscription d'un client
    public void register(String email, String password, String firstName, String lastName) {
        // Vérifier si l'email existe déjà dans la base de données
        if (clientRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Cet email est déjà utilisé.");
        }
        // Créer un nouveau client
        Client client = new Client(email, password, firstName, lastName);
        // Sauvegarder le client dans la base de données
        clientRepository.save(client);
    }

    // Authentification d'un client
    public Optional<Client> authenticate(String email, String password) {
        // Trouver le client par email
        Optional<Client> clientOpt = clientRepository.findByEmail(email);
        
        // Vérifier si le client existe et si le mot de passe est correct
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            if (client.getPassword().equals(password)) {
                return Optional.of(client);  // Authentification réussie
            } else {
                throw new RuntimeException("Mot de passe incorrect.");
            }
        }
        throw new RuntimeException("Aucun client trouvé avec cet email.");
    }
}
