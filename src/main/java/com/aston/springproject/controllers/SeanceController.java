package com.aston.springproject.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.aston.springproject.dto.CritereDTO;
import com.aston.springproject.models.Seance;
import com.aston.springproject.services.SeanceService;

@RestController
@CrossOrigin
@RequestMapping("seances")
public class SeanceController {
	
	@Autowired
	private SeanceService service;

	//Ajouter une séance à la bdd
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Seance save(@RequestBody Seance entity) {
		return this.service.save(entity);
	}
	
	//Ajouter un client à une séance
	@PostMapping("{sid}/assister/{cid}")
	@ResponseStatus(HttpStatus.CREATED)
	public void ajouterClient(@PathVariable String sid,@PathVariable String cid ) {
		this.service.ajouterClient(sid, cid);
	}
	
	//Modifier Séance
	@PutMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Seance update(@RequestBody Seance s){
		return this.service.update(s);
	}
	
	//Récupérer toutes les séances de la bdd
	@GetMapping("")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Seance> findAll() {
		return this.service.findAll();
	}
	
	//Récupérer une séance dans la bdd grace à son id
	@GetMapping("{sid}")
	@ResponseStatus(value = HttpStatus.OK)
	public Optional<Seance> findById(@PathVariable String sid) {
		return this.service.findById(sid);
	}
	
	//Recette d'une séance
	@GetMapping("{sid}/recette")
	@ResponseStatus(value = HttpStatus.OK)
	public float getSeanceRecette(@PathVariable String sid) {
		return this.service.getSeanceRecette(sid);
	}
	
	//Récupérer places restantes à une séance
	@GetMapping("{sid}/places")
	@ResponseStatus(value = HttpStatus.OK)
	public int getPlacesRestantes(@PathVariable String sid) {
		return this.service.getPlacesRestantes(sid);
	}
	
	// Trouver séance en fonction d'un titre de film
	@GetMapping("film/{titre}")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Seance> findAllByFilmTitre(@PathVariable String titre) {
		return this.service.findAllByFilmTitre(titre);
	}
	
	// Trouver les séances entre deux plages horaire
	@GetMapping("horaire/{min}/{max}")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Seance> findSeanceBetweenMinMax(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime min, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime max) {
		return this.service.findSeanceBetweenMinMax(min,max);
	}
	
	//Rechercher seances selon criteres
	@GetMapping("/recherche")
	public List<Seance> findSeanceAvecCriteres(@RequestBody CritereDTO critere){
		return this.service.findSeanceAvecCriteres(critere);
	}
	
	//Supprimer une séance
	@DeleteMapping("")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete(@RequestBody Seance s) {
		this.deleteById(s.getId());
	}
	
	//Supprimer une Séance ayant un id particulier
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteById(@PathVariable String id) {
		this.service.deleteById(id);
	}
}
