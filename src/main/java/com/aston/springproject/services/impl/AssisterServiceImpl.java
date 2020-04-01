package com.aston.springproject.services.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.aston.springproject.models.Assister;
import com.aston.springproject.models.Client;
import com.aston.springproject.services.AssisterService;

@Service
public class AssisterServiceImpl implements AssisterService{

	@Override
	public Assister ajouterAssister(Client client, String seanceType) {
		Assister assister = new Assister();
		assister.setClient(client);
		
		//Calcul du prix du ticket en fonction du type de séance seanceType, de l'age du client et de son statut d'étudiant
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
		//On récupère l'age du client
		Integer age = LocalDate.now().getYear()-client.getNaissance().getYear();
		
		if (age<10){
			prix -= 4;
		}
		if (client.getEtudiant()) {
			prix -= 2;
		}
		assister.setPrix(prix);
		return assister;
	}	
}
