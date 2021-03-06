package com.proxiad.formation.jpa.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proxiad.formation.jpa.dto.CommandeClientDTO;
import com.proxiad.formation.jpa.model.Client;
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
	public List<Commande> findAll() {
		String queryStr = "select c from Commande c";
		TypedQuery<Commande> query = em.createQuery(queryStr, Commande.class);
		return query.getResultList();
	}

	@Override
	public List<Commande> findAll(int startPosition, int maxResult) {
		String queryStr = "select c from Commande c order by c.id ";
		TypedQuery<Commande> query = em.createQuery(queryStr, Commande.class).setFirstResult(startPosition)
				.setMaxResults(maxResult);
		return query.getResultList();
	}

	@Override
	public List<Commande> findByNumeroClient(String numeroClient) {
		String queryStr = "select c from Commande c where c.client.numero = :numeroClient ";
		TypedQuery<Commande> query = em.createQuery(queryStr, Commande.class);
		query.setParameter("numeroClient", numeroClient);
		return query.getResultList();
	}

	@Override
	public List<Commande> findByNumeroClientCriteria(String numeroClient) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Commande> criteriaQuery = cb.createQuery(Commande.class);
		Root<Commande> commande = criteriaQuery.from(Commande.class);
		Join<Commande, Client> client = commande.join("client");

		ParameterExpression<String> pNumeroClient = cb.parameter(String.class);

		criteriaQuery.select(commande).where(cb.equal(client.get("numero"), pNumeroClient));

		TypedQuery<Commande> query = em.createQuery(criteriaQuery);
		query.setParameter(pNumeroClient, numeroClient);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Commande> findByNumeroClientHibernateCriteria(String numeroClient) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Commande.class);
		
		// on peut chainer les criteria :
		// Criteria clientCriteria = criteria.createCriteria("client");
		// clientCriteria.add(Restrictions.eq("numero", numeroClient));
		
		criteria.add(Restrictions.eq("client.numero", numeroClient));
		return criteria.list();
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

	@Override
	public List<Commande> findByDesignationArticle(String designationArticle) {
		String queryStr = "select c from Commande c join fetch c.lignes l where upper(l.article.designation) like :designationArticle ";
		TypedQuery<Commande> query = em.createQuery(queryStr, Commande.class);
		query.setParameter("designationArticle", "%" + designationArticle.toUpperCase() + "%");
		return query.getResultList();
	}

	@Override
	public Double calculeMontantTotal(String idCommande) {
		String queryStr = "select sum(l.article.prix * l.quantite) from Commande c join c.lignes l where c.id = :idCommande ";
		TypedQuery<Double> query = em.createQuery(queryStr, Double.class);
		query.setParameter("idCommande", idCommande);
		return query.getSingleResult();
	}

	@Override
	public List<Commande> findAllFetchLigne() {
		// expliquer le distinct
		//String queryStr = "select c from Commande c ";
		// String queryStr = "select distinct c from Commande c join c.lignes l ";
		String queryStr = "select distinct c from Commande c join fetch c.lignes l  ";
		TypedQuery<Commande> query = em.createQuery(queryStr, Commande.class);
		return query.getResultList();
	}

	@Override
	public void flush() {
		em.flush();
	}

	@Override
	public void detach(Commande commande) {
		em.detach(commande);
	}

	@Override
	public List<CommandeClientDTO> getCommandeClientDTOs() {
		// @formatter:off
		String queryStr = "select new com.proxiad.formation.jpa.dto.CommandeClientDTO("
				+ "c.client.nom, "
				+ "c.client.prenom, "
				+ "c.id, "
				+ "c.etat, "
				+ "c.dateCreation, "
				+ "l.quantite, "
				+ "l.article.code, "
				+ "l.article.designation, "
				+ "l.article.prix, "
				+ "l.article.prix * l.quantite) "
				+ "from Commande c join c.lignes l  ";
		// @formatter:on
		TypedQuery<CommandeClientDTO> query = em.createQuery(queryStr, CommandeClientDTO.class);
		return query.getResultList();
	}

}
