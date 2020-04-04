package com.aston.springproject.repositories.custom;

import java.util.List;

import com.aston.springproject.dto.CritereDTO;
import com.aston.springproject.models.Seance;

public interface SeanceRepositoryCustom {

	public List<Seance> findCustom(CritereDTO critere);

}
