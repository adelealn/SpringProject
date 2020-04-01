package com.aston.springproject.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aston.springproject.models.Seance;
import com.aston.springproject.repositories.SeanceRepository;
import com.aston.springproject.services.SeanceService;

@Service
public class SeanceServiceImpl implements SeanceService{
	
	@Autowired private SeanceRepository repo;

	@Override
	public Seance save(Seance s) {
		return this.repo.save(s);
	}
	
	@Override
	public Seance update(Seance s) {
		return this.repo.save(s);
	}
	
	@Override
	public List<Seance> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Optional<Seance> findById(String sid) {
		Optional<Seance> optS = this.repo.findById(sid);
		if (optS.isPresent()) {
			return optS;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La séance d'id: "+sid+" n'existe pas");
		}
	}

	@Override
	public void delete(String sid) {
		if (this.repo.existsById(sid)){
			this.repo.deleteById(sid);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La séance d'id: "+sid+" que vous souhaitez supprimer n'existe pas");
		}
	}

	

}
