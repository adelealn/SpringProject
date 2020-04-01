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
	public Seance save(@RequestBody Seance entity) {
		return this.service.save(entity);
	}
	
	//Ajouter un client à une séance
	@PostMapping("{sid}/assister/{cid}")
	public void ajouterClient(@PathVariable String sid,@PathVariable String cid ) {
		this.service.ajouterClient(sid, cid);
	}
	
	//Modifier Séance
	@PutMapping("")
	public Seance update(@RequestBody Seance s){
		return this.service.update(s);
	}
	
	//Récupérer toutes les séances de la bdd
	@GetMapping("")
	public List<Seance> findAll() {
		return this.service.findAll();
	}
	
	//Récupérer une séance dans la bdd grace à son id
	@GetMapping("{id}")
	public Optional<Seance> findById(@PathVariable String id) {
		return this.service.findById(id);
	}
	
	//Supprimer une séance
	@DeleteMapping("")
	public void delete(@RequestBody Seance s) {
		this.delete(s.getId());
	}
	
	//Supprimer une Séance ayant un id particulier
	@DeleteMapping("{id}")
	public void delete(@PathVariable String id) {
		this.service.delete(id);
	}
}
