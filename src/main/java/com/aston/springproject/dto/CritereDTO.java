package com.aston.springproject.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CritereDTO {
	private String filmGenre;
	private LocalDateTime HoraireMin;
	private LocalDateTime HoraireMax;
	private int AgeLimite;
	private String seanceType;
}
