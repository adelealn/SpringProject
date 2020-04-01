package com.aston.springproject.services;

import java.util.List;
import java.util.Optional;

import com.aston.springproject.models.Seance;

public interface SeanceService {

	public Seance save(Seance entity);

	public List<Seance> findAll();

	public Optional<Seance> findById(String id);

	public void delete(String id);

	public Seance update(Seance s);

}
