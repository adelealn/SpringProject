package com.aston.springproject.dto;

import java.util.List;

import com.aston.springproject.models.Cinema;
import com.aston.springproject.models.Salle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinemaDTO {
	
	private Cinema cinema;
	private List<Salle> salles;

}
