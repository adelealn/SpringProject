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
	private LocalDateTime horaireMin;
	private LocalDateTime horaireMax;
	private int ageLimite;
	private String seanceType;
}
