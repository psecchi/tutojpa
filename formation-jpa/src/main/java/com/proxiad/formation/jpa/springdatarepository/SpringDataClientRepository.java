package com.proxiad.formation.jpa.springdatarepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proxiad.formation.jpa.model.Client;

@Repository
public interface SpringDataClientRepository extends JpaRepository<Client, String> {

	// Pas besoin de déclarer les méthodes CRUD 
	
	// ex de methode de recherche par nom : pas besoin de code l'implementation :
	List<Client> findByPrenom(String prenom);
	
	// ex de methode utilisant @Query
	@Query("select c from Client c where c.nom = ?1 and c.prenom = ?2 ")
	List<Client> findByNomAndPrenom(String nom , String prenom);
}
