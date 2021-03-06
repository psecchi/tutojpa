package com.proxiad.formation.jpa.repository;

import java.util.List;

import com.proxiad.formation.jpa.model.Client;

public interface ClientRepository {

	void create(Client client);

	Client update(Client client);

	Client find(String numeroClient);

	List<Client> findAll();

	void delete(Client client);
	
	public void updateAll();
	
	void flush();
	
	void detach(Client client);
	
	public String getNomClientProcedureStockee(String numeroClient);
	
	public List<Client> findByNomCriteria(String nom);
	
	public List<Client> findByNomHibernateCriteria(String nom);
}
