package com.aston.springproject.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aston.springproject.exceptions.NotFoundException;
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
			throw new NotFoundException(cid, "Client");
		}
	}

	@Override
	public void deleteById(String cid) {
		if (this.repo.existsById(cid)){
			this.repo.deleteById(cid);
		}
		else {
			throw new NotFoundException("Le client que vous souhaitez supprimer n'existe pas");
		}
	}
}
