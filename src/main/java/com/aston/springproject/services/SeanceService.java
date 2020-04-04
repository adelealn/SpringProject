package com.aston.springproject.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;

import com.aston.springproject.dto.CritereDTO;
import com.aston.springproject.models.Seance;

public interface SeanceService {

	public Seance save(Seance entity);

	public List<Seance> findAll();

	public Optional<Seance> findById(String id);

	public void deleteById(String id);

	public Seance update(Seance s);

	public void ajouterClient(String sid, String cid);
	
	public float getSeanceRecette(String sid);

	public int getPlacesRestantes(String sid);

	public List<Seance> findAllByFilmTitre(String titre);
	
	public List<Seance> findSeanceBetweenMinMax(LocalDateTime min, LocalDateTime max);
	
	public List<Seance> findSeanceAvecCriteres(@RequestBody CritereDTO critere);

}
