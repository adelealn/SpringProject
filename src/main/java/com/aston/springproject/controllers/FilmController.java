package com.aston.springproject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aston.springproject.models.Film;
import com.aston.springproject.services.FilmService;

@RestController
@CrossOrigin
@RequestMapping("films")
public class FilmController {
	
	@Autowired
	private FilmService service;
	
	//Ajouter un film à la bdd
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Film save(@RequestBody Film entity) {
		return this.service.save(entity);
	}
	
	//Modifier film
	@PutMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Film update(@RequestBody Film f){
		return this.service.update(f);
	}
	
	//Récupérer tous les films de la bdd
	@GetMapping("")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Film> findAll() {
		return this.service.findAll();
	}
	
	//Récupérer un film dans la bdd grace à son id
	@GetMapping("{fid}")
	@ResponseStatus(value = HttpStatus.OK)
	public Optional<Film> findById(@PathVariable String fid) {
		return this.service.findById(fid);
	}
	
	//Savoir combien a rapporté un film
	@GetMapping("{fid}/recette")
	@ResponseStatus(value = HttpStatus.OK)
	public float getFilmRecette(@ PathVariable String fid) {
		return this.service.getFilmRecette(fid);
	}
	
	//Supprimer un film
	@DeleteMapping("")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete(@RequestBody Film f) {
		if (f.getId() != null) {
			this.deleteById(f.getId());
		}
	}
	
	//Supprimer un Film ayant un id particulier
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteById(@PathVariable String id) {
		this.service.deleteById(id);
	}
}
