package com.proxiad.formation.jpa.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proxiad.formation.jpa.model.Commande;
import com.proxiad.formation.jpa.repository.CommandeRepository;

@Repository
@Transactional
public class CommandeRepositoryImpl implements CommandeRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Commande find(String id) {
		return em.find(Commande.class, id);
	}

	@Override
	public List<Commande> findByNumeroClient(Integer numeroClient) {
		String queryStr = "select c from Commande c where c.client.numero = :numeroClient ";
		TypedQuery<Commande> query = em.createNamedQuery(queryStr, Commande.class);
		query.setParameter("numeroClient", numeroClient);
		return query.getResultList();
	}

	@Override
	public void create(Commande commande) {
		em.persist(commande);
	}

	@Override
	public Commande update(Commande commande) {
		return em.merge(commande);
	}

	@Override
	public void delete(Commande commande) {
		if (em.contains(commande)) {
			em.remove(commande);
		} else {
			em.remove(em.merge(commande));
		}
	}

}
