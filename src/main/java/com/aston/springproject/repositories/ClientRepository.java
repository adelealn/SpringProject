package com.aston.springproject.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aston.springproject.models.Client;


public interface ClientRepository extends MongoRepository<Client, String>{

}
