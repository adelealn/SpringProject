package com.aston.springproject.models;

import java.time.LocalDateTime;
import java.util.List;

public class Seance {
	
	private Film film;
	private LocalDateTime date;
	private List<Assister> clients;
	private Salle salle;
	private String type;

}
