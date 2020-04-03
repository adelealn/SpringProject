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

import com.aston.springproject.models.Client;
import com.aston.springproject.services.ClientService;

@RestController
@CrossOrigin
@RequestMapping("clients")
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	//Ajouter un client à la bdd
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Client save(@RequestBody Client entity) {
		return this.service.save(entity);
	}
	
	//Modifier film
	@PutMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Client update(@RequestBody Client c){
		return this.service.update(c);
	}
	
	//Récupérer tous les clients de la bdd
	@GetMapping("")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Client> findAll() {
		return this.service.findAll();
	}
	
	//Récupérer un client dans la bdd grace à son id
	@GetMapping("{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Optional<Client> findById(@PathVariable String id) {
		return this.service.findById(id);
	}
	
	//Supprimer un client
	@DeleteMapping("")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete(@RequestBody Client c) {
		this.deleteById(c.getId());
	}
	
	//Supprimer un client ayant un id particulier
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteById(@PathVariable String id) {
		this.service.deleteById(id);
	}
}
