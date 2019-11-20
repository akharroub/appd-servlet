package com.afpa.cda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.PersonneRepository;
import com.afpa.cda.dto.PersonneDto;
import com.afpa.cda.entity.Personne;

@Service
public class PersonneServiceImpl implements IPersonneService {

	@Autowired
	private PersonneRepository personneRepository;
	
	@Override
	public List<PersonneDto> chercherToutesLesPersonnes() {
		return this.personneRepository
			.findAll()
			.stream()
			.map(e->PersonneDto.builder()
					.id(e.getId())
					.nom(e.getNom())
					.prenom(e.getPrenom())
					.build())
			.collect(Collectors.toList());
	}

	@Override
	public boolean deleteById(int id) {
		if(this.personneRepository.existsById(id)) {
			this.personneRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Optional<PersonneDto> findById(int id) {
		Optional<Personne> pers = this.personneRepository.findById(id);
		Optional<PersonneDto> res = Optional.empty();
		if(pers.isPresent()) {
			Personne p = pers.get();
			res = Optional.of(
					PersonneDto.builder()
					.id(p.getId())
					.nom(p.getNom())
					.prenom(p.getPrenom())
					.adresse(p.getAdresse())
					.dateNaissance(p.getDateNaissance())
					.build());
		}
		return res;
	}

	@Override
	public void ajouterPersonne(String nom, String prenom) {
		Personne personne = Personne.builder().nom(nom).prenom(prenom).build();
		this.personneRepository.save(personne);
		
	}

}
