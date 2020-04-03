package com.aston.springproject.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aston.springproject.models.Assister;
import com.aston.springproject.models.Client;
import com.aston.springproject.models.Film;
import com.aston.springproject.models.Seance;
import com.aston.springproject.repositories.ClientRepository;
import com.aston.springproject.repositories.FilmRepository;
import com.aston.springproject.repositories.SeanceRepository;
import com.aston.springproject.services.AssisterService;
import com.aston.springproject.services.SeanceService;

@Service
public class SeanceServiceImpl implements SeanceService{
	
	@Autowired private SeanceRepository repo;
	@Autowired private ClientRepository clientrepo;
	@Autowired private FilmRepository filmrepo;
	@Autowired private SeanceService seanceService; 
	@Autowired private AssisterService assisterService;

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

	@Override
	public void ajouterClient(String sid, String cid) {
		Optional<Seance> optS = this.seanceService.findById(sid);
		if (optS.isPresent()) {
			Seance s = optS.get();
			Optional<Client> optC = this.clientrepo.findById(cid);
			if (optC.isPresent()) {
				Assister assister = assisterService.ajouterAssister(optC.get(), s.getType());
				s.getClients().add(assister);
				this.save(s);
			}
			else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "le client d'id: "+cid+" n'existe pas");
			}
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La séance d'id: "+sid+" n'existe pas");
		}
	}

	@Override
	public float getSeanceRecette(String sid) {
		Optional<Seance> optS = this.seanceService.findById(sid);
		float recetteSeance = 0F;
		if (optS.isPresent()) {
			Seance s = optS.get();
			List<Assister> clients = s.getClients();
			
			for (Assister a : clients) {
				if (a != null) {
					recetteSeance += a.getPrix();
				}
			}
			return recetteSeance;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La séance d'id: "+sid+" n'existe pas");
		}
	}

	@Override
	public int getPlacesRestantes(String sid) {
		Optional<Seance> optS = this.seanceService.findById(sid);
		if (optS.isPresent()) {
			Seance s = optS.get();
			List<Assister> clients = s.getClients();
			if (s.getSalle() != null) {
				int placesalle =s.getSalle().getPlace();
				if (clients != null) {
					int placesutilisees = clients.size();
					return placesalle-placesutilisees;
				}
				else {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun client n'assiste à la séance d'id: "+sid);
				}
			}
			else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La séance d'id: "+sid+" n'est diffusée dans aucune salle");
			}
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La séance d'id: "+sid+" n'existe pas");
		}
	}

	@Override
	public List<Seance> findAllByFilmTitre(String titre) {
		Iterable<Film> films = this.filmrepo.findByTitre(titre);
		return this.repo.findAllByFilmIn(films);
	}

	@Override
	public List<Seance> findSeanceBetweenMinMax(LocalDateTime min, LocalDateTime max) {
		return this.repo.findSeanceByDateBetween(min,max);
	}

	

}
