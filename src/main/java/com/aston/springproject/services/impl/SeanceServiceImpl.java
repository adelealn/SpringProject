package com.aston.springproject.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aston.springproject.dto.CritereDTO;
import com.aston.springproject.exceptions.ForbiddenException;
import com.aston.springproject.exceptions.NotFoundException;
import com.aston.springproject.models.Assister;
import com.aston.springproject.models.Client;
import com.aston.springproject.models.Film;
import com.aston.springproject.models.Seance;
import com.aston.springproject.repositories.ClientRepository;
import com.aston.springproject.repositories.FilmRepository;
import com.aston.springproject.repositories.SeanceRepository;
import com.aston.springproject.repositories.custom.SeanceRepositoryCustom;
import com.aston.springproject.services.AssisterService;
import com.aston.springproject.services.SeanceService;

@Service
public class SeanceServiceImpl implements SeanceService{
	
	@Autowired private SeanceRepository repo;
	@Autowired private ClientRepository clientrepo;
	@Autowired private FilmRepository filmrepo;
	@Autowired private SeanceService seanceService; 
	@Autowired private AssisterService assisterService;
	@Autowired private SeanceRepositoryCustom seancecustomrepo;

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
			throw new NotFoundException(sid, optS.getClass().getSimpleName());
		}
	}

	@Override
	public void deleteById(String sid) {
		if (this.repo.existsById(sid)){
			this.repo.deleteById(sid);
		}
		else {
			throw new NotFoundException("La séance que vous souhaitez supprimer n'existe pas");
		}
	}
	
	//Ajout d'un client d'id cid à la séance sid
	@Override
	public void ajouterClient(String sid, String cid) {
		// On récupère la séance d'id sid
		Optional<Seance> optS = this.seanceService.findById(sid);
		Seance s = optS.get();
		//On récupère le client d'id cid
		Optional<Client> optC = this.clientrepo.findById(cid);
		//On récupère le nombre de places restantes d'une séance
		int placesrestantes = this.seanceService.getPlacesRestantes(s.getId());
		if (placesrestantes > 0) {
			Client client = optC.get();
			// On regarde si la date de naissance du client est informée
			if (client.getNaissance() != null) {
				int ageClient = LocalDate.now().getYear()-client.getNaissance().getYear();
				int ageLimiteSeance = s.getFilm().getAgeLimite();
				if (ageClient > ageLimiteSeance) {
					Assister assister = assisterService.ajouterAssister(client, s.getType());
					s.getClients().add(assister);
					this.save(s);
				}
				else {
					throw new ForbiddenException("client",cid,"séance",sid,"le client n'a pas l'age requis pour ce film" );
				}
			}
			else {
				throw new NotFoundException("La date de naissance du client n'est pas informée");
			}
		}
		else {
			throw new ForbiddenException("client",cid,"séance",sid,"la séance n'a plus de place" );
		}
	}

	@Override
	public float getSeanceRecette(String sid) {
		Optional<Seance> optS = this.seanceService.findById(sid);
		float recetteSeance = 0F;
		Seance s = optS.get();
		List<Assister> clients = s.getClients();
		for (Assister a : clients) {
			if (a != null) {
				recetteSeance += a.getPrix();
			}
		}
		return recetteSeance;
	}

	//Récupérer le nombre de places restantes d'un séance d'id sid
	@Override
	public int getPlacesRestantes(String sid) {
		// Récupération de la séance en fonction de son id
		Optional<Seance> optS = this.seanceService.findById(sid);
		Seance s = optS.get();
		List<Assister> clients = s.getClients();
		if (s.getSalle() != null) {
			int placesalle =s.getSalle().getPlace();
			if (clients != null) {
				int placesutilisees = clients.size();
				return placesalle-placesutilisees;
			}
			// Si la séance n'a aucun client qui y assistent, renvoie une exception
			else {
				throw new NotFoundException("Aucun client n'assiste à la séance");
			}
		}
		// Si la séance n'est diffusée dans aucune salle, renvoie une exception
		else {
			throw new NotFoundException("La séance n'est diffusée dans aucune salle");
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

	@Override
	public List<Seance> findSeanceAvecCriteres(CritereDTO critere) {
		// TODO Auto-generated method stub
		return this.seancecustomrepo.findCustom(critere);
	}

	

}
