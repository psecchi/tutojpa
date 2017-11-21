package com.proxiad.formation.jpa.springdatarepository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.proxiad.formation.jpa.springdatarepository.CommandeRepositoryCustom;

public class CommandeRepositoryCustomImpl implements CommandeRepositoryCustom {

	@PersistenceContext
	EntityManager em;
	
	
	// exemple de méthode de repository custom dans une config Spring Data
	@Override
	public Double testCustom(String idCommande) {
		String queryStr = "select sum(l.article.prix * l.quantite) from Commande c join c.lignes l where c.id = :idCommande ";
		TypedQuery<Double> query = em.createQuery(queryStr, Double.class);
		query.setParameter("idCommande", idCommande);
		return query.getSingleResult();
	}
	
}
