package com.aston.springproject.services.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aston.springproject.exceptions.NotFoundException;
import com.aston.springproject.models.Assister;
import com.aston.springproject.models.Client;
import com.aston.springproject.repositories.AssisterRepository;
import com.aston.springproject.services.AssisterService;

@Service
public class AssisterServiceImpl implements AssisterService{
	
	@Autowired AssisterRepository repo;

	@Override
	public Assister ajouterAssister(Client client, String seanceType) {
		Assister assister = new Assister();
		assister.setClient(client);
		
		//Calcul du prix du ticket en fonction du type de séance seanceType
		float prix = 10;
		if (seanceType.equals("3D")) {
			prix += 3;
		}
		else if (seanceType.equals("IMAX")) {
			prix += 6;
		}
		else if (seanceType.equals("4DX")) {
			prix += 8;
		}
		//Réduction du prix si le client a moins de 10 ans
		if (client.getNaissance() != null) {
			int age = LocalDate.now().getYear()-client.getNaissance().getYear();
		
			if (age<10){
				prix -= 4;
			}
		} 
		else {
			throw new NotFoundException("La date de naissance du client n'a pas été fournie");
		}
		// Réduction du prix si le client est étudiant
		if (client.getEtudiant() != null) {
			if (client.getEtudiant()) {
				prix -= 2;
			}
		}
		else {
			throw new NotFoundException("Le statut d'étudiant du client n'a pas été fourni");
		}
		assister.setPrix(prix);
		return this.repo.save(assister);
	}	
}
