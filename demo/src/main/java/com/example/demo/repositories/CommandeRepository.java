package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Client;
import com.example.demo.entity.Commande;

public interface  CommandeRepository extends CrudRepository<Commande, Long>{

	public Iterable<Commande> findAllByClient(Client client);

}
