package com.afpa.cda.service;

import java.util.List;
import java.util.Optional;

import com.afpa.cda.dto.PersonneDto;

public interface IPersonneService {
	
	public List<PersonneDto> chercherToutesLesPersonnes();

	public boolean deleteById(int id);

	public Optional<PersonneDto> findById(int id);
	
	public void ajouterPersonne(String nom,String prenom);
}
