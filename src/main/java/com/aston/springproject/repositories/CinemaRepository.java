package com.aston.springproject.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aston.springproject.dto.CinemaDTO;
import com.aston.springproject.models.Cinema;

public interface CinemaRepository extends MongoRepository<Cinema, String>{

	public void save(CinemaDTO cdto);

}
