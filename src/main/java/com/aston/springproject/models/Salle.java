package com.aston.springproject.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salle {
	
	@Id
	private String id;
	@DBRef
	private Cinema cinema;
	private String nom;
	private int place;
}
