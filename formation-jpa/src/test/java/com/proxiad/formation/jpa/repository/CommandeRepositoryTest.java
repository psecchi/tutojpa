package com.proxiad.formation.jpa.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.hibernate.LazyInitializationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.proxiad.formation.jpa.dto.CommandeClientDTO;
import com.proxiad.formation.jpa.model.Article;
import com.proxiad.formation.jpa.model.Commande;
import com.proxiad.formation.jpa.model.EtatCommande;
import com.proxiad.formation.jpa.model.LigneCommande;

@DatabaseSetup("classpath:data/commande.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:data/commande.xml")
public class CommandeRepositoryTest extends AbstractRepositoryTest {

	@Autowired
	private CommandeRepository commandeRepository;
	
	@Autowired
	private ClientRepository clientRepository;

	@Test
	public void testFindById() {
		Commande commande = commandeRepository.find("1");
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
	public void testFindWithPagination() {
		int start = 0;
		int maxResult = 2;
		List<Commande> commandes = commandeRepository.findAll(start, maxResult);
		assertEquals(2, commandes.size());
		assertEquals("1", commandes.get(0).getId());
		assertEquals("2", commandes.get(1).getId());
		
		start = 2;
		commandes = commandeRepository.findAll(start, maxResult);
		assertEquals(2, commandes.size());
		assertEquals("3", commandes.get(0).getId());
		assertEquals("4", commandes.get(1).getId());
	}
	
	@Test
	public void testCreate() {
		Commande commande = new Commande();
		commande.setEtat(EtatCommande.EN_COURS);
		commande.setDateCreation(new Date());
		commande.setClient(clientRepository.find("3"));
		LigneCommande ligne1 = new LigneCommande();
		ligne1.setNumeroLigne(1);
		ligne1.setArticle(new Article(1111));
		commande.addLigne(ligne1);
		commandeRepository.create(commande);
		// EXPLIQUER LE FLUCH ET DETACH => montrer les requetes générées sans l'un et l'autre !
		commandeRepository.flush();
		commandeRepository.detach(commande);
		
		assertNotNull(commandeRepository.find(commande.getId()));
		assertEquals(1, commandeRepository.find(commande.getId()).getLignes().size());
	}
	
	@Test
	public void testUpdate() {
		Commande commande = commandeRepository.find("2");
		
		LigneCommande newLigne = new LigneCommande();
		newLigne.setNumeroLigne(commande.getLignes().size() + 1);
		newLigne.setArticle(new Article(1111));
		commande.addLigne(newLigne);
		
		commandeRepository.update(commande);
		// EXPLIQUER LE FLUCH ET DETACH => montrer les requetes générées sans l'un et l'autre !
		commandeRepository.flush();
		commandeRepository.detach(commande);
		
		assertEquals(2, commandeRepository.find(commande.getId()).getLignes().size());
	}

	@Test
	public void testFindByNumeroClient() {
		List<Commande> commandes = commandeRepository.findByNumeroClient("1");
		assertNotNull(commandes);
		assertEquals(2, commandes.size());
	}
	
	@Test
	public void testFindByNumeroClientCriteria() {
		List<Commande> commandes = commandeRepository.findByNumeroClientCriteria("1");
		assertNotNull(commandes);
		assertEquals(2, commandes.size());
	}
	
	@Test
	public void testFindByNumeroClientHibernateCriteria() {
		List<Commande> commandes = commandeRepository.findByNumeroClientHibernateCriteria("1");
		assertNotNull(commandes);
		assertEquals(2, commandes.size());
	}
	
	@Test
	public void testFindByDesignationArticle() {
		List<Commande> commandes = commandeRepository.findByDesignationArticle("tournevis");
		assertNotNull(commandes);
		assertEquals(3, commandes.size());
	}

	@Test
	public void testCalculMontantTotal() {
		Double total = commandeRepository.calculeMontantTotal("1");
		assertEquals(new Double(489.48), total);
	}

	@Test
	public void testSuppressionDeLigne() {

		Commande commande = commandeRepository.find("1");
		int nbLigne = commande.getLignes().size();

		LigneCommande ligneASupprimer = new LigneCommande();
		ligneASupprimer.setCommande(commande);
		ligneASupprimer.setNumeroLigne(1);

		commande.getLignes().remove(ligneASupprimer);
		commandeRepository.update(commande);

		// faire le test en commentant le cascade

		commandeRepository.flush(); // expliquer pourquoi fulsh et detach
		commandeRepository.detach(commande);

		Commande commandeBis = commandeRepository.find("1");
		assertEquals(nbLigne - 1, commandeBis.getLignes().size());

	}

	@Test
	public void testFindAllFetchLigne() {
		// but du test : analyser les requetes dans les logs.
		// Changer l'implémentation de commandeRepository.findAllFetchLigne() ,
		// pour chaque requete JPQL, eexcuter ce test et regarder les requetes
		// générées
		List<Commande> commandes = commandeRepository.findAllFetchLigne();
		System.out.println("avant getLigne");
		for (Commande commande : commandes) {
			System.out.println("la commande " + commande.getId() + " a " + commande.getLignes().size() + " lignes");
			
		}
		assertNotNull(commandes);
		System.out.println("après getLigne");

	}

	@Test(expected = LazyInitializationException.class)
	public void testPbLazy() {

		// But du test : tenter d'accéder à une propriété en lazy d'une entité
		// "detachée" du contexte
		Commande commande = commandeRepository.find("1");
		// commande.getLignes().size(); // si je décommente, je n'ai plus l'erreur : les lignes ont été chargée avant le detach 
		commandeRepository.detach(commande); // l'entité commande n'est plus gérée par l'entityManager
		int nbLigne = commande.getLignes().size(); // => l'appel getLignes à génère une LazyInitializationException
		assertEquals(3, nbLigne);
	}
	
	@Test
	public void testGetCommandeClientDTOs() {
		List<CommandeClientDTO> commandeClientDTOs = commandeRepository.getCommandeClientDTOs();
		assertEquals(6, commandeClientDTOs.size());
		for (CommandeClientDTO commandeClientDTO : commandeClientDTOs) {
			System.out.println(commandeClientDTO);
		}
	}
	

}
