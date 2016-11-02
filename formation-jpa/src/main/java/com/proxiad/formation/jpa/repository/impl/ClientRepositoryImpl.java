package com.proxiad.formation.jpa.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proxiad.formation.jpa.model.Client;
import com.proxiad.formation.jpa.repository.ClientRepository;

@Repository
@Transactional
public class ClientRepositoryImpl implements ClientRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Client client) {
		em.persist(client);
		em.flush();
	}

	@Override
	public Client update(Client client) {
		return em.merge(client);
	}

	@Override
	public Client find(String numeroClient) {
		return em.find(Client.class, numeroClient);
	}

	@Override
	public List<Client> findAll() {
		String query = "select c from Client c";
		return em.createQuery(query, Client.class).getResultList();
	}

	@Override
	public void delete(Client client) {
		// Tester si l'entity client est attachée au contexte de persistence.
		// Expliquer la notion d'entitée attach/detach
		if (em.contains(client)) {
			em.remove(client);
		} else {
			em.remove(em.merge(client));
		}
	}
	
	@Override
	public void flush() {
		em.flush();
	}
	
	@Override
	public void detach(Client client) {
		em.detach(client);
	}

}
