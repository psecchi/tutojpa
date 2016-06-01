package com.proxiad.formation.jpa.springdatarepository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.proxiad.formation.jpa.model.Client;
import com.proxiad.formation.jpa.repository.AbstractRepositoryTest;

@DatabaseSetup("classpath:data/client.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:data/client.xml")
public class SpringDataClientRepositoryTest extends AbstractRepositoryTest {

	@Autowired
	private SpringDataClientRepository clientRepository;

	@Test
	public void testFindAll() {
		final List<Client> clients = clientRepository.findAll();
		assertEquals(1, clients.size());
		assertEquals("Zidane", clients.get(0).getNom());
	}

	@Test
	public void testFind() {
		final Client client = clientRepository.findOne("1");
		assertEquals("Zidane", client.getNom());
	}

	@Test
	public void testFindByPrenom() {
		final List<Client> clients = clientRepository.findByPrenom("Zinedine");
		assertEquals(1, clients.size());
		assertEquals("Zidane", clients.get(0).getNom());
	}

	@Test
	public void testFindByPrenom2() {
		final List<Client> clients = clientRepository.findByNomAndPrenom("Zidane", "Zinedine");
		assertEquals(1, clients.size());
		assertEquals("Zidane", clients.get(0).getNom());
	}

	@Test
	public void testSave() {
		int initialSize = clientRepository.findAll().size();
		Client client = new Client();
		client.setNumero("2");
		client.setNom("Dupont");
		client.setPrenom("Martin");

		clientRepository.save(client);

		assertEquals(initialSize + 1, clientRepository.findAll().size());
	}
}
