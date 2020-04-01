package com.aston.springproject.services;

import com.aston.springproject.models.Assister;
import com.aston.springproject.models.Client;

public interface AssisterService {

	public Assister ajouterAssister(Client client, String seanceType);

}
