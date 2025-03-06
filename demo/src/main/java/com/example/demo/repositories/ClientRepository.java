package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Client;

public interface  ClientRepository extends CrudRepository<Client, String>  {
    
    public Client findByEmail(String email);
    public Client findByEmailAndPassword(String email,String password);
}