package com.aston.springproject.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aston.springproject.models.Assister;
import com.aston.springproject.models.Film;
import com.aston.springproject.models.Seance;
import com.aston.springproject.repositories.FilmRepository;
import com.aston.springproject.repositories.SeanceRepository;
import com.aston.springproject.services.FilmService;
import com.aston.springproject.services.SeanceService;

@Service
public class FilmServiceImpl implements FilmService{
	
	@Autowired private FilmRepository repo;
	@Autowired private SeanceService seanceservice;
	
	@Override
	public Film save(Film f) {
		return this.repo.save(f);
	}
	
	@Override
	public Film update(Film f) {
		return this.repo.save(f);
	}
	
	@Override
	public List<Film> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Optional<Film> findById(String fid) {
		Optional<Film> optF = this.repo.findById(fid);
		if (optF.isPresent()) {
			return optF;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le film d'id: "+fid+" n'existe pas");
		}
	}

	@Override
	public void delete(String fid) {
		if (this.repo.existsById(fid)){
			this.repo.deleteById(fid);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le film d'id: "+fid+" que vous souhaitez supprimer n'existe pas");
		}
	}

	@Override
	public float getFilmRecette(String fid) {
		Optional<Film> optF = this.repo.findById(fid);
		
		float recetteFilm = 0F;
		if (optF.isPresent()) {
			Film f = optF.get();
			List<Seance> seances = this.seanceservice.findAllByFilmTitre(f.getTitre());
			for (Seance s : seances) {
				recetteFilm += this.seanceservice.getSeanceRecette(s.getId());
			}
			return recetteFilm;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le film d'id: "+fid+" n'existe pas");
		}
	}

}
