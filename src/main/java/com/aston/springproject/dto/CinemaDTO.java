package com.aston.springproject.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.aston.springproject.models.Cinema;
import com.aston.springproject.models.Salle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinemaDTO {
	
	@DBRef
	private Cinema cinema;
	@DBRef
	private List<Salle> salles = new ArrayList<>();

}
