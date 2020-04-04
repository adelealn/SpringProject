package com.aston.springproject.repositories.custom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.aston.springproject.dto.CritereDTO;
import com.aston.springproject.models.Seance;
import com.aston.springproject.repositories.custom.SeanceRepositoryCustom;

public class SeanceRepositoryCustomImpl implements SeanceRepositoryCustom{
	@Autowired MongoTemplate template;
	
	@Override
	public List<Seance> findCustom(CritereDTO critere){
		Query query = new Query();
		if (critere.getAgeLimite() != 0) {
			query.addCriteria((Criteria.where("ageLimite")).is(critere.getAgeLimite()));
		}
		if (critere.getFilmGenre() != null) {
			query.addCriteria((Criteria.where("genre")).is(critere.getFilmGenre()));
		}
		if (critere.getSeanceType() != null) {
			query.addCriteria((Criteria.where("type")).is(critere.getSeanceType()));
		}
		if (critere.getHoraireMin() != null && critere.getHoraireMax() != null) {
			query.addCriteria((Criteria.where("date")).gt(critere.getHoraireMin()).and("date").lt(critere.getHoraireMax()));
		}
		
		return this.template.find(query, Seance.class);
	}
	

}
