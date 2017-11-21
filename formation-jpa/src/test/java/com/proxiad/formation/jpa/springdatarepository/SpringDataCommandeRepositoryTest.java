package com.proxiad.formation.jpa.springdatarepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.proxiad.formation.jpa.model.Client;
import com.proxiad.formation.jpa.model.Commande;
import com.proxiad.formation.jpa.model.EtatCommande;
import com.proxiad.formation.jpa.repository.AbstractRepositoryTest;

@DatabaseSetup("classpath:data/commande.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:data/commande.xml")
public class SpringDataCommandeRepositoryTest extends AbstractRepositoryTest {

	@Autowired
	private SpringDataCommandeRepository commandeRepository;
	
	@Autowired
	private SpringDataClientRepository clientRepository;

	@Test
	public void testFindById() {
		Commande commande = commandeRepository.findOne("1");
		assertNotNull(commande);
		assertEquals(3, commande.getLignes().size());
	}

	@Test
	public void testFindAll() {
		List<Commande> commandes = commandeRepository.findAll();
		assertNotNull(commandes);
		assertEquals(4, commandes.size());
	}
	
	@Test
	public void testCreate() {
		Commande commande = new Commande();
		commande.setEtat(EtatCommande.EN_COURS);
		commande.setDateCreation(new Date());
		commande.setClient(clientRepository.findOne("3"));
		
		commandeRepository.save(commande);
		
		assertEquals(2, commandeRepository.findByClientNumero("3").size());
	}

	@Test
	public void testFindByNumeroClient() {
		List<Commande> commandes = commandeRepository.findByClientNumero("1");
		assertNotNull(commandes);
		assertEquals(2, commandes.size());
		
		
		Client client = new Client();
		client.setNumero("1");
		commandes = commandeRepository.findByClient(client);
		assertNotNull(commandes);
		assertEquals(2, commandes.size());
	}

	@Test
	public void testFindByDesignationArticle() {
		List<Commande> commandes = commandeRepository.findByLignesArticleDesignationLikeIgnoreCase("%tournevis%");
		assertNotNull(commandes);
		assertEquals(3, commandes.size());
	}

	@Test
	public void testCalculMontantTotal() {
		Double total = commandeRepository.calculeMontantTotal("1");
		assertEquals(new Double(489.48), total);
	}
	
//	@Test
//	public void testToto() {
//		Double total = commandeRepository.toto("1");
//		assertEquals(new Double(489.48), total);
//	}
	

}
