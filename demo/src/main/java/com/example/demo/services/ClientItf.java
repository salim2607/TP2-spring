package com.example.demo.services;

import com.example.demo.entity.Client;

public interface ClientItf {

    Client findByEmail(String email);

    Client findByEmailAndPassword(String email,String password);

    void create(String email, String password, String nom, String prenom );
    
}
