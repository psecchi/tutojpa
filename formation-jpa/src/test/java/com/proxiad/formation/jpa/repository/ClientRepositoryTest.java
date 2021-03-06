package com.proxiad.formation.jpa.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.proxiad.formation.jpa.model.Client;

@DatabaseSetup("classpath:data/client.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:data/client.xml")
public class ClientRepositoryTest extends AbstractRepositoryTest {

	@Autowired
	private ClientRepository clientRepository;

	@Test
	public void testFindAll() {
		List<Client> clients = clientRepository.findAll();
		assertEquals(1, clients.size());
		assertEquals("Zidane", clients.get(0).getNom());
	}

	@Test
	public void testFind() {
		Client client = clientRepository.find("1");
		assertEquals("Zidane", client.getNom());
	}
	
	@Test
	public void testUpdateAll() {
		clientRepository.updateAll();
		List<Client> clients = clientRepository.findAll();
		for (Client client : clients) {
			assertEquals("titi", client.getNom());
		}
	}

	@Test
	public void testSave() {
		int initialSize = clientRepository.findAll().size();
		Client client = new Client();
		client.setNumero("2");
		client.setNom("Dupont");
		client.setPrenom("Martin");

		clientRepository.create(client);

		assertEquals(initialSize + 1, clientRepository.findAll().size());
		
		// on test egalement la persistance des champs liés à la tracabilité : 
		assertNotNull(client.getDateCreation());
		assertNotNull(client.getDateMaj());
		assertTrue(client.getDateCreation().equals(client.getDateMaj()));
	}

	@Test
	public void testUpdate() {
		Client client = clientRepository.find("1");
		client.setNom("NomMAJ");

		clientRepository.update(client);
		clientRepository.flush();
		
		client = clientRepository.find("1");
		assertEquals("NomMAJ", client.getNom());
		assertEquals("Zinedine", client.getPrenom());
		
		// on test egalement la persistance des champs liés à la tracabilité : 
		assertNotNull(client.getDateMaj());
		assertFalse(client.getDateMaj().equals(client.getDateCreation()));
	}

	@Test
	public void testDelete() {
		int initialSize = clientRepository.findAll().size();

		Client client = clientRepository.find("1");

		clientRepository.delete(client);

		assertEquals(initialSize - 1, clientRepository.findAll().size());
	}
	
	@Test
	public void testTouteModifSurUneEntiteManagedEstPeriste() {
		Client client = clientRepository.find("1");
		client.setNom("NomMAJ2");

		clientRepository.flush();

		// nous n'avons pas appelée la méthode save de notre repository ni même
		// le persist de l'entityManager pourtant le nom a été mis à jour en
		// base, un ordre sql d'update a été généré lors du flush :
		assertEquals("NomMAJ2", clientRepository.find("1").getNom());
	}
	
	@Test
	public void testTouteModifSurUneEntiteDetachedEstPerdue() {
		Client client = clientRepository.find("1");
		client.setNom("NomMAJ3");

		clientRepository.detach(client);
		clientRepository.flush();

		assertEquals("Zidane", clientRepository.find("1").getNom());
	}
	
	@Test
	@Ignore("ne fonctionne que sur une vrai BDD où la proc stock existe et non une base embarquée")
	public void testGetNomClientProcStock() {
		String nom = clientRepository.getNomClientProcedureStockee("1");
		assertNotNull(nom);
		assertEquals("Zidane", nom);
	}
	
	@Test
	public void testFindByNomCriteria() {
		List<Client> clients = clientRepository.findByNomCriteria("Zidane");
		assertNotNull(clients);
		assertEquals("Zidane", clients.get(0).getNom());
	}
	
	@Test
	public void testFindByNomHibernateCriteria() {
		List<Client> clients = clientRepository.findByNomHibernateCriteria("Zidane");
		assertNotNull(clients);
		assertEquals("Zidane", clients.get(0).getNom());
	}
}
