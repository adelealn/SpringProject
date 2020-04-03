package com.aston.springproject.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aston.springproject.models.Film;

public interface FilmRepository extends MongoRepository<Film, String>{

	public Iterable<Film> findByTitre(String titre);


}
