package com.aston.springproject.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aston.springproject.models.Assister;

public interface AssisterRepository extends MongoRepository<Assister, String>{

}
