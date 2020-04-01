package com.aston.springproject.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client{
	
	@Id
	private String id;
	private String nom;
	private Boolean etudiant;
	private LocalDate naissance;
	
}
