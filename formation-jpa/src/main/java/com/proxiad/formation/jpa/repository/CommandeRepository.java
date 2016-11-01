package com.proxiad.formation.jpa.repository;

import java.util.List;

import com.proxiad.formation.jpa.model.Commande;

public interface CommandeRepository {

	Commande find(String id);
	
	List<Commande> findAll();

	List<Commande> findByNumeroClient(String numeroClient);

	void create(Commande commande);

	Commande update(Commande commande);

	void delete(Commande commande);

	List<Commande> findByDesignationArticle(String designationArticle);
	
	Double calculeMontantTotal(String numeroCommande);
	
	// Juste pour les tests : 
	void flush();
	
	void detach(Commande commande);
	
	List<Commande> findAllFetchLigne();
	
}
