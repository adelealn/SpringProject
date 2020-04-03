package com.aston.springproject.services;

import java.util.List;
import java.util.Optional;

import com.aston.springproject.models.Film;

public interface FilmService {

	public Film save(Film f);

	public List<Film> findAll();

	public Optional<Film> findById(String fid);

	public void delete(String fid);

	public Film update(Film f);

	public float getFilmRecette(String fid);
	
}
