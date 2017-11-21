package com.proxiad.formation.jpa.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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
	public void updateAll() {
		String query = "update Client c set c.nom = 'titi' ";
		em.createQuery(query).executeUpdate();
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
	
	@Override
	public String getNomClientProcedureStockee(String numeroClient) {
		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("test");
		storedProcedure.registerStoredProcedureParameter("numeroClient", String.class, ParameterMode.IN);
		storedProcedure.setParameter("numeroClient", numeroClient);
		storedProcedure.execute();
		return (String)storedProcedure.getSingleResult();

	}
	
	@Override
	public List<Client> findByNomCriteria(String nom) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Client> criteriaQuery = builder.createQuery(Client.class);
		Root<Client> client = criteriaQuery.from(Client.class);
		ParameterExpression<String> pNomClient = builder.parameter(String.class);
		criteriaQuery.select(client).where(builder.equal(client.get("nom"), pNomClient));

		TypedQuery<Client> query = em.createQuery(criteriaQuery);
		query.setParameter(pNomClient, nom);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> findByNomHibernateCriteria(String nom) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Client.class);
		criteria.add(Restrictions.eq("nom", nom));
		return criteria.list();
	}

}
