package com.example.demo.repositories;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.LigneCommande;

public interface LigneCommandeRepository extends CrudRepository<LigneCommande, Long> {
}
