package com.example.demo.magasin;

import java.util.Optional;

public interface ClientItf {
   
    Client register(Client client);
    Optional<Client> login(String email, String password);
    void init();
}
