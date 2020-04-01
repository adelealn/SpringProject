package com.aston.springproject.services;

import java.util.List;
import java.util.Optional;

import com.aston.springproject.models.Client;

public interface ClientService {

	public Client save(Client entity);

	public List<Client> findAll();

	public Optional<Client> findById(String id);

	public void delete(String id);

	public Client update(Client c);

}
