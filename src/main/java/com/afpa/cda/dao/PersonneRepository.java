package com.afpa.cda.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.afpa.cda.entity.Personne;

@Repository
public interface PersonneRepository extends CrudRepository<Personne, Integer> {
	
	@Query("select p from Personne p")
	public List<Personne> findAll();
}
