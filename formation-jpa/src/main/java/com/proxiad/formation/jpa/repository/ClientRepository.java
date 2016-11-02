package com.proxiad.formation.jpa.repository;

import java.util.List;

import com.proxiad.formation.jpa.model.Client;

public interface ClientRepository {

	void create(Client client);

	Client update(Client client);

	Client find(String numeroClient);

	List<Client> findAll();

	void delete(Client client);
	
	void flush();
	
	void detach(Client client);
}
