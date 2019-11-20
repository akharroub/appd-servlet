package com.afpa.cda.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PersonneDto {
	private int id;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String metier;
	private String adresse;
}
