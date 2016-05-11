package com.proxiad.formation.jpa.repository;

import java.util.List;

import com.proxiad.formation.jpa.model.Client;

public interface ClientRepository {

	public void create(Client client);
	
	public Client update(Client client);
	
	Client find(String numeroClient);
	
	List<Client> findAll();
}
