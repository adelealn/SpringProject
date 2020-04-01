package com.aston.springproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aston.springproject.dto.CinemaDTO;
import com.aston.springproject.services.CinemaService;


@RestController
@CrossOrigin
public class CinemaController {

	@Autowired
	private CinemaService service;
	
	@PostMapping("cinemas")
	public void save(@RequestBody CinemaDTO cdto) {
		this.service.save(cdto);
	}
}
