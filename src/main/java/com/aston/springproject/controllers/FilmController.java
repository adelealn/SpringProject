package com.aston.springproject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public Film save(@RequestBody Film entity) {
		return this.service.save(entity);
	}
	
	//Modifier film
	@PutMapping("")
	public Film update(@RequestBody Film f){
		return this.service.update(f);
	}
	
	//Récupérer tous les films de la bdd
	@GetMapping("")
	public List<Film> findAll() {
		return this.service.findAll();
	}
	
	//Récupérer un film dans la bdd grace à son id
	@GetMapping("{fid}")
	public Optional<Film> findById(@PathVariable String fid) {
		return this.service.findById(fid);
	}
	
	//Savoir combien a rapporté un film
	@GetMapping("{fid}/recette")
	public float getFilmRecette(@ PathVariable String fid) {
		return this.service.getFilmRecette(fid);
	}
	
	
	
	//Supprimer un film
	@DeleteMapping("")
	public void delete(@RequestBody Film f) {
		this.delete(f.getId());
	}
	
	//Supprimer un Film ayant un id particulier
	@DeleteMapping("{id}")
	public void delete(@PathVariable String id) {
		this.service.delete(id);
	}
}
