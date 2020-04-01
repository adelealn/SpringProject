package com.aston.springproject.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aston.springproject.models.Salle;

public interface SalleRepository  extends MongoRepository<Salle, String>{

}
