package com.aston.springproject.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aston.springproject.dto.CinemaDTO;
import com.aston.springproject.exceptions.NotFoundException;
import com.aston.springproject.models.Salle;
import com.aston.springproject.repositories.CinemaRepository;
import com.aston.springproject.repositories.SalleRepository;
import com.aston.springproject.services.CinemaService;

@Service
public class CinemaServiceImpl implements CinemaService{
	
	@Autowired private CinemaRepository cinerepo;
	@Autowired private SalleRepository sallerepo;
	
	@Override
	public void save(CinemaDTO cdto) {
		this.cinerepo.save(cdto.getCinema());
		if (cdto.getSalles() != null) {
			for (Salle s : cdto.getSalles()) {
				s.setCinema(cdto.getCinema());
				this.sallerepo.save(s);
			}	
		}
		else {
			throw new NotFoundException("Aucune salle n'a été ajoutée");
		}
		
	}
}
