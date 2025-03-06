package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Client;
import com.example.demo.repositories.ClientRepository;

@Service
public class ClientService implements ClientItf {

    @Autowired
    private ClientRepository repo;

    @Override
    public Client findByEmail(String email) {
          return repo.findByEmail(email);
    }

    @Override
    public void create(String email, String password, String nom, String prenom) {
        var client = new Client(email, password, nom, prenom);
        repo.save(client);
    }

    @Override
    public Client findByEmailAndPassword(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }
    
}
