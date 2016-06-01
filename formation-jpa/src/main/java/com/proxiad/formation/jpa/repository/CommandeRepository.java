package com.proxiad.formation.jpa.repository;

import java.util.List;

import com.proxiad.formation.jpa.model.Commande;

public interface CommandeRepository {

	Commande find(String id);

	List<Commande> findByNumeroClient(Integer numeroClient);

	void create(Commande commande);

	Commande update(Commande commande);

	void delete(Commande commande);

}
