package com.proxiad.formation.jpa.repository;

import java.util.List;

import com.proxiad.formation.jpa.dto.CommandeClientDTO;
import com.proxiad.formation.jpa.model.Commande;

public interface CommandeRepository {

	Commande find(String id);

	List<Commande> findAll();
	
	public List<Commande> findAll(int startPosition, int maxResult);

	List<Commande> findByNumeroClient(String numeroClient);

	void create(Commande commande);

	Commande update(Commande commande);

	void delete(Commande commande);

	List<Commande> findByDesignationArticle(String designationArticle);

	Double calculeMontantTotal(String numeroCommande);

	List<Commande> findAllFetchLigne();

	List<CommandeClientDTO> getCommandeClientDTOs();
	
	public List<Commande> findByNumeroClientCriteria(String numeroClient);
	
	public List<Commande> findByNumeroClientHibernateCriteria(String numeroClient);

	// Juste pour les tests :
	void flush();

	void detach(Commande commande);

}
