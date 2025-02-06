package com.example.demo.magasin;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Interface pour l'accès aux données des clients.

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
}
