package com.proxiad.formation.jpa.springdatarepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proxiad.formation.jpa.model.Client;
import com.proxiad.formation.jpa.model.Commande;

@Repository
public interface SpringDataCommandeRepository extends JpaRepository<Commande, String> /* , CommandeRepositoryCustom */ {

	// Pas besoin de déclarer les méthodes CRUD 
	
	List<Commande> findByClientNumero(String numeroClient);
	
	List<Commande> findByClient(Client client);
	
	List<Commande> findByLignesArticleDesignationLikeIgnoreCase(String designationArticle);
	
	@Query("select sum(l.article.prix * l.quantite) from Commande c join c.lignes l where c.id = ?1 ")
	Double calculeMontantTotal(String commandeId);
	

}
