package com.afpa.cda.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.afpa.cda.dto.PersonneDto.PersonneDtoBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Personne {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERSONNE_SEQ")
    private int id;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String metier;
	private String adresse;
	
}
