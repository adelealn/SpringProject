package com.aston.springproject.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aston.springproject.models.Client;
import com.aston.springproject.repositories.ClientRepository;
import com.aston.springproject.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired ClientRepository repo;

	@Override
	public Client save(Client c) {
		return this.repo.save(c);
	}
	
	@Override
	public Client update(Client c) {
		return this.repo.save(c);
	}

	@Override
	public List<Client> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Optional<Client> findById(String cid) {
		Optional<Client> optC = this.repo.findById(cid);
		if (optC.isPresent()) {
			return optC;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le client d'id: "+cid+" n'existe pas");
		}
	}

	@Override
	public void delete(String cid) {
		if (this.repo.existsById(cid)){
			this.repo.deleteById(cid);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le client d'id: "+cid+" que vous souhaitez supprimer n'existe pas");
		}
		
	}

	

}
