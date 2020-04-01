package com.aston.springproject.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seance {
	
	@Id
	private String id;
	private Film film;
	private LocalDateTime date;
	private List<Assister> clients;
	private Salle salle;
	private String type;

}
