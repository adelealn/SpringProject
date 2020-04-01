package com.aston.springproject.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aston.springproject.models.Seance;

public interface SeanceRepository extends MongoRepository<Seance, String>{

}
