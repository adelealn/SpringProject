package com.aston.springproject.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aston.springproject.models.Film;
import com.aston.springproject.models.Seance;

public interface SeanceRepository extends MongoRepository<Seance, String>{
	
	public List<Seance> findAllByFilmIn(Iterable<Film> films);

	public List<Seance> findSeanceByDateBetween(LocalDateTime min, LocalDateTime max);
	

}
